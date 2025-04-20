package com.softease.BancoSimple.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cuenta_origen_id", nullable = false)
    private Cuenta cuentaOrigen;

    @ManyToOne
    @JoinColumn(name = "cuenta_destino_id", nullable = false)
    private Cuenta cuentaDestino;

    @Column(nullable = false)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransaccion tipo;

    @Enumerated(EnumType.STRING)
    private EstadoTransaccion estado = EstadoTransaccion.pendiente;

    @Column(name = "fecha_transaccion")
    private LocalDateTime fechaTransaccion;

    public enum TipoTransaccion {
        transferencia, recarga, pago
    }

    public enum EstadoTransaccion {
        pendiente, completado, fallido
    }
}
