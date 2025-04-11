package com.softease.BancoSimple.mapper;

import com.softease.BancoSimple.dto.RecargaDTO;
import com.softease.BancoSimple.model.Recarga;

public class RecargaMapper {

    public static RecargaDTO toDTO(Recarga recarga) {
        return RecargaDTO.builder()
                .id(recarga.getId())
                .cuentaId(recarga.getCuenta().getId())
                .monto(recarga.getMonto())
                .estado(recarga.getEstado().name())
                .fecha(recarga.getFecha())
                .build();
    }

    public static Recarga toEntity(RecargaDTO dto) {
        Recarga.RecargaBuilder builder = Recarga.builder()
                .id(dto.getId())
                .monto(dto.getMonto())
                .estado(Recarga.EstadoRecarga.valueOf(dto.getEstado()))
                .fecha(dto.getFecha());

        return builder.build();
    }
}
