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
public class TransaccionFormDTO {
    private Integer idUsuarioOrigen;     // email del usuario que origina
    private String nombreDestinatario;   // nombre del beneficiario
    private String rutDestinatario;      // RUT del beneficiario
    private String numeroCuenta;         // número de cuenta destino
    private BigDecimal monto;            // monto a transferir
    private String descripcion;          // descripción del pago
}
