package com.example.obligatorioDDA.Repository;


import com.example.obligatorioDDA.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findByEmail(String email);
}
