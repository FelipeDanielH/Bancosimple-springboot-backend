package com.softease.BancoSimple.controller;

import com.softease.BancoSimple.dto.RecargaDTO;
import com.softease.BancoSimple.service.RecargaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recargas")
@RequiredArgsConstructor
public class RecargaController {

    private final RecargaService recargaService;

    @PostMapping
    public ResponseEntity<RecargaDTO> crear(@RequestBody RecargaDTO dto) {
        return ResponseEntity.ok(recargaService.crearRecarga(dto));
    }

    @GetMapping
    public ResponseEntity<List<RecargaDTO>> listar() {
        return ResponseEntity.ok(recargaService.listarRecargas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecargaDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(recargaService.obtenerRecargaPorId(id));
    }
}
