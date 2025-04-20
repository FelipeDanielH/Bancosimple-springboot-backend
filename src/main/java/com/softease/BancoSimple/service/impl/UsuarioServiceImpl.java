package com.softease.BancoSimple.service.impl;

import com.softease.BancoSimple.dto.auth.RegisterRequest;
import com.softease.BancoSimple.dto.auth.UsuarioResponseDTO;
import com.softease.BancoSimple.mapper.UsuarioMapper;
import com.softease.BancoSimple.model.Usuario;
import com.softease.BancoSimple.repository.UsuarioRepository;
import com.softease.BancoSimple.service.UsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UsuarioResponseDTO> obtenerTodos() {
        return repository.findAll().stream()
                .map(UsuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO obtenerPorId(Integer id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return UsuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioResponseDTO crear(RegisterRequest request) {
        Usuario usuario = UsuarioMapper.toEntity(request);
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        Usuario guardado = repository.save(usuario);
        return UsuarioMapper.toDto(guardado);
    }

    @Override
    public UsuarioResponseDTO actualizar(Integer id, RegisterRequest request) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        Usuario actualizado = repository.save(usuario);
        return UsuarioMapper.toDto(actualizado);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Boolean existeUsuarioPorEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
    }
}
