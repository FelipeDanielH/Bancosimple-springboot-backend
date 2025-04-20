package com.softease.BancoSimple.mapper;

import com.softease.BancoSimple.dto.TransaccionDTO;
import com.softease.BancoSimple.model.Cuenta;
import com.softease.BancoSimple.model.Transaccion;

public class TransaccionMapper {

    public static TransaccionDTO toDTO(Transaccion t) {
        return TransaccionDTO.builder()
                .id(t.getId())
                .cuentaOrigenId(t.getCuentaOrigen().getId())
                .cuentaDestinoId(t.getCuentaDestino().getId())
                .monto(t.getMonto())
                .tipo(t.getTipo().name())
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
                .tipo(Transaccion.TipoTransaccion.valueOf(dto.getTipo()))
                .estado(Transaccion.EstadoTransaccion.valueOf(dto.getEstado()))
                .fechaTransaccion(dto.getFechaTransaccion())
                .build();
    }
}

