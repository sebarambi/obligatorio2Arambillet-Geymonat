package com.example.obligatorioDDA.Controller;

import com.example.obligatorioDDA.EntitiesDTOs.VideoJuegoDTO;
import com.example.obligatorioDDA.Entity.AdministradorEntity;
import com.example.obligatorioDDA.Entity.CategoriaEntity;
import com.example.obligatorioDDA.Entity.VideoJuegoEntity;
import com.example.obligatorioDDA.Service.AdministradorService;
import com.example.obligatorioDDA.Service.CategoriaService;
import com.example.obligatorioDDA.Service.VideoJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Cambiar para producción
@RequestMapping("/videojuegos")

public class VideoJuegoController {

    @Autowired
    private VideoJuegoService videoJuegoService;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/add")
    public ResponseEntity<?> agregarVideojuego(@RequestBody VideoJuegoEntity videojuego) {
        try {
            // Validar administrador y categoría
            if (videojuego.getAdministrador() == null || videojuego.getAdministrador().getIdAdministrador() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Administrador no proporcionado.");
            }
            if (videojuego.getCategoria() == null || videojuego.getCategoria().getIdCategoria() == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Categoría no proporcionada.");
            }

            // Verificar existencia del administrador
            Optional<AdministradorEntity> administradorOpt = administradorService.findAdministradorById(videojuego.getAdministrador().getIdAdministrador());
            if (administradorOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado.");
            }

            // Verificar existencia de la categoría
            Optional<CategoriaEntity> categoriaOpt = categoriaService.findCategoriaById(videojuego.getCategoria().getIdCategoria());
            if (categoriaOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada.");
            }

            // Asignar administrador y categoría
            videojuego.setAdministrador(administradorOpt.get());
            videojuego.setCategoria(categoriaOpt.get());

            // Guardar el videojuego
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
    public ResponseEntity<List<VideoJuegoDTO>> getAllVideojuegos() {
        // Obtener lista de entidades
        List<VideoJuegoEntity> videojuegos = videoJuegoService.getAll();

        // Convertir las entidades a DTOs
        List<VideoJuegoDTO> videojuegosDTO = videojuegos.stream()
                .map(videojuego -> new VideoJuegoDTO(
                        videojuego.getIdVideojuego(),
                        videojuego.getNombreVideojuego(),
                        videojuego.getDescripcion(),
                        videojuego.getPrecio(),
                        videojuego.getImagen(),
                        videojuego.getStock(),
                        videojuego.getAdministrador().getIdAdministrador(),
                        videojuego.getCategoria().getIdCategoria()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(videojuegosDTO);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getVideojuegoById(@PathVariable int id) {
        Optional<VideoJuegoEntity> videojuegoOpt = videoJuegoService.findById(id);

        if (videojuegoOpt.isPresent()) {
            VideoJuegoEntity videojuego = videojuegoOpt.get();
            VideoJuegoDTO videojuegoDTO = new VideoJuegoDTO(
                    videojuego.getIdVideojuego(),
                    videojuego.getNombreVideojuego(),
                    videojuego.getDescripcion(),
                    videojuego.getPrecio(),
                    videojuego.getImagen(),
                    videojuego.getStock(),
                    videojuego.getAdministrador().getIdAdministrador(),
                    videojuego.getCategoria().getIdCategoria()
            );
            return ResponseEntity.ok(videojuegoDTO);
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

            // Actualizar campos básicos
            videojuegoActualizado.setNombreVideojuego(videojuego.getNombreVideojuego());
            videojuegoActualizado.setDescripcion(videojuego.getDescripcion());
            videojuegoActualizado.setPrecio(videojuego.getPrecio());
            videojuegoActualizado.setImagen(videojuego.getImagen());
            videojuegoActualizado.setStock(videojuego.getStock());

            // Validar y asignar la categoría
            if (videojuego.getCategoria() != null && videojuego.getCategoria().getIdCategoria() != 0) {
                Optional<CategoriaEntity> categoriaOpt = categoriaService.findCategoriaById(videojuego.getCategoria().getIdCategoria());
                if (categoriaOpt.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Categoría no válida");
                }
                videojuegoActualizado.setCategoria(categoriaOpt.get());
            }

            // Validar y asignar el administrador
            if (videojuego.getAdministrador() != null && videojuego.getAdministrador().getIdAdministrador() != 0) {
                Optional<AdministradorEntity> administradorOpt = administradorService.findAdministradorById(videojuego.getAdministrador().getIdAdministrador());
                if (administradorOpt.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Administrador no válido");
                }
                videojuegoActualizado.setAdministrador(administradorOpt.get());
            }

            VideoJuegoEntity videojuegoGuardado = videoJuegoService.save(videojuegoActualizado);
            return ResponseEntity.ok(videojuegoGuardado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el videojuego: " + e.getMessage());
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
            // Obtener la lista de videojuegos con stock menor a la cantidad
            List<VideoJuegoEntity> videojuegos = videoJuegoService.findByStockLessThan(cantidad);

            if (videojuegos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No hay videojuegos con stock menor a " + cantidad);
            }

            // Convertir las entidades a DTOs
            List<VideoJuegoDTO> videojuegosDTO = videojuegos.stream()
                    .map(videojuego -> new VideoJuegoDTO(
                            videojuego.getIdVideojuego(),
                            videojuego.getNombreVideojuego(),
                            videojuego.getDescripcion(),
                            videojuego.getPrecio(),
                            videojuego.getImagen(),
                            videojuego.getStock(),
                            videojuego.getAdministrador().getIdAdministrador(),
                            videojuego.getCategoria().getIdCategoria()
                    ))
                    .collect(Collectors.toList());

            // Retornar la lista de DTOs
            return ResponseEntity.ok(videojuegosDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar videojuegos con stock menor a " + cantidad + ": " + e.getMessage());
        }
    }

}
