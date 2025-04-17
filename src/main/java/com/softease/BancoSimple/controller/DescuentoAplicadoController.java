package com.softease.BancoSimple.controller;

import com.softease.BancoSimple.dto.DescuentoAplicadoDTO;
import com.softease.BancoSimple.service.DescuentoAplicadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/descuentos-aplicados")
@RequiredArgsConstructor
public class DescuentoAplicadoController {

    private final DescuentoAplicadoService service;

    @GetMapping
    public ResponseEntity<List<DescuentoAplicadoDTO>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescuentoAplicadoDTO> obtenerPorId(@PathVariable Integer id) {
        DescuentoAplicadoDTO dto = service.obtenerPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DescuentoAplicadoDTO> crear(@RequestBody DescuentoAplicadoDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

