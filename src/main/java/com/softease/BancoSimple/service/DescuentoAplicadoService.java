package com.softease.BancoSimple.service;

import com.softease.BancoSimple.dto.DescuentoAplicadoDTO;

import java.util.List;

public interface DescuentoAplicadoService {
    List<DescuentoAplicadoDTO> obtenerTodos();
    DescuentoAplicadoDTO obtenerPorId(Integer id);
    DescuentoAplicadoDTO crear(DescuentoAplicadoDTO dto);
    void eliminar(Integer id);
}
