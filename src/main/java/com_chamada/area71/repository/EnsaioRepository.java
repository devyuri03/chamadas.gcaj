package com_chamada.area71.repository;

import com_chamada.area71.model.Ensaio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EnsaioRepository extends JpaRepository<Ensaio, Long> {

    boolean existsByData(LocalDate date);
}
