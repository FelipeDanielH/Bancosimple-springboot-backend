package com.softease.BancoSimple.service.impl;

import com.softease.BancoSimple.dto.DescuentoAplicadoDTO;
import com.softease.BancoSimple.mapper.DescuentoAplicadoMapper;
import com.softease.BancoSimple.model.DescuentoAplicado;
import com.softease.BancoSimple.repository.DescuentoAplicadoRepository;
import com.softease.BancoSimple.service.DescuentoAplicadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DescuentoAplicadoServiceImpl implements DescuentoAplicadoService {

    private final DescuentoAplicadoRepository repository;

    @Override
    public List<DescuentoAplicadoDTO> obtenerTodos() {
        return repository.findAll().stream()
                .map(DescuentoAplicadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DescuentoAplicadoDTO obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(DescuentoAplicadoMapper::toDTO)
                .orElse(null);
    }

    @Override
    public DescuentoAplicadoDTO crear(DescuentoAplicadoDTO dto) {
        DescuentoAplicado entity = DescuentoAplicadoMapper.toEntity(dto);
        return DescuentoAplicadoMapper.toDTO(repository.save(entity));
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
