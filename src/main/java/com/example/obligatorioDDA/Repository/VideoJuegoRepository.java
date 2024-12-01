package com.example.obligatorioDDA.Repository;

import com.example.obligatorioDDA.Entity.VideoJuegoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoJuegoRepository extends JpaRepository<VideoJuegoEntity, Integer> {
}
