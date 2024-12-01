package com.example.obligatorioDDA.Repository;

import com.example.obligatorioDDA.Entity.VideoJuegoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoJuegoRepository extends JpaRepository<VideoJuegoEntity, Integer> {
    List<VideoJuegoEntity> findByStockLessThan(int cantidad);
}
