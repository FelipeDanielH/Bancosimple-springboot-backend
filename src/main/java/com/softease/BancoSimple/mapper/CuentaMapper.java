package com.softease.BancoSimple.mapper;

import com.softease.BancoSimple.dto.CuentaDTO;
import com.softease.BancoSimple.model.Cuenta;

public class CuentaMapper {

    public static CuentaDTO toDTO(Cuenta cuenta) {
        return CuentaDTO.builder()
                .id(cuenta.getId())
                .usuarioId(cuenta.getUsuarioId())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .tipo(cuenta.getTipo().name())
                .saldo(cuenta.getSaldo())
                .build();
    }

    public static Cuenta toEntity(CuentaDTO dto) {
        return Cuenta.builder()
                .id(dto.getId())
                .usuarioId(dto.getUsuarioId())
                .numeroCuenta(dto.getNumeroCuenta())
                .tipo(Cuenta.TipoCuenta.valueOf(dto.getTipo()))
                .saldo(dto.getSaldo())
                .build();
    }
}
