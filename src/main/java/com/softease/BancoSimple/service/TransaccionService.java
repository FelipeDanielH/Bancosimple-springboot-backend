package com.softease.BancoSimple.service;


import com.softease.BancoSimple.dto.transacciones.TransaccionDTO;
import com.softease.BancoSimple.dto.transacciones.TransaccionFormDTO;
import com.softease.BancoSimple.dto.transacciones.TransaccionSummaryDTO;

import java.util.List;

public interface TransaccionService {
    List<TransaccionDTO> obtenerTodas();
    TransaccionDTO obtenerPorId(Integer id);
    TransaccionDTO crear(TransaccionDTO dto);
    void eliminar(Integer id);
    List<TransaccionSummaryDTO> obtenerUltimasTransacciones(Integer userId, int limite);

    TransaccionDTO procesarTransaccion(TransaccionFormDTO request);
}
