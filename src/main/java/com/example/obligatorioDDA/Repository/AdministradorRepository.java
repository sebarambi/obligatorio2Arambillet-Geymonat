package com.example.obligatorioDDA.Repository;

import com.example.obligatorioDDA.Entity.AdministradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<AdministradorEntity, Integer> {
    Optional<AdministradorEntity> findByEmailAndContrasenia(String email, String contrasenia);
}
