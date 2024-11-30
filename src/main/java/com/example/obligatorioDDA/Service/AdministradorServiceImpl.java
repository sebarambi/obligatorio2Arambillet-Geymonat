package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.Entity.AdministradorEntity;
import com.example.obligatorioDDA.Entity.UsuarioComunEntity;
import com.example.obligatorioDDA.Entity.UsuarioEntity;
import com.example.obligatorioDDA.Entity.UsuarioPremiumEntity;
import com.example.obligatorioDDA.Repository.AdministradorRepository;
import com.example.obligatorioDDA.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public AdministradorEntity save(AdministradorEntity administradorEntity) {
        return administradorRepository.save(administradorEntity);
    }

    public List<AdministradorEntity> getAllAdministradores() {
        return administradorRepository.findAll()
                .stream()
                .filter(AdministradorEntity.class::isInstance)
                .map(AdministradorEntity.class::cast)
                .toList();
    }

    public void delete(int id) {
        administradorRepository.deleteById(id);
    }

    public Optional<AdministradorEntity> findAdministradorById(int id) {
        return administradorRepository.findById(id);
    }
}