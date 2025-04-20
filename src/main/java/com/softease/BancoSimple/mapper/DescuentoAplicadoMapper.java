package com.softease.BancoSimple.mapper;

import com.softease.BancoSimple.dto.DescuentoAplicadoDTO;
import com.softease.BancoSimple.model.Beneficio;
import com.softease.BancoSimple.model.DescuentoAplicado;
import com.softease.BancoSimple.model.Transaccion;

public class DescuentoAplicadoMapper {

    public static DescuentoAplicadoDTO toDTO(DescuentoAplicado d) {
        return DescuentoAplicadoDTO.builder()
                .id(d.getId())
                .transaccionId(d.getTransaccion().getId())
                .beneficioId(d.getBeneficio().getId())
                .montoDescuento(d.getMontoDescuento())
                .build();
    }

    public static DescuentoAplicado toEntity(DescuentoAplicadoDTO dto) {
        return DescuentoAplicado.builder()
                .id(dto.getId())
                .transaccion(Transaccion.builder().id(dto.getTransaccionId()).build())
                .beneficio(Beneficio.builder().id(dto.getBeneficioId()).build())
                .montoDescuento(dto.getMontoDescuento())
                .build();
    }
}
