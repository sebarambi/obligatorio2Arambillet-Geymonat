package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.Entity.*;

import java.util.List;
import java.util.Optional;

public interface AdministradorService {
    AdministradorEntity save(AdministradorEntity administradorEntity);
    List<AdministradorEntity> getAllAdministradores();
    Optional<AdministradorEntity> findAdministradorById(int id);
    Optional<AdministradorEntity> findByEmailAndContrasenia(String email, String password);
    void delete(int id);
}