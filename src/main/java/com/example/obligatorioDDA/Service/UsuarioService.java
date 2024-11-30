package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.Entity.UsuarioComunEntity;
import com.example.obligatorioDDA.Entity.UsuarioEntity;
import com.example.obligatorioDDA.Entity.UsuarioPremiumEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioComunEntity save(UsuarioComunEntity usuarioComunEntity);
    List<UsuarioComunEntity> getAllc();
    List<UsuarioPremiumEntity> getAllp();
    void delete(int id);
    Optional<UsuarioEntity> findById(int id);
    UsuarioEntity save(UsuarioEntity usuarioEntity);

    // Metodo para convertir usuarios
    UsuarioPremiumEntity convertirAUsuarioPremium(int id, String fechaMembresia);
    UsuarioComunEntity convertirAUsuarioComun(int id);
}
