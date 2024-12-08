package com.example.obligatorioDDA.Repository;

import com.example.obligatorioDDA.Entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public interface VentaRepository extends JpaRepository<VentaEntity, Integer> {
    List<VentaEntity> findByFechaDeVentaBetween(Date fechaInicio, Date fechaFin);
}
