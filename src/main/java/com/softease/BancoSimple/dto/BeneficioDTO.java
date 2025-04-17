package com.softease.BancoSimple.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeneficioDTO {
    private Integer id;
    private String descripcion;
    private BigDecimal descuento;
    private Boolean activo;
}

