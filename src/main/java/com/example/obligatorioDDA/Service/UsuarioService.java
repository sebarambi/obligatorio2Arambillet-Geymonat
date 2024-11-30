package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.Entity.UsuarioComunEntity;
import com.example.obligatorioDDA.Entity.UsuarioPremiumEntity;

import java.util.List;

public interface UsuarioService {
    UsuarioComunEntity save(UsuarioComunEntity usuarioComunEntity);
    List<UsuarioComunEntity> getAll();
    void delete(int id);

    // Metodo para convertir usuarios
    UsuarioPremiumEntity convertirAUsuarioPremium(int id, String fechaMembresia);
    UsuarioComunEntity convertirAUsuarioComun(int id);
}
