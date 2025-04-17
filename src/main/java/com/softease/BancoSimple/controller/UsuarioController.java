package com.softease.BancoSimple.controller;
import com.softease.BancoSimple.dto.auth.RegisterRequest;
import com.softease.BancoSimple.dto.auth.UsuarioResponseDTO;
import com.softease.BancoSimple.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioResponseDTO> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public UsuarioResponseDTO crear(@RequestBody RegisterRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizar(@PathVariable Integer id, @RequestBody RegisterRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}