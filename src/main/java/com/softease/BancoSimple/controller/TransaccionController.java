package com.softease.BancoSimple.controller;

import com.softease.BancoSimple.dto.transacciones.TransaccionDTO;
import com.softease.BancoSimple.dto.transacciones.TransaccionFormDTO;
import com.softease.BancoSimple.dto.transacciones.TransaccionSummaryDTO;
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

    // Rutas personalizadas

    @GetMapping("/{userId}/ultimas")
    public ResponseEntity<List<TransaccionSummaryDTO>> getUltimas(
            @PathVariable Integer userId,
            @RequestParam(name = "limite", defaultValue = "10") int limite) {
        List<TransaccionSummaryDTO> resumen = transaccionService.obtenerUltimasTransacciones(userId, limite);
        return ResponseEntity.ok(resumen);
    }

    @PostMapping("/procesar")
    public ResponseEntity<TransaccionDTO> procesar(@RequestBody TransaccionFormDTO request) {
        TransaccionDTO resultado = transaccionService.procesarTransaccion(request);
        return ResponseEntity.ok(resultado);
    }
}
