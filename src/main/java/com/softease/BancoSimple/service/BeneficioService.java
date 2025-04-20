package com.softease.BancoSimple.service;

import com.softease.BancoSimple.dto.BeneficioDTO;

import java.util.List;

public interface BeneficioService {
    List<BeneficioDTO> obtenerTodos();
    BeneficioDTO obtenerPorId(Integer id);
    BeneficioDTO crear(BeneficioDTO dto);
    BeneficioDTO actualizar(Integer id, BeneficioDTO dto);
    void eliminar(Integer id);
}
