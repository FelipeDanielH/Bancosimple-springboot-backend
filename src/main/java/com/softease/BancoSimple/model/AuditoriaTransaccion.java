package com.softease.BancoSimple.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria_transacciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaTransaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "transaccion_id")
    private Transaccion transaccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_anterior", nullable = false)
    private EstadoAuditoria estadoAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_nuevo", nullable = false)
    private EstadoAuditoria estadoNuevo;

    @Column
    private LocalDateTime fecha;

    public enum EstadoAuditoria {
        pendiente, completado, fallido
    }
}
