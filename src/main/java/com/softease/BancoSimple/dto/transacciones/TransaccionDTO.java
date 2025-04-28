package com.softease.BancoSimple.dto.transacciones;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaccionDTO {

    private Integer id;
    private Integer cuentaOrigenId;
    private Integer cuentaDestinoId;
    private BigDecimal monto;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaTransaccion;
}
