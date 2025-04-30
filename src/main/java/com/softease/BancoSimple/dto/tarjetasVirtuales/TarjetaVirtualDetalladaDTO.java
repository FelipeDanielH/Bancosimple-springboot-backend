package com.softease.BancoSimple.dto.tarjetasVirtuales;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TarjetaVirtualDetalladaDTO {
    private Integer id;
    private Integer cuentaId;
    private String numeroTarjeta;
    private String cvv;
    private BigDecimal saldo;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaExpiracion;
}
