package com.softease.BancoSimple.controller;

import com.softease.BancoSimple.dto.AuditoriaTransaccionDTO;
import com.softease.BancoSimple.service.AuditoriaTransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditoria-transacciones")
@RequiredArgsConstructor
public class AuditoriaTransaccionController {

    private final AuditoriaTransaccionService auditoriaService;

    @GetMapping
    public ResponseEntity<List<AuditoriaTransaccionDTO>> obtenerTodas() {
        return ResponseEntity.ok(auditoriaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaTransaccionDTO> obtenerPorId(@PathVariable Integer id) {
        AuditoriaTransaccionDTO dto = auditoriaService.obtenerPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }
}
