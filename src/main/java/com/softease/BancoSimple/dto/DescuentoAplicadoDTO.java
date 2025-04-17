package com.softease.BancoSimple.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DescuentoAplicadoDTO {
    private Integer id;
    private Integer transaccionId;
    private Integer beneficioId;
    private BigDecimal montoDescuento;
}
