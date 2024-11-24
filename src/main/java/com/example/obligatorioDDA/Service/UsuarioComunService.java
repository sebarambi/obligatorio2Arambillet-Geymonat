package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.Entity.UsuarioComunEntity;
import com.example.obligatorioDDA.Entity.UsuarioEntity;

import java.util.List;

public interface UsuarioComunService {
    public UsuarioComunEntity save(UsuarioComunEntity usuarioComunEntity);
    List<UsuarioComunEntity> getAll();
}
