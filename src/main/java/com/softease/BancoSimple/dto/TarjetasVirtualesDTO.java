package com.softease.BancoSimple.dto;

import com.softease.BancoSimple.model.Cuenta;
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
public class TarjetasVirtualesDTO {

    private Integer id;

    private Integer cuentaId;

    private String numeroTarjeta;

    private String cvv;

    private BigDecimal saldo;

    private String estado;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaExpiracion;
}
