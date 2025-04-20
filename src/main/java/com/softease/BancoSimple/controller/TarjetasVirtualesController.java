package com.softease.BancoSimple.controller;

import com.softease.BancoSimple.dto.TarjetasVirtualesDTO;
import com.softease.BancoSimple.service.TarjetasVirtualesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarjetas_virtuales")
public class TarjetasVirtualesController {

    @Autowired
    private TarjetasVirtualesService tarjetasVirtualesService;

    @GetMapping
    public List<TarjetasVirtualesDTO> obtenerTodas(){
        return tarjetasVirtualesService.obtenerTodas();
    }

    @PostMapping
    public ResponseEntity<TarjetasVirtualesDTO> crear(@RequestBody TarjetasVirtualesDTO dto) {
        TarjetasVirtualesDTO nueva = tarjetasVirtualesService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }


}
