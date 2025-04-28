package com.softease.BancoSimple.mapper;

import com.softease.BancoSimple.dto.transacciones.TransaccionDTO;
import com.softease.BancoSimple.dto.transacciones.TransaccionFormDTO;
import com.softease.BancoSimple.dto.transacciones.TransaccionSummaryDTO;
import com.softease.BancoSimple.model.Cuenta;
import com.softease.BancoSimple.model.Transaccion;

public class TransaccionMapper {

    public static TransaccionDTO toDTO(Transaccion t) {
        return TransaccionDTO.builder()
                .id(t.getId())
                .cuentaOrigenId(t.getCuentaOrigen().getId())
                .cuentaDestinoId(t.getCuentaDestino().getId())
                .monto(t.getMonto())
                .descripcion(t.getDescripcion())
                .estado(t.getEstado().name())
                .fechaTransaccion(t.getFechaTransaccion())
                .build();
    }

    public static Transaccion toEntity(TransaccionDTO dto) {
        return Transaccion.builder()
                .id(dto.getId())
                .cuentaOrigen(Cuenta.builder().id(dto.getCuentaOrigenId()).build())
                .cuentaDestino(Cuenta.builder().id(dto.getCuentaDestinoId()).build())
                .monto(dto.getMonto())
                .descripcion(dto.getDescripcion())
                .estado(Transaccion.EstadoTransaccion.valueOf(dto.getEstado()))
                .fechaTransaccion(dto.getFechaTransaccion())
                .build();
    }

    public static TransaccionSummaryDTO toSummaryDto(Transaccion t, Integer userId) {
        TransaccionSummaryDTO s = new TransaccionSummaryDTO();
        s.setDescripcion(t.getDescripcion());
        s.setFecha(t.getFechaTransaccion());
        s.setMonto(t.getMonto());
        s.setEstado(t.getEstado().name());
        // comparar el userId con el id de origen
        if (userId.equals(t.getCuentaOrigen().getId())) {
            s.setTipo("out");
        } else {
            s.setTipo("in");
        }
        return s;
    }

    public TransaccionDTO fromRequest(TransaccionFormDTO req) {
        TransaccionDTO dto = new TransaccionDTO();
        // TODO: inyectar CuentaService y usarlo para convertir:
        // Integer origenId   = cuentaService.buscarPorEmail(req.getIdUsuarioOrigen()).getId();
        // Long destinoId  = cuentaService.buscarPorNumeroCuenta(req.getNumeroCuenta()).getId();
        dto.setCuentaOrigenId(/*origenId*/ null);
        dto.setCuentaDestinoId(/*destinoId*/ null);

        dto.setMonto(req.getMonto());
        dto.setDescripcion(req.getDescripcion());
        // Al crear la transacción, el estado se establecerá en 'pendiente' y fecha ahora.
        // dto.setEstado("pendiente");
        // dto.setFechaTransaccion(LocalDateTime.now());

        return dto;
    }

}

