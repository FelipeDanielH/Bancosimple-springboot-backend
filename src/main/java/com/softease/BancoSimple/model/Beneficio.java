package com.softease.BancoSimple.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "beneficios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(precision = 5, scale = 2)
    private BigDecimal descuento;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean activo;
}

