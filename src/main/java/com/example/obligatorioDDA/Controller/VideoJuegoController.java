package com.example.obligatorioDDA.Controller;

import com.example.obligatorioDDA.Entity.AdministradorEntity;
import com.example.obligatorioDDA.Entity.VideoJuegoEntity;
import com.example.obligatorioDDA.Service.AdministradorService;
import com.example.obligatorioDDA.Service.VideoJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:3000") // Cambiar para producción
@RequestMapping("/videojuegos")

public class VideoJuegoController {

    @Autowired
    private VideoJuegoService videoJuegoService;

    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/add")
    public ResponseEntity<?> agregarVideojuego(@RequestBody Map<String, Object> requestData) {
        try {
            // Validar que los campos necesarios no sean nulos o vacíos
            if (esNuloOInvalido(requestData.get("nombreVideojuego")) ||
                    esNuloOInvalido(requestData.get("descripcion")) ||
                    esNuloOInvalido(requestData.get("precio")) ||
                    esNuloOInvalido(requestData.get("imagen")) ||
                    esNuloOInvalido(requestData.get("stock")) ||
                    esNuloOInvalido(requestData.get("idAdministrador"))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: Todos los campos (nombreVideojuego, descripcion, precio, imagen, stock, idAdministrador) son obligatorios.");
            }

            // Buscar el administrador por ID
            int idAdministrador = Integer.parseInt(requestData.get("idAdministrador").toString());
            Optional<AdministradorEntity> administradorOpt = administradorService.findAdministradorById(idAdministrador);

            if (administradorOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Administrador no encontrado.");
            }

            // Crear videojuego y asociarlo con el administrador
            VideoJuegoEntity videojuego = new VideoJuegoEntity();
            videojuego.setNombreVideojuego((String) requestData.get("nombreVideojuego"));
            videojuego.setDescripcion((String) requestData.get("descripcion"));
            videojuego.setPrecio(Integer.parseInt(requestData.get("precio").toString())); // Convertir a int
            videojuego.setImagen((String) requestData.get("imagen"));
            videojuego.setStock(Integer.parseInt(requestData.get("stock").toString())); // Convertir a int
            videojuego.setAdministrador(administradorOpt.get());

            // Guardar videojuego
            VideoJuegoEntity nuevoVideojuego = videoJuegoService.save(videojuego);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVideojuego);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error inesperado: " + e.getMessage());
        }
    }

    private boolean esNuloOInvalido(Object valor) {
        return valor == null || (valor instanceof String && ((String) valor).trim().isEmpty());
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

    @GetMapping("/stock-menor/{cantidad}")
    public ResponseEntity<?> listarVideojuegosConStockMenor(@PathVariable int cantidad) {
        try {
            List<VideoJuegoEntity> videojuegos = videoJuegoService.findByStockLessThan(cantidad);

            if (videojuegos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay videojuegos con stock menor a " + cantidad);
            }

            return ResponseEntity.ok(videojuegos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar videojuegos con stock menor a " + cantidad + ": " + e.getMessage());
        }
    }
}
