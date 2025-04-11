package com.softease.BancoSimple.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

class CuentaTest {

    @Test
    void testBuilderCompleto() {
        LocalDateTime fecha = LocalDateTime.now();

        Cuenta cuenta = Cuenta.builder()
                .id(1)
                .usuarioId(100)
                .numeroCuenta("ES123456789")
                .tipo(Cuenta.TipoCuenta.corriente)
                .saldo(new BigDecimal("1500.75"))
                .fechaCreacion(fecha)
                .build();

        assertAll("Verificación de campos builder",
                () -> assertEquals(1, cuenta.getId()),
                () -> assertEquals(100, cuenta.getUsuarioId()),
                () -> assertEquals("ES123456789", cuenta.getNumeroCuenta()),
                () -> assertEquals(Cuenta.TipoCuenta.corriente, cuenta.getTipo()),
                () -> assertEquals(0, new BigDecimal("1500.75").compareTo(cuenta.getSaldo())),
                () -> assertEquals(fecha, cuenta.getFechaCreacion())
        );
    }

    @Test
    void testCamposObligatorios() {
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuarioId(200);
        cuenta.setNumeroCuenta("ES987654321");
        cuenta.setTipo(Cuenta.TipoCuenta.ahorro);
        cuenta.setSaldo(BigDecimal.ZERO);

        assertAll("Validación de campos no nulos",
                () -> assertNotNull(cuenta.getUsuarioId()),
                () -> assertNotNull(cuenta.getNumeroCuenta()),
                () -> assertNotNull(cuenta.getTipo()),
                () -> assertNotNull(cuenta.getSaldo())
        );
    }

    @Test
    void testEnumTiposCuenta() {
        assertAll("Validación de valores del enum",
                () -> assertEquals("corriente", Cuenta.TipoCuenta.corriente.name()),
                () -> assertEquals("ahorro", Cuenta.TipoCuenta.ahorro.name())
        );
    }

    @Test
    void testConstructorAllArgs() {
        LocalDateTime fecha = LocalDateTime.of(2025, 1, 1, 12, 0);
        Cuenta cuenta = new Cuenta(2, 300, "ES00000000",
                Cuenta.TipoCuenta.ahorro,
                new BigDecimal("500.25"),
                fecha);

        assertAll("Verificación de constructor completo",
                () -> assertEquals(2, cuenta.getId()),
                () -> assertEquals("ES00000000", cuenta.getNumeroCuenta()),
                () -> assertEquals(300, cuenta.getUsuarioId()),
                () -> assertEquals(Cuenta.TipoCuenta.ahorro, cuenta.getTipo())
        );
    }
}