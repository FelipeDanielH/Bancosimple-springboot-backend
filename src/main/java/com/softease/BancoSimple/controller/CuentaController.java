package com.softease.BancoSimple.controller;

import com.softease.BancoSimple.dto.CuentaDTO;
import com.softease.BancoSimple.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<CuentaDTO> obtenerTodas() {
        return cuentaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public CuentaDTO obtenerPorId(@PathVariable Integer id) {
        return cuentaService.obtenerPorId(id);
    }

    @PostMapping
    public CuentaDTO crear(@RequestBody CuentaDTO cuentaDTO) {
        return cuentaService.crear(cuentaDTO);
    }

    @PutMapping("/{id}")
    public CuentaDTO actualizar(@PathVariable Integer id, @RequestBody CuentaDTO cuentaDTO) {
        return cuentaService.actualizar(id, cuentaDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        cuentaService.eliminar(id);
    }
}
