package com.softease.BancoSimple.service.impl;

import com.softease.BancoSimple.dto.AuditoriaTransaccionDTO;
import com.softease.BancoSimple.mapper.AuditoriaTransaccionMapper;
import com.softease.BancoSimple.repository.AuditoriaTransaccionRepository;
import com.softease.BancoSimple.service.AuditoriaTransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditoriaTransaccionServiceImpl implements AuditoriaTransaccionService {

    private final AuditoriaTransaccionRepository auditoriaRepo;

    @Override
    public List<AuditoriaTransaccionDTO> obtenerTodas() {
        return auditoriaRepo.findAll().stream()
                .map(AuditoriaTransaccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuditoriaTransaccionDTO obtenerPorId(Integer id) {
        return auditoriaRepo.findById(id)
                .map(AuditoriaTransaccionMapper::toDTO)
                .orElse(null);
    }
}

