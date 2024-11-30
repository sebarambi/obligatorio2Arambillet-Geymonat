package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.Entity.AdministradorEntity;
import com.example.obligatorioDDA.Entity.UsuarioComunEntity;
import com.example.obligatorioDDA.Entity.UsuarioEntity;
import com.example.obligatorioDDA.Entity.UsuarioPremiumEntity;

import java.util.List;
import java.util.Optional;

public interface AdministradorService {
    AdministradorEntity save(AdministradorEntity administradorEntity);
    List<AdministradorEntity> getAllAdministradores();
    Optional<AdministradorEntity> findAdministradorById(int id);
    void delete(int id);
}