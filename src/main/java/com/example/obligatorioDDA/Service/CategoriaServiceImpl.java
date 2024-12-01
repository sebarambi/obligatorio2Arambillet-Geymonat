package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.Entity.CategoriaEntity;
import com.example.obligatorioDDA.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public CategoriaEntity save(CategoriaEntity categoria) {
        return categoriaRepository.save(categoria);
    }


    public List<CategoriaEntity> getAll() {
        return categoriaRepository.findAll();
    }


    public Optional<CategoriaEntity> findById(int id) {
        return categoriaRepository.findById(id);
    }


    public void delete(int id) {
        categoriaRepository.deleteById(id);
    }
}
