package com.softease.BancoSimple.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarjetas_virtuales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarjetasVirtuales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy es para rendimiento (??
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuentaId;


    @Column(nullable = false)
    private String cvv;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTarjeta estado;

    @Column(name = "fecha_creacion", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_expiracion", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime fechaExpiracion;

    public enum EstadoTarjeta {
        activa, inactiva, bloqueada
    }
}
