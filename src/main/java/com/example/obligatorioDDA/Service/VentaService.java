package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.Entity.VentaEntity;

import java.util.List;
import java.util.Optional;

public interface VentaService {
    VentaEntity save(VentaEntity venta);
    List<VentaEntity> getAll();
    Optional<VentaEntity> findById(int id);
    void delete(int id);
}
