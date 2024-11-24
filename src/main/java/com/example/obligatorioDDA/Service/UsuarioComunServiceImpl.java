package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.Entity.UsuarioComunEntity;
import com.example.obligatorioDDA.Repository.UsuarioComunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioComunServiceImpl implements UsuarioComunService {

    @Autowired
    private UsuarioComunRepository usuarioComunRepository;

    public UsuarioComunEntity save(UsuarioComunEntity usuarioComunEntity) {
        return usuarioComunRepository.save(usuarioComunEntity);
    }


    public List<UsuarioComunEntity> getAll() {
        return usuarioComunRepository.findAll();
    }
}