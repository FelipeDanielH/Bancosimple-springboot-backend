package com.softease.BancoSimple.controller;

import com.softease.BancoSimple.dto.CuentaDTO;
import com.softease.BancoSimple.dto.cuenta.CuentaResumenDTO;
import com.softease.BancoSimple.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        CuentaDTO cuenta = cuentaService.obtenerPorId(id);

        if (cuenta == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Cuenta no encontrada con ID: " + id
            );
        }

        return cuenta;
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

    @GetMapping("/mia/{id}")
    public CuentaResumenDTO obtenerResumenPorId(@PathVariable Integer id) {
        return cuentaService.obtenerCuentaPorUsuario(id);
    }
}
