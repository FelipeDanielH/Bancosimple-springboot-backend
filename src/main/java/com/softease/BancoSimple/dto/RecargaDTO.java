package com.softease.BancoSimple.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RecargaDTO {
    private Integer id;
    private Integer cuentaId;
    private BigDecimal monto;
    private String estado;
    private LocalDateTime fecha;
}
