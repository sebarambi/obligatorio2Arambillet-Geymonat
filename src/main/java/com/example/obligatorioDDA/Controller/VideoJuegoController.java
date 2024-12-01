package com.example.obligatorioDDA.Controller;

import com.example.obligatorioDDA.Entity.VideoJuegoEntity;
import com.example.obligatorioDDA.Service.VideoJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:3000") // Cambiar para producción
@RequestMapping("/videojuegos")

public class VideoJuegoController {

    @Autowired
    private VideoJuegoService videoJuegoService;

    @PostMapping("/add")
    public ResponseEntity<?> agregarVideojuego(@RequestBody VideoJuegoEntity videojuego) {
        try {
            VideoJuegoEntity nuevoVideojuego = videoJuegoService.save(videojuego);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVideojuego);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al agregar el videojuego: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<VideoJuegoEntity>> getAllVideojuegos() {
        return ResponseEntity.ok(videoJuegoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVideojuegoById(@PathVariable int id) {
        Optional<VideoJuegoEntity> videojuego = videoJuegoService.findById(id);

        if (videojuego.isPresent()) {
            return ResponseEntity.ok(videojuego.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Videojuego no encontrado");
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editarVideojuego(@PathVariable int id, @RequestBody VideoJuegoEntity videojuego) {
        Optional<VideoJuegoEntity> videojuegoExistente = videoJuegoService.findById(id);

        if (videojuegoExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Videojuego no encontrado");
        }

        try {
            VideoJuegoEntity videojuegoActualizado = videojuegoExistente.get();
            videojuegoActualizado.setNombreVideojuego(videojuego.getNombreVideojuego());
            videojuegoActualizado.setDescripcion(videojuego.getDescripcion());
            videojuegoActualizado.setPrecio(videojuego.getPrecio());
            videojuegoActualizado.setImagen(videojuego.getImagen());
            videojuegoActualizado.setStock(videojuego.getStock());

            VideoJuegoEntity videojuegoGuardado = videoJuegoService.save(videojuegoActualizado);
            return ResponseEntity.ok(videojuegoGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el videojuego: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVideojuego(@PathVariable int id) {
        Optional<VideoJuegoEntity> videojuegoExistente = videoJuegoService.findById(id);

        if (videojuegoExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Videojuego no encontrado");
        }

        try {
            videoJuegoService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar el videojuego: " + e.getMessage());
        }
    }
}
