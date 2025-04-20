package com.softease.BancoSimple.mapper;

import com.softease.BancoSimple.dto.CuentaDTO;
import com.softease.BancoSimple.model.Cuenta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaMapperTest {

    @Test
    void toDTO_ConvierteEntityCorrectamente() {
        Cuenta entity = Cuenta.builder()
                .id(1)
                .tipo(Cuenta.TipoCuenta.ahorro)
                .numeroCuenta("ES123")
                .build();

        CuentaDTO dto = CuentaMapper.toDTO(entity);

        assertAll("Mapeo Entity -> DTO",
                () -> assertEquals("ahorro", dto.getTipo()),
                () -> assertEquals("ES123", dto.getNumeroCuenta())
        );
    }

    @Test
    void toEntity_ConvierteDTOCorrectamente() {
        CuentaDTO dto = CuentaDTO.builder()
                .tipo("corriente")
                .numeroCuenta("ES456")
                .build();

        Cuenta entity = CuentaMapper.toEntity(dto);

        assertAll("Mapeo DTO -> Entity",
                () -> assertEquals(Cuenta.TipoCuenta.corriente, entity.getTipo()),
                () -> assertEquals("ES456", entity.getNumeroCuenta())
        );
    }

    @Test
    void toEntity_TipoInvalido_LanzaExcepcion() {
        CuentaDTO dto = CuentaDTO.builder().tipo("invalido").build();

        assertThrows(IllegalArgumentException.class,
                () -> CuentaMapper.toEntity(dto));
    }
}