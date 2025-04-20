package com.softease.BancoSimple.service;

import com.softease.BancoSimple.dto.AuditoriaTransaccionDTO;

import java.util.List;

public interface AuditoriaTransaccionService {
    List<AuditoriaTransaccionDTO> obtenerTodas();
    AuditoriaTransaccionDTO obtenerPorId(Integer id);
}
