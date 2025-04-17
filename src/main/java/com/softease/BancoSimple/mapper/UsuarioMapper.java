package com.softease.BancoSimple.mapper;
import com.softease.BancoSimple.dto.auth.RegisterRequest;
import com.softease.BancoSimple.dto.auth.UsuarioResponseDTO;
import com.softease.BancoSimple.model.Usuario;

public class UsuarioMapper {

    public static UsuarioResponseDTO toDto(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }

    public static Usuario toEntity(RegisterRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        return usuario;
    }
}

