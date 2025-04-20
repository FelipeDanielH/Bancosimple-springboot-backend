package com.softease.BancoSimple.service.impl;

import com.softease.BancoSimple.dto.TransaccionDTO;
import com.softease.BancoSimple.mapper.TransaccionMapper;
import com.softease.BancoSimple.model.Transaccion;
import com.softease.BancoSimple.repository.TransaccionRepository;
import com.softease.BancoSimple.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;

    @Override
    public List<TransaccionDTO> obtenerTodas() {
        return transaccionRepository.findAll().stream()
                .map(TransaccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransaccionDTO obtenerPorId(Integer id) {
        return transaccionRepository.findById(id)
                .map(TransaccionMapper::toDTO)
                .orElse(null);
    }

    @Override
    public TransaccionDTO crear(TransaccionDTO dto) {
        Transaccion entidad = TransaccionMapper.toEntity(dto);
        return TransaccionMapper.toDTO(transaccionRepository.save(entidad));
    }

    @Override
    public void eliminar(Integer id) {
        transaccionRepository.deleteById(id);
    }
}
