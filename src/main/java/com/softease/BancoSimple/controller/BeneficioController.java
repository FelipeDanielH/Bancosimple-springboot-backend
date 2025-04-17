package com.softease.BancoSimple.controller;


import com.softease.BancoSimple.dto.BeneficioDTO;
import com.softease.BancoSimple.service.BeneficioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficios")
@RequiredArgsConstructor
public class BeneficioController {

    private final BeneficioService beneficioService;

    @GetMapping
    public ResponseEntity<List<BeneficioDTO>> listar() {
        return ResponseEntity.ok(beneficioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeneficioDTO> obtenerPorId(@PathVariable Integer id) {
        BeneficioDTO beneficio = beneficioService.obtenerPorId(id);
        return beneficio != null ? ResponseEntity.ok(beneficio) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BeneficioDTO> crear(@RequestBody BeneficioDTO dto) {
        return ResponseEntity.ok(beneficioService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeneficioDTO> actualizar(@PathVariable Integer id, @RequestBody BeneficioDTO dto) {
        BeneficioDTO actualizado = beneficioService.actualizar(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        beneficioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

