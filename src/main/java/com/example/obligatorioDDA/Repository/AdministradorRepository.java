package com.example.obligatorioDDA.Repository;

import com.example.obligatorioDDA.Entity.AdministradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<AdministradorEntity, Integer> {
}
