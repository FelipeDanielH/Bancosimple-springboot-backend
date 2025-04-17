package com.softease.BancoSimple.mapper;

import com.softease.BancoSimple.dto.AuditoriaTransaccionDTO;
import com.softease.BancoSimple.model.AuditoriaTransaccion;
import com.softease.BancoSimple.model.Transaccion;

public class AuditoriaTransaccionMapper {

    public static AuditoriaTransaccionDTO toDTO(AuditoriaTransaccion a) {
        return AuditoriaTransaccionDTO.builder()
                .id(a.getId())
                .transaccionId(a.getTransaccion().getId())
                .estadoAnterior(a.getEstadoAnterior().name())
                .estadoNuevo(a.getEstadoNuevo().name())
                .fecha(a.getFecha())
                .build();
    }

    public static AuditoriaTransaccion toEntity(AuditoriaTransaccionDTO dto) {
        return AuditoriaTransaccion.builder()
                .id(dto.getId())
                .transaccion(Transaccion.builder().id(dto.getTransaccionId()).build())
                .estadoAnterior(AuditoriaTransaccion.EstadoAuditoria.valueOf(dto.getEstadoAnterior()))
                .estadoNuevo(AuditoriaTransaccion.EstadoAuditoria.valueOf(dto.getEstadoNuevo()))
                .fecha(dto.getFecha())
                .build();
    }
}
