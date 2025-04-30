package com.softease.BancoSimple.service;

import com.softease.BancoSimple.dto.TarjetasVirtualesDTO;
import com.softease.BancoSimple.dto.tarjetasVirtuales.TarjetaVirtualDetalladaDTO;

import java.util.List;

public interface TarjetasVirtualesService {
    public List <TarjetasVirtualesDTO> obtenerTodas ();

    TarjetasVirtualesDTO crear(TarjetasVirtualesDTO dto);
    TarjetaVirtualDetalladaDTO obtenerPorCuentaId(Integer cuentaId);
}
