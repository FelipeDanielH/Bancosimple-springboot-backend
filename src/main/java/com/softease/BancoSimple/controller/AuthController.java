package com.softease.BancoSimple.controller;

import com.softease.BancoSimple.dto.auth.AuthRequest;
import com.softease.BancoSimple.dto.auth.AuthResponse;
import com.softease.BancoSimple.dto.auth.RegisterRequest;
import com.softease.BancoSimple.dto.auth.UsuarioResponseDTO;
import com.softease.BancoSimple.model.Usuario;
import com.softease.BancoSimple.security.JwtService;
import com.softease.BancoSimple.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Usuario nuevo = usuarioService.buscarUsuarioPorEmail(request.getEmail()).orElseThrow();
        String jwt = jwtService.generarToken(nuevo);


        AuthResponse response = new AuthResponse();
        response.setToken(jwt);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (usuarioService.existeUsuarioPorEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("El correo ya está en uso.");
        }

        // UsuarioResponseDTO nuevoUsuario = usuarioService.crear(request); // Si queremos mostrar los datos del usuario al crearse, tbn hay que modificar el authcontroller pa eso

        UserDetails userDetails = usuarioService.loadUserByUsername(request.getEmail());
        String jwt = jwtService.generarToken(userDetails);

        AuthResponse response = new AuthResponse();
        response.setToken(jwt);
        return ResponseEntity.ok(response);
    }

}