package com.softease.BancoSimple.service.impl;

import com.softease.BancoSimple.dto.RecargaDTO;
import com.softease.BancoSimple.mapper.RecargaMapper;
import com.softease.BancoSimple.model.Cuenta;
import com.softease.BancoSimple.model.Recarga;
import com.softease.BancoSimple.repository.CuentaRepository;
import com.softease.BancoSimple.repository.RecargaRepository;
import com.softease.BancoSimple.service.RecargaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecargaServiceImpl implements RecargaService {

    private final RecargaRepository recargaRepository;
    private final CuentaRepository cuentaRepository;

    @Override
    public RecargaDTO crearRecarga(RecargaDTO dto) {
        Cuenta cuenta = cuentaRepository.findById(dto.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        Recarga recarga = RecargaMapper.toEntity(dto);
        recarga.setCuenta(cuenta);
        recarga.setFecha(LocalDateTime.now());

        Recarga guardada = recargaRepository.save(recarga);
        return RecargaMapper.toDTO(guardada);
    }

    @Override
    public List<RecargaDTO> listarRecargas() {
        return recargaRepository.findAll().stream()
                .map(RecargaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RecargaDTO obtenerRecargaPorId(Integer id) {
        Recarga recarga = recargaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recarga no encontrada"));
        return RecargaMapper.toDTO(recarga);
    }
}
