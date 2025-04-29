package com.softease.BancoSimple.service;

import com.softease.BancoSimple.dto.CuentaDTO;
import com.softease.BancoSimple.dto.cuenta.CuentaResumenDTO;
import com.softease.BancoSimple.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.List;

public interface CuentaService {
    List<CuentaDTO> obtenerTodas();
    CuentaDTO obtenerPorId(Integer id);
    CuentaDTO crear(CuentaDTO cuentaDTO);
    CuentaDTO actualizar(Integer id, CuentaDTO cuentaDTO);
    void eliminar(Integer id);
    CuentaResumenDTO obtenerCuentaPorUsuario(Integer id);
    // <-- Nuevos métodos -->
    /**
     * Resta `monto` del saldo de la cuenta indicada.
     * @throws InsufficientFundsException si no hay saldo suficiente.
     */
    void debitar(Integer cuentaId, BigDecimal monto);

    /**
     * Suma `monto` al saldo de la cuenta indicada.
     */
    void acreditar(Integer cuentaId, BigDecimal monto);

}
