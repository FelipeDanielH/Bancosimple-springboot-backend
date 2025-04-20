package com.softease.BancoSimple.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "descuentos_aplicados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DescuentoAplicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "transaccion_id")
    private Transaccion transaccion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "beneficio_id")
    private Beneficio beneficio;

    @Column(name = "monto_descuento", nullable = false)
    private BigDecimal montoDescuento;
}

