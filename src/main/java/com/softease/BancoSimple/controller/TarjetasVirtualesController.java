package com.softease.BancoSimple.controller;

import com.softease.BancoSimple.dto.TarjetasVirtualesDTO;
import com.softease.BancoSimple.service.TarjetasVirtualesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
