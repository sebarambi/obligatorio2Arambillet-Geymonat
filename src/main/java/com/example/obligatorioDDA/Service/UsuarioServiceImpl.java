package com.example.obligatorioDDA.Service;

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
    public List<UsuarioComunEntity> getAllc() {
        return usuarioRepository.findAll()
                .stream()
                .filter(UsuarioComunEntity.class::isInstance)
                .map(UsuarioComunEntity.class::cast)
                .toList();
    }

    @Override
    public List<UsuarioPremiumEntity> getAllp() {
        return usuarioRepository.findAll()
                .stream()
                .filter(UsuarioPremiumEntity.class::isInstance)
                .map(UsuarioPremiumEntity.class::cast)
                .toList();
    }

    @Override
    public void delete(int id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UsuarioPremiumEntity convertirAUsuarioPremium(int id, String fechaMembresia) {
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

        // Convertir fechaMembresia a LocalDate
        try {
            LocalDate fechaLocalDate = LocalDate.parse(fechaMembresia); // Formato esperado: yyyy-MM-dd
            usuarioPremium.setFechaMembresia(fechaLocalDate); // Asignar directamente como LocalDate
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

        // Guardar como UsuarioComun
        return usuarioRepository.save(usuarioComun);
    }

}

