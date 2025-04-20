package com.softease.BancoSimple.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void testConstructorVacio_CreaInstanciaValida() {
        Usuario usuario = new Usuario();
        assertNotNull(usuario, "El constructor vacío debe crear una instancia");
    }

    @Test
    void testConstructorCompleto_InicializaCamposCorrectamente() {
        LocalDateTime fecha = LocalDateTime.now();
        Usuario usuario = new Usuario(
                1,
                "Ana López",
                "ana@example.com",
                "secreto123",
                fecha
        );

        assertAll("Verificación de campos con constructor completo",
                () -> assertEquals(1, usuario.getId()),
                () -> assertEquals("Ana López", usuario.getNombre()),
                () -> assertEquals("ana@example.com", usuario.getEmail()),
                () -> assertEquals("secreto123", usuario.getPassword()),
                () -> assertEquals(fecha, usuario.getFechaRegistro())
        );
    }

    @Test
    void testGettersYSetters_ModificanCamposCorrectamente() {
        Usuario usuario = new Usuario();
        LocalDateTime fecha = LocalDateTime.of(2025, 1, 1, 12, 30);

        usuario.setId(2);
        usuario.setNombre("Carlos Ruiz");
        usuario.setEmail("carlos@empresa.cl");
        usuario.setPassword("claveSegura#");
        usuario.setFechaRegistro(fecha);

        assertAll("Validación de setters/getters",
                () -> assertEquals(2, usuario.getId()),
                () -> assertEquals("Carlos Ruiz", usuario.getNombre()),
                () -> assertEquals("carlos@empresa.cl", usuario.getEmail()),
                () -> assertEquals("claveSegura#", usuario.getPassword()),
                () -> assertEquals(fecha, usuario.getFechaRegistro())
        );
    }

    @Test
    void testIgualdad_UsuariosConMismoId_SonIguales() {
        Usuario usuario1 = new Usuario(3, "Maria", "maria@test.com", "pass", null);
        Usuario usuario2 = new Usuario(3, "Maria", "maria@test.com", "pass", null);

        assertEquals(usuario1, usuario2, "Usuarios con mismo ID deben ser iguales");
        assertEquals(usuario1.hashCode(), usuario2.hashCode(), "Hash codes deben coincidir");
    }

    @Test
    void testToString_ContieneInformacionRelevante() {
        Usuario usuario = new Usuario(4, "Juan", "juan@mail.com", "1234", null);
        String texto = usuario.toString();

        assertAll("Validación de representación en texto",
                () -> assertTrue(texto.contains("Juan")),
                () -> assertTrue(texto.contains("juan@mail.com"))
        );
    }
}