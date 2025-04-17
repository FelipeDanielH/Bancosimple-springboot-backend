package com.softease.BancoSimple.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaTransaccionDTO {
    private Integer id;
    private Integer transaccionId;
    private String estadoAnterior;
    private String estadoNuevo;
    private LocalDateTime fecha;
}
