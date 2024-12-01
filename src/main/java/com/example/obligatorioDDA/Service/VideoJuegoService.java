package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.Entity.VideoJuegoEntity;

import java.util.List;
import java.util.Optional;

public interface VideoJuegoService {
    VideoJuegoEntity save(VideoJuegoEntity videojuego);
    List<VideoJuegoEntity> getAll();
    Optional<VideoJuegoEntity> findById(int id);
    void delete(int id);
    List<VideoJuegoEntity> findByStockLessThan(int cantidad);
}
