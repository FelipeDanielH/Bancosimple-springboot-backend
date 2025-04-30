package com.softease.BancoSimple.mapper;

import com.softease.BancoSimple.dto.TarjetasVirtualesDTO;
import com.softease.BancoSimple.dto.tarjetasVirtuales.TarjetaVirtualDetalladaDTO;
import com.softease.BancoSimple.model.Cuenta;
import com.softease.BancoSimple.model.TarjetasVirtuales;

public class TarjetasVirtualesMapper {

    public static TarjetasVirtualesDTO toDTO(TarjetasVirtuales tarjeta) {
        return TarjetasVirtualesDTO.builder()
                .id(tarjeta.getId())
                .cuentaId(tarjeta.getCuentaId() != null ? tarjeta.getCuentaId().getId() : null)
                .cvv(tarjeta.getCvv())
                .estado(tarjeta.getEstado().name())
                .fechaCreacion(tarjeta.getFechaCreacion())
                .fechaExpiracion(tarjeta.getFechaExpiracion())
                .build();
    }

    public static TarjetasVirtuales toEntity(TarjetasVirtualesDTO dto) {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(dto.getCuentaId());

        return TarjetasVirtuales.builder()
                .id(dto.getId())
                .cuentaId(cuenta)
                .cvv(dto.getCvv())
                .estado(TarjetasVirtuales.EstadoTarjeta.valueOf(dto.getEstado()))
                .fechaCreacion(dto.getFechaCreacion())
                .fechaExpiracion(dto.getFechaExpiracion())
                .build();
    }

    public static TarjetaVirtualDetalladaDTO toDetalladaDTO(TarjetasVirtuales entidad) {
        TarjetaVirtualDetalladaDTO dto = new TarjetaVirtualDetalladaDTO();
        dto.setId(entidad.getId());
        dto.setCuentaId(entidad.getCuentaId().getId());
        dto.setNumeroTarjeta(entidad.getCuentaId().getNumeroCuenta());  // nuevo
        dto.setCvv(entidad.getCvv());
        dto.setEstado(entidad.getEstado().name());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setFechaExpiracion(entidad.getFechaExpiracion());
        // extraemos saldo de Cuenta
        dto.setSaldo(entidad.getCuentaId().getSaldo());
        return dto;
    }
}
