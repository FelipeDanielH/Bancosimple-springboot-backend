package com.softease.BancoSimple.dto.transacciones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraRequestDTO {
    private String nombreTitular;
    private String numeroTarjeta;
    private String cvv;
    private String fechaExpiracionMes;
    private String fechaExpiracionAnio;
    private String numeroTarjetaVendedor;
    private BigDecimal monto;
    private String descripcion;
}
