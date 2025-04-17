package com.softease.BancoSimple.repository;

import com.softease.BancoSimple.model.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Integer> {
}

