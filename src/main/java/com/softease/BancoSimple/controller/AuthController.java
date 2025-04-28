package com.softease.BancoSimple.controller;

import com.softease.BancoSimple.dto.CuentaDTO;
import com.softease.BancoSimple.dto.auth.*;
import com.softease.BancoSimple.model.Usuario;
import com.softease.BancoSimple.security.JwtService;
import com.softease.BancoSimple.service.CuentaService;
import com.softease.BancoSimple.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CuentaService cuentaService;
    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // 1) Autenticar credenciales
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(auth);

            // 2) Obtener Usuario desde Optional o lanzar excepción si no existe
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException(
                            "No se encontró un usuario con email: " + request.getEmail()
                    ));

            // 3) Crear los claims para el JWT
            Map<String, Object> claims = Map.of(
                    "id",             usuario.getId(),
                    "nombre",         usuario.getNombre(),
                    "email",          usuario.getEmail(),
                    "telefono",       usuario.getTelefono(),
                    "direccion",      usuario.getDireccion(),
                    "fecha_registro", usuario.getFechaRegistro().toString()
            );

            // 4) Generar token
            UserDetails userDetails = usuarioService.loadUserByUsername(usuario.getEmail());
            String token = jwtService.generarToken(claims, userDetails);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas");
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ex.getMessage());
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (usuarioService.existeUsuarioPorEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("El correo ya está en uso.");
        }

        // Crear y guardar usuario
        UsuarioResponseDTO usuario = usuarioService.crear(request);

        // 2. Crear cuenta asociada
        CuentaDTO nuevaCuenta = new CuentaDTO();
        nuevaCuenta.setUsuarioId(usuario.getId());
        nuevaCuenta.setTipo("corriente");
        nuevaCuenta.setSaldo(BigDecimal.valueOf(100000000));
        nuevaCuenta.setNumeroCuenta(generarNumeroCuenta());

        cuentaService.crear(nuevaCuenta);


        Map<String, Object> claims = new HashMap<>();
        claims.put("id", usuario.getId());
        claims.put("nombre", usuario.getNombre());
        claims.put("email", usuario.getEmail());
        claims.put("telefono", usuario.getTelefono());
        claims.put("direccion", usuario.getDireccion());
        claims.put("fecha_registro", usuario.getFechaRegistro().toString());

        // UsuarioResponseDTO nuevoUsuario = usuarioService.crear(request); // Si queremos mostrar los datos del usuario al crearse, tbn hay que modificar el authcontroller pa eso

        // Generar token con claims
        UserDetails userDetails = usuarioService.loadUserByUsername(usuario.getEmail());
        String token = jwtService.generarToken(claims, userDetails);

        return ResponseEntity.ok(Map.of("token", token));
    }

    private String generarNumeroCuenta() {
        // Generar número de cuenta aleatorio de 12 dígitos
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}