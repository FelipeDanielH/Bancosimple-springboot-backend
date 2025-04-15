package com.softease.BancoSimple.service;

import com.softease.BancoSimple.dto.auth.RegisterRequest;
import com.softease.BancoSimple.dto.auth.UsuarioResponseDTO;
import com.softease.BancoSimple.model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UsuarioService extends UserDetailsService {

    List<UsuarioResponseDTO> obtenerTodos();
    UsuarioResponseDTO obtenerPorId(Integer id);
    UsuarioResponseDTO crear(RegisterRequest request);
    UsuarioResponseDTO actualizar(Integer id, RegisterRequest request);
    void eliminar(Integer id);

    Optional<Usuario> buscarUsuarioPorEmail(String email);
    Boolean existeUsuarioPorEmail(String email);
}

