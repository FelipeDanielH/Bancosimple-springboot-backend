package com.softease.BancoSimple.dto.transacciones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaccionSummaryDTO {
    private String descripcion;
    private LocalDateTime fecha;
    private BigDecimal monto;
    private String tipo;    // "in" o "out"
    private String estado;
}
