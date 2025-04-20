package com.softease.BancoSimple.repository;

import com.softease.BancoSimple.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
}
