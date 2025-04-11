package com.softease.BancoSimple.service;

import com.softease.BancoSimple.dto.RecargaDTO;

import java.util.List;

public interface RecargaService {
    RecargaDTO crearRecarga(RecargaDTO dto);
    List<RecargaDTO> listarRecargas();
    RecargaDTO obtenerRecargaPorId(Integer id);
}
