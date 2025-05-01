package com.softease.BancoSimple.service.impl;

import com.softease.BancoSimple.dto.transacciones.CompraRequestDTO;
import com.softease.BancoSimple.dto.transacciones.TransaccionDTO;
import com.softease.BancoSimple.dto.transacciones.TransaccionFormDTO;
import com.softease.BancoSimple.dto.transacciones.TransaccionSummaryDTO;
import com.softease.BancoSimple.mapper.TransaccionMapper;
import com.softease.BancoSimple.model.Cuenta;
import com.softease.BancoSimple.model.TarjetasVirtuales;
import com.softease.BancoSimple.model.Transaccion;
import com.softease.BancoSimple.repository.CuentaRepository;
import com.softease.BancoSimple.repository.TarjetasVirtualesRepository;
import com.softease.BancoSimple.repository.TransaccionRepository;
import com.softease.BancoSimple.repository.UsuarioRepository;
import com.softease.BancoSimple.service.CuentaService;
import com.softease.BancoSimple.service.TransaccionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final CuentaRepository cuentaRepository;
    private final CuentaService cuentaService;
    private final TarjetasVirtualesRepository tarjetasVirtualesRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<TransaccionDTO> obtenerTodas() {
        return transaccionRepository.findAll().stream()
                .map(TransaccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransaccionDTO obtenerPorId(Integer id) {
        return transaccionRepository.findById(id)
                .map(TransaccionMapper::toDTO)
                .orElse(null);
    }

    @Override
    public TransaccionDTO crear(TransaccionDTO dto) {
        Transaccion entidad = TransaccionMapper.toEntity(dto);
        return TransaccionMapper.toDTO(transaccionRepository.save(entidad));
    }

    @Override
    public void eliminar(Integer id) {
        transaccionRepository.deleteById(id);
    }

    // Metodos personalizados
    @Override
    public List<TransaccionSummaryDTO> obtenerUltimasTransacciones(Integer userId, int limite) {
        Pageable page = PageRequest.of(0, limite);
        List<Transaccion> lista = transaccionRepository.findUltimasByUser(userId, page);
        return lista.stream()
                .map(t -> TransaccionMapper.toSummaryDto(t, userId))
                .collect(Collectors.toList());
    }

    @Override
    public TransaccionDTO procesarTransaccion(TransaccionFormDTO request) {
        // 1. Resuelvo IDs de cuentas
        Integer origenId = request.getIdUsuarioOrigen();
        Cuenta cuentaDestino = cuentaRepository.findByNumeroCuenta(request.getNumeroCuenta());
        Integer destinoId = cuentaDestino.getId();

        // 2. Construyo entidad y la persisto con estado PENDIENTE
        Transaccion entidad = Transaccion.builder()
                .cuentaOrigen(Cuenta.builder().id(origenId).build())
                .cuentaDestino(Cuenta.builder().id(destinoId).build())
                .monto(request.getMonto())
                .descripcion(request.getDescripcion())
                .estado(Transaccion.EstadoTransaccion.pendiente)
                .fechaTransaccion(LocalDateTime.now())
                .build();

        Transaccion pendiente = transaccionRepository.save(entidad);

        // 3. Ajusto saldos: debito en origen y crédito en destino
        cuentaService.debitar(origenId, request.getMonto());
        cuentaService.acreditar(destinoId, request.getMonto());

        // 4. Marco la transacción como COMPLETADA y actualizo
        pendiente.setEstado(Transaccion.EstadoTransaccion.completado);
        Transaccion completada = transaccionRepository.save(pendiente);

        // 5. Retorno el DTO final
        return TransaccionMapper.toDTO(completada);
    }


    @Override
    public TransaccionDTO procesarCompra(CompraRequestDTO req) {
        // 1. Validar y obtener tarjeta del comprador por número de cuenta (numeroTarjeta = numeroCuenta)
        Cuenta cuentaComprador = cuentaRepository
                .findByNumeroCuenta(req.getNumeroTarjeta());

        TarjetasVirtuales tarjetaComprador = tarjetasVirtualesRepository
                .findFirstByCuentaId_Id(cuentaComprador.getId());

        // 1.a Formatear y validar fecha de expiración
        String fechaFormateada = String.format("%02d/%02d",
                tarjetaComprador.getFechaExpiracion().getMonthValue(),
                tarjetaComprador.getFechaExpiracion().getYear() % 100);

        // 1.b Validar CVV, titular y fecha (titular viene de Usuario)
        String nombreReal = usuarioRepository.findById(cuentaComprador.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"))
                .getNombre();

        System.out.println("nombre real: " + nombreReal);
        System.out.println("cuenta comprador: " + cuentaComprador.getNumeroCuenta());
        System.out.println("fecha formateada: " + fechaFormateada);
        System.out.println("cvv " + tarjetaComprador.getCvv());

        if (!tarjetaComprador.getCvv().equals(req.getCvv())
                || !nombreReal.equalsIgnoreCase(req.getNombreTitular())
                || !fechaFormateada.equals(req.getFechaExpiracionMes() + "/" + req.getFechaExpiracionAnio())) {
            throw new RuntimeException("Datos de tarjeta inválidos");
        }

        // 2. Debitar saldo del comprador
        Integer origenId = cuentaComprador.getId();
        cuentaService.debitar(origenId, req.getMonto());

        // 3. Dese cuenta vendedora y tarjetavirtual del vendedor
        Cuenta cuentaVendedor = cuentaRepository
                .findByNumeroCuenta(req.getNumeroTarjetaVendedor());

        TarjetasVirtuales tarjetaVendedora = tarjetasVirtualesRepository
                .findFirstByCuentaId_Id(cuentaVendedor.getId());

        Integer destinoId = cuentaVendedor.getId();
        cuentaService.acreditar(destinoId, req.getMonto());

        // 4. Construir y guardar Transaccion
        Transaccion entidad = Transaccion.builder()
                .cuentaOrigen(Cuenta.builder().id(origenId).build())
                .cuentaDestino(Cuenta.builder().id(destinoId).build())
                .monto(req.getMonto())
                .descripcion(req.getDescripcion())
                .estado(Transaccion.EstadoTransaccion.pendiente)
                .fechaTransaccion(LocalDateTime.now())
                .build();

        Transaccion pendiente = transaccionRepository.save(entidad);

        // 5. Actualizar estado y volver a guardar
        pendiente.setEstado(Transaccion.EstadoTransaccion.completado);
        Transaccion completada = transaccionRepository.save(pendiente);

        // 6. Devolver DTO
        return TransaccionMapper.toDTO(completada);
    }

}
