package com.softease.BancoSimple.service.impl;

import com.softease.BancoSimple.dto.TarjetasVirtualesDTO;
import com.softease.BancoSimple.mapper.TarjetasVirtualesMapper;
import com.softease.BancoSimple.model.TarjetasVirtuales;
import com.softease.BancoSimple.repository.TarjetasVirtualesRepository;
import com.softease.BancoSimple.service.TarjetasVirtualesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TarjetasVirtualesServiceImpl implements TarjetasVirtualesService {

    private final TarjetasVirtualesRepository tarjetasVirtualesRepository;

    @Override
    public List<TarjetasVirtualesDTO> obtenerTodas() {
        List<TarjetasVirtualesDTO> listaTarjetas = tarjetasVirtualesRepository.findAll()
                .stream()
                .map(TarjetasVirtualesMapper::toDTO)
                .collect(Collectors.toList());
        return listaTarjetas;
    }

    @Override
    public TarjetasVirtualesDTO crear(TarjetasVirtualesDTO dto) {
        TarjetasVirtuales entidad = TarjetasVirtualesMapper.toEntity(dto);
        entidad.setFechaCreacion(LocalDateTime.now());
        TarjetasVirtuales guardada = tarjetasVirtualesRepository.save(entidad);
        return TarjetasVirtualesMapper.toDTO(guardada);
    }

}
