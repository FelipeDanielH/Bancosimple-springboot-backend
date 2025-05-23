package com.softease.BancoSimple.repository;

import com.softease.BancoSimple.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);

    Boolean existsByEmail(String email);
}