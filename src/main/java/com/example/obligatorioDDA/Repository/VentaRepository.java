package com.example.obligatorioDDA.Repository;

import com.example.obligatorioDDA.Entity.VentaEntity;
import com.example.obligatorioDDA.Entity.VideoJuegoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<VentaEntity, Integer> {
}
