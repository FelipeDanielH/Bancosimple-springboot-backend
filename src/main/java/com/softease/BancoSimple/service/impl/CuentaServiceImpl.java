package com.softease.BancoSimple.service.impl;

import com.softease.BancoSimple.dto.CuentaDTO;
import com.softease.BancoSimple.mapper.CuentaMapper;
import com.softease.BancoSimple.model.Cuenta;
import com.softease.BancoSimple.repository.CuentaRepository;
import com.softease.BancoSimple.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<CuentaDTO> obtenerTodas() {
        return cuentaRepository.findAll()
                .stream()
                .map(CuentaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaDTO obtenerPorId(Integer id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return CuentaMapper.toDTO(cuenta);
    }

    @Override
    public CuentaDTO crear(CuentaDTO cuentaDTO) {
        Cuenta cuenta = CuentaMapper.toEntity(cuentaDTO);
        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
        return CuentaMapper.toDTO(cuentaGuardada);
    }

    @Override
    public CuentaDTO actualizar(Integer id, CuentaDTO cuentaDTO) {
        Cuenta existente = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        existente.setUsuarioId(cuentaDTO.getUsuarioId());
        existente.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        existente.setTipo(Cuenta.TipoCuenta.valueOf(cuentaDTO.getTipo()));
        existente.setSaldo(cuentaDTO.getSaldo());

        Cuenta actualizada = cuentaRepository.save(existente);
        return CuentaMapper.toDTO(actualizada);
    }

    @Override
    public void eliminar(Integer id) {
        cuentaRepository.deleteById(id);
    }
}
