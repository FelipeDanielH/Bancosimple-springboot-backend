package com.softease.BancoSimple.repository;

import com.softease.BancoSimple.model.Cuenta;
import com.softease.BancoSimple.model.Transaccion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
    @Query("""
        SELECT t FROM Transaccion t
         WHERE t.cuentaOrigen.id = :userId
            OR t.cuentaDestino.id = :userId
         ORDER BY t.fechaTransaccion DESC
        """)
    List<Transaccion> findUltimasByUser(@Param("userId") Integer userId, Pageable pageable);

    List<Transaccion> findByCuentaDestino(Cuenta cuentaDestino);
}
