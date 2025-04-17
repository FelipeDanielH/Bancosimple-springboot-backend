package com.softease.BancoSimple.service.impl;

import com.softease.BancoSimple.dto.BeneficioDTO;
import com.softease.BancoSimple.mapper.BeneficioMapper;
import com.softease.BancoSimple.model.Beneficio;
import com.softease.BancoSimple.repository.BeneficioRepository;
import com.softease.BancoSimple.service.BeneficioService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeneficioServiceImpl implements BeneficioService {

    private final BeneficioRepository beneficioRepository;

    @Override
    public List<BeneficioDTO> obtenerTodos() {
        return beneficioRepository.findAll()
                .stream()
                .map(BeneficioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BeneficioDTO obtenerPorId(Integer id) {
        return beneficioRepository.findById(id)
                .map(BeneficioMapper::toDTO)
                .orElse(null);
    }

    @Override
    public BeneficioDTO crear(BeneficioDTO dto) {
        Beneficio beneficio = BeneficioMapper.toEntity(dto);
        beneficio.setId(null); // Para evitar conflictos si el DTO trae ID
        return BeneficioMapper.toDTO(beneficioRepository.save(beneficio));
    }

    @Override
    public BeneficioDTO actualizar(Integer id, BeneficioDTO dto) {
        return beneficioRepository.findById(id)
                .map(b -> {
                    b.setDescripcion(dto.getDescripcion());
                    b.setDescuento(dto.getDescuento());
                    b.setActivo(dto.getActivo());
                    return BeneficioMapper.toDTO(beneficioRepository.save(b));
                }).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        beneficioRepository.deleteById(id);
    }
}

