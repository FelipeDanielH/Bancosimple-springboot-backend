package com.softease.BancoSimple.service.impl;

import com.softease.BancoSimple.dto.transacciones.TransaccionDTO;
import com.softease.BancoSimple.dto.transacciones.TransaccionFormDTO;
import com.softease.BancoSimple.dto.transacciones.TransaccionSummaryDTO;
import com.softease.BancoSimple.mapper.TransaccionMapper;
import com.softease.BancoSimple.model.Cuenta;
import com.softease.BancoSimple.model.Transaccion;
import com.softease.BancoSimple.repository.CuentaRepository;
import com.softease.BancoSimple.repository.TransaccionRepository;
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
//        cuentaService.debitar(origenId, request.getMonto());
//        cuentaService.acreditar(destinoId, request.getMonto());

        // 4. Marco la transacción como COMPLETADA y actualizo
        pendiente.setEstado(Transaccion.EstadoTransaccion.completado);
        Transaccion completada = transaccionRepository.save(pendiente);

        // 5. Retorno el DTO final
        return TransaccionMapper.toDTO(completada);
    }



}
