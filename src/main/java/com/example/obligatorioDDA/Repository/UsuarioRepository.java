package com.example.obligatorioDDA.Repository;


import com.example.obligatorioDDA.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
}
