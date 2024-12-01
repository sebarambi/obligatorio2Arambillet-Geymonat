package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.Entity.VideoJuegoEntity;
import com.example.obligatorioDDA.Repository.VideoJuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoJuegoServiceImpl implements VideoJuegoService {

    @Autowired
    private VideoJuegoRepository videojuegoRepository;

    public VideoJuegoEntity save(VideoJuegoEntity videojuego) {
        return videojuegoRepository.save(videojuego);
    }

    public List<VideoJuegoEntity> getAll() {
        return videojuegoRepository.findAll();
    }

    public Optional<VideoJuegoEntity> findById(int id) {
        return videojuegoRepository.findById(id);
    }

    public void delete(int id) {
        videojuegoRepository.deleteById(id);
    }

    public List<VideoJuegoEntity> findByStockLessThan(int cantidad) {
        return videojuegoRepository.findByStockLessThan(cantidad);
    }
}
