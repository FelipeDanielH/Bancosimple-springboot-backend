package com.softease.BancoSimple.mapper;

import com.softease.BancoSimple.dto.BeneficioDTO;
import com.softease.BancoSimple.model.Beneficio;


public class BeneficioMapper {

    public static BeneficioDTO toDTO(Beneficio beneficio) {
        return BeneficioDTO.builder()
                .id(beneficio.getId())
                .descripcion(beneficio.getDescripcion())
                .descuento(beneficio.getDescuento())
                .activo(beneficio.getActivo())
                .build();
    }

    public static Beneficio toEntity(BeneficioDTO dto) {
        return Beneficio.builder()
                .id(dto.getId())
                .descripcion(dto.getDescripcion())
                .descuento(dto.getDescuento())
                .activo(dto.getActivo())
                .build();
    }
}

