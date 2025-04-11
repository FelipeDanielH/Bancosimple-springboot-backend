package com.softease.BancoSimple.service;

import com.softease.BancoSimple.dto.CuentaDTO;

import java.util.List;

public interface CuentaService {
    List<CuentaDTO> obtenerTodas();
    CuentaDTO obtenerPorId(Integer id);
    CuentaDTO crear(CuentaDTO cuentaDTO);
    CuentaDTO actualizar(Integer id, CuentaDTO cuentaDTO);
    void eliminar(Integer id);
}
