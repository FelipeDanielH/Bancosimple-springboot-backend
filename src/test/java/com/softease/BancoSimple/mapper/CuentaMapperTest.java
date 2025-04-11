package com.softease.BancoSimple.mapper;

import com.softease.BancoSimple.dto.CuentaDTO;
import com.softease.BancoSimple.model.Cuenta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaMapperTest {

    @Test
    void toDTO_ConvierteEntityCorrectamente() {
        // Arrange
        Cuenta entity = Cuenta.builder()
                .id(1)
                .tipo(Cuenta.TipoCuenta.ahorro)
                .numeroCuenta("ES123")
                .build();

        // Act
        CuentaDTO dto = CuentaMapper.toDTO(entity);

        // Assert
        assertAll("Mapeo Entity -> DTO",
                () -> assertEquals("ahorro", dto.getTipo()),
                () -> assertEquals("ES123", dto.getNumeroCuenta())
        );
    }

    @Test
    void toEntity_ConvierteDTOCorrectamente() {
        // Arrange
        CuentaDTO dto = CuentaDTO.builder()
                .tipo("corriente")
                .numeroCuenta("ES456")
                .build();

        // Act
        Cuenta entity = CuentaMapper.toEntity(dto);

        // Assert
        assertAll("Mapeo DTO -> Entity",
                () -> assertEquals(Cuenta.TipoCuenta.corriente, entity.getTipo()),
                () -> assertEquals("ES456", entity.getNumeroCuenta())
        );
    }

    @Test
    void toEntity_TipoInvalido_LanzaExcepcion() {
        // Arrange
        CuentaDTO dto = CuentaDTO.builder().tipo("invalido").build();

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> CuentaMapper.toEntity(dto));
    }
}
