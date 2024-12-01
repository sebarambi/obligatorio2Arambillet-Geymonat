package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.Entity.CategoriaEntity;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    CategoriaEntity save(CategoriaEntity categoria);
    List<CategoriaEntity> getAll();
    Optional<CategoriaEntity> findById(int id);
    void delete(int id);
}
