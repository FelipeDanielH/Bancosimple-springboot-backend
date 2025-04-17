package com.softease.BancoSimple.controller;

import com.softease.BancoSimple.dto.TransaccionDTO;
import com.softease.BancoSimple.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacciones")
@RequiredArgsConstructor
public class TransaccionController {

    private final TransaccionService transaccionService;

    @GetMapping
    public ResponseEntity<List<TransaccionDTO>> listar() {
        return ResponseEntity.ok(transaccionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransaccionDTO> obtenerPorId(@PathVariable Integer id) {
        TransaccionDTO dto = transaccionService.obtenerPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TransaccionDTO> crear(@RequestBody TransaccionDTO dto) {
        return ResponseEntity.ok(transaccionService.crear(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        transaccionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
