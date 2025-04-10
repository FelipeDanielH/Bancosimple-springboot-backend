package com.softease.BancoSimple.mapper;

import com.softease.BancoSimple.dto.TarjetasVirtualesDTO;
import com.softease.BancoSimple.model.Cuenta;
import com.softease.BancoSimple.model.TarjetasVirtuales;

public class TarjetasVirtualesMapper {

    public static TarjetasVirtualesDTO toDTO(TarjetasVirtuales tarjeta) {
        return TarjetasVirtualesDTO.builder()
                .id(tarjeta.getId())
                .cuentaId(tarjeta.getCuentaId() != null ? tarjeta.getCuentaId().getId() : null)
                .numeroTarjeta(tarjeta.getNumeroTarjeta())
                .cvv(tarjeta.getCvv())
                .saldo(tarjeta.getSaldo())
                .estado(tarjeta.getEstado().name())
                .fechaCreacion(tarjeta.getFechaCreacion())
                .fechaExpiracion(tarjeta.getFechaExpiracion())
                .build();
    }

    public static TarjetasVirtuales toEntity(TarjetasVirtualesDTO dto) {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(dto.getCuentaId()); // Sólo seteamos el ID, pa' que JPA no se enoje

        return TarjetasVirtuales.builder()
                .id(dto.getId())
                .cuentaId(cuenta)
                .numeroTarjeta(dto.getNumeroTarjeta())
                .cvv(dto.getCvv())
                .saldo(dto.getSaldo())
                .estado(TarjetasVirtuales.EstadoTarjeta.valueOf(dto.getEstado()))
                .fechaCreacion(dto.getFechaCreacion())
                .fechaExpiracion(dto.getFechaExpiracion())
                .build();
    }
}
