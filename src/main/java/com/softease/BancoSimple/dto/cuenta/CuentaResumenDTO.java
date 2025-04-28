package com.softease.BancoSimple.dto.cuenta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaResumenDTO {
    private BigDecimal saldoDisponible;
    private String numeroCuenta;
    private String nombreTitular;
    private String tipoCuenta;
}