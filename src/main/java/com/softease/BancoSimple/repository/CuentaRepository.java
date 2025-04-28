package com.softease.BancoSimple.repository;

import com.softease.BancoSimple.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    Cuenta findByUsuarioId(Integer usuarioId);
    void deleteByUsuarioId(Integer usuarioId);

    Cuenta findByNumeroCuenta(String numeroCuenta);

}
