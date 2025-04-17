package com.softease.BancoSimple.service;


import com.softease.BancoSimple.dto.TransaccionDTO;
import java.util.List;

public interface TransaccionService {
    List<TransaccionDTO> obtenerTodas();
    TransaccionDTO obtenerPorId(Integer id);
    TransaccionDTO crear(TransaccionDTO dto);
    void eliminar(Integer id);
}
