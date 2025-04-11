package com.softease.BancoSimple.controller;

import com.softease.BancoSimple.dto.CuentaDTO;
import com.softease.BancoSimple.service.CuentaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CuentaControllerTest {

    @Mock
    private CuentaService cuentaService;

    @InjectMocks
    private CuentaController cuentaController;

    // Helper para crear DTOs consistentes
    private CuentaDTO crearCuentaDTOEjemplo(Integer id, String tipo) {
        return CuentaDTO.builder()
                .id(id)
                .usuarioId(100)
                .numeroCuenta("ES" + id)
                .tipo(tipo)
                .saldo(new BigDecimal("1000.00"))
                .fechaCreacion(LocalDateTime.now())
                .build();
    }

    @Test
    void obtenerTodas_RetornaListaCuentas() {
        // Arrange
        CuentaDTO cuenta1 = crearCuentaDTOEjemplo(1, "corriente");
        CuentaDTO cuenta2 = crearCuentaDTOEjemplo(2, "ahorro");
        when(cuentaService.obtenerTodas()).thenReturn(List.of(cuenta1, cuenta2));

        // Act
        List<CuentaDTO> resultado = cuentaController.obtenerTodas();

        // Assert
        assertAll("Verificación lista completa",
                () -> assertEquals(2, resultado.size()),
                () -> assertEquals("corriente", resultado.get(0).getTipo()),
                () -> verify(cuentaService).obtenerTodas()
        );
    }

    @Test
    void obtenerPorId_Existente_RetornaCuenta() {
        // Arrange
        CuentaDTO cuentaMock = crearCuentaDTOEjemplo(1, "corriente");
        when(cuentaService.obtenerPorId(1)).thenReturn(cuentaMock);

        // Act
        CuentaDTO resultado = cuentaController.obtenerPorId(1);

        // Assert
        assertAll("Campos principales",
                () -> assertEquals(1, resultado.getId()),
                () -> assertEquals("ES1", resultado.getNumeroCuenta()),
                () -> assertNotNull(resultado.getFechaCreacion())
        );
    }

    @Test
    void crearCuenta_Valida_RetornaCreada() {
        // Arrange
        CuentaDTO nuevaCuenta = crearCuentaDTOEjemplo(null, "ahorro");
        CuentaDTO cuentaCreada = crearCuentaDTOEjemplo(3, "ahorro");
        when(cuentaService.crear(nuevaCuenta)).thenReturn(cuentaCreada);

        // Act
        CuentaDTO resultado = cuentaController.crear(nuevaCuenta);

        // Assert
        assertAll("Creación exitosa",
                () -> assertEquals(3, resultado.getId()),
                () -> assertEquals("ahorro", resultado.getTipo()),
                () -> verify(cuentaService).crear(nuevaCuenta)
        );
    }


    @Test
    void eliminarCuenta_EjecutaSinErrores() {
        // Act & Assert
        assertDoesNotThrow(() -> cuentaController.eliminar(1));
        verify(cuentaService).eliminar(1);
    }

    @Test
    void obtenerPorId_Inexistente_LanzaExcepcion() {
        when(cuentaService.obtenerPorId(999)).thenReturn(null);

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> cuentaController.obtenerPorId(999)
        );

        assertAll("Validación excepción",
                () -> assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode()),
                () -> assertTrue(exception.getMessage().contains("999"))
        );
    }
}