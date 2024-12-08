package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.EntitiesDTOs.UsuarioComunDTO;
import com.example.obligatorioDDA.EntitiesDTOs.UsuarioDTO;
import com.example.obligatorioDDA.EntitiesDTOs.UsuarioPremiumDTO;
import com.example.obligatorioDDA.Entity.UsuarioComunEntity;
import com.example.obligatorioDDA.Entity.UsuarioEntity;
import com.example.obligatorioDDA.Entity.UsuarioPremiumEntity;
import com.example.obligatorioDDA.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioComunEntity save(UsuarioComunEntity usuarioComunEntity) {
        return usuarioRepository.save(usuarioComunEntity);
    }

    @Override
    public List<UsuarioComunDTO> getAllc() {
        return usuarioRepository.findAll()
                .stream()
                .filter(UsuarioComunEntity.class::isInstance) // Filtra solo los objetos de tipo UsuarioPremiumEntity
                .map(usuarioEntity -> (UsuarioComunEntity) usuarioEntity) // Haz un cast seguro a UsuarioPremiumEntity
                .map(usuarioComunEntity -> new UsuarioComunDTO(
                        usuarioComunEntity.getId(),
                        usuarioComunEntity.getFechaRegistro(),
                        usuarioComunEntity.getEmail(),
                        usuarioComunEntity.getNombre()
                ))
                .toList();
    }

    @Override
    public List<UsuarioPremiumDTO> getAllp() {
        return usuarioRepository.findAll()
                .stream()
                .filter(UsuarioPremiumEntity.class::isInstance) // Filtra solo los objetos de tipo UsuarioPremiumEntity
                .map(usuarioEntity -> (UsuarioPremiumEntity) usuarioEntity) // Haz un cast seguro a UsuarioPremiumEntity
                .map(usuarioPremiumEntity -> new UsuarioPremiumDTO(
                        usuarioPremiumEntity.getId(),
                        usuarioPremiumEntity.getFechaRegistro(),
                        usuarioPremiumEntity.getEmail(),
                        usuarioPremiumEntity.getNombre(),
                        usuarioPremiumEntity.getFechaMembresia()
                ))
                .toList();
    }


    @Override
    public List<UsuarioDTO> getAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioEntity -> new UsuarioDTO(
                        usuarioEntity.getId(),
                        usuarioEntity.getFechaRegistro(), // Convierte LocalDate a String
                        usuarioEntity.getEmail(),
                        usuarioEntity.getNombre()
                ))
                .toList();
    }


    @Override
    public void delete(int id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public UsuarioPremiumEntity convertirAUsuarioPremium(int id, String fechaMembresia, String tarjeta) {
        Optional<UsuarioEntity> usuarioEntityOpt = usuarioRepository.findById(id);

        if (usuarioEntityOpt.isEmpty() || !(usuarioEntityOpt.get() instanceof UsuarioComunEntity)) {
            throw new IllegalArgumentException("El usuario no existe o no es un usuario común.");
        }

        UsuarioComunEntity usuarioComun = (UsuarioComunEntity) usuarioEntityOpt.get();

        // Eliminar el usuario común
        usuarioRepository.delete(usuarioComun);

        // Limpiar el contexto de persistencia
        usuarioRepository.flush();

        // Crear un nuevo UsuarioPremium
        UsuarioPremiumEntity usuarioPremium = new UsuarioPremiumEntity();
        usuarioPremium.setNombre(usuarioComun.getNombre());
        usuarioPremium.setEmail(usuarioComun.getEmail());
        usuarioPremium.setContrasenia(usuarioComun.getContrasenia());
        usuarioPremium.setFechaRegistro(usuarioComun.getFechaRegistro());

        // Validar la tarjeta antes de asignarla
        if (tarjeta == null || tarjeta.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de tarjeta es obligatorio.");
        }
        usuarioPremium.setTarjeta(tarjeta); // Guardar el número de tarjeta

        // Convertir fechaMembresia a LocalDate
        try {
            LocalDate fechaLocalDate = LocalDate.parse(fechaMembresia); // Formato esperado: yyyy-MM-dd
            usuarioPremium.setFechaMembresia(fechaLocalDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fecha inválida. Formato esperado: yyyy-MM-dd.");
        }

        // Guardar como UsuarioPremium
        return usuarioRepository.save(usuarioPremium);
    }


    @Transactional
    public UsuarioComunEntity convertirAUsuarioComun(int id) {
        Optional<UsuarioEntity> usuarioEntityOpt = usuarioRepository.findById(id);

        if (usuarioEntityOpt.isEmpty() || !(usuarioEntityOpt.get() instanceof UsuarioPremiumEntity)) {
            throw new IllegalArgumentException("El usuario no existe o no es un usuario premium.");
        }

        UsuarioPremiumEntity usuarioPremium = (UsuarioPremiumEntity) usuarioEntityOpt.get();

        // Eliminar el usuario premium
        usuarioRepository.delete(usuarioPremium);

        // Limpiar el contexto de persistencia
        usuarioRepository.flush();

        // Crear un nuevo UsuarioComun
        UsuarioComunEntity usuarioComun = new UsuarioComunEntity();
        usuarioComun.setNombre(usuarioPremium.getNombre());
        usuarioComun.setEmail(usuarioPremium.getEmail());
        usuarioComun.setContrasenia(usuarioPremium.getContrasenia());
        usuarioComun.setFechaRegistro(usuarioPremium.getFechaRegistro());
        usuarioComun.setTarjeta(usuarioPremium.getTarjeta());

        // Guardar como UsuarioComun
        return usuarioRepository.save(usuarioComun);
    }

    @Override
    public Optional<UsuarioEntity> findById(int id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public UsuarioEntity save(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    @Override
    public Optional<UsuarioEntity> login(String email, String password) {
        // Buscar usuario por email
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findByEmail(email);

        // Validar si la contraseña coincide
        if (usuarioOpt.isPresent() && usuarioOpt.get().getContrasenia().equals(password)) {
            return usuarioOpt;
        }

        return Optional.empty();
    }

}

