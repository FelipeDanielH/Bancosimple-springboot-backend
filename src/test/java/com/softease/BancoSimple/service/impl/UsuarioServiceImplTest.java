package com.softease.BancoSimple.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.softease.BancoSimple.dto.auth.UsuarioResponseDTO;
import com.softease.BancoSimple.model.Usuario;
import com.softease.BancoSimple.repository.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioServiceImpl service;

    @Test
    void obtenerPorId_DeberiaRetornarUsuarioDTO() {
        Usuario usuario = new Usuario(1, "Fran", "fran@mail.com", "1234", LocalDateTime.now());
        when(repository.findById(1)).thenReturn(Optional.of(usuario));

        UsuarioResponseDTO dto = service.obtenerPorId(1);

        assertEquals("Fran", dto.getNombre());
    }

    @Test
    void buscarUsuarioPorUsername_DeberiaRetornarOptionalUsuario() {
        Usuario usuario = new Usuario(1, "Fran", "fran@mail.com", "1234", LocalDateTime.now());
        when(repository.findByEmail("fran@mail.com")).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = service.buscarUsuarioPorEmail("fran@mail.com");

        assertTrue(resultado.isPresent());
        assertEquals("Fran", resultado.get().getNombre());
    }
}
