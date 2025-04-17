package com.softease.BancoSimple.service;

import com.softease.BancoSimple.dto.TarjetasVirtualesDTO;

import java.util.List;

public interface TarjetasVirtualesService {
    public List <TarjetasVirtualesDTO> obtenerTodas ();

    TarjetasVirtualesDTO crear(TarjetasVirtualesDTO dto);

}
