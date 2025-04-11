package com.softease.BancoSimple.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaDTO {
    private Integer id;
    private Integer usuarioId;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldo;
    private LocalDateTime fechaCreacion;
}
