package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.EntitiesDTOs.UsuarioComunDTO;
import com.example.obligatorioDDA.EntitiesDTOs.UsuarioDTO;
import com.example.obligatorioDDA.EntitiesDTOs.UsuarioPremiumDTO;
import com.example.obligatorioDDA.Entity.UsuarioComunEntity;
import com.example.obligatorioDDA.Entity.UsuarioEntity;
import com.example.obligatorioDDA.Entity.UsuarioPremiumEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioComunEntity save(UsuarioComunEntity usuarioComunEntity);
    List<UsuarioComunDTO> getAllc();
    List<UsuarioPremiumDTO> getAllp();
    List<UsuarioDTO> getAll();
    void delete(int id);
    Optional<UsuarioEntity> findById(int id);
    UsuarioEntity save(UsuarioEntity usuarioEntity);
    Optional<UsuarioEntity> login(String email, String password);

    // Metodo para convertir usuarios
    UsuarioPremiumEntity convertirAUsuarioPremium(int id, String fechaMembresia, String tarjeta);
    UsuarioComunEntity convertirAUsuarioComun(int id);
}
