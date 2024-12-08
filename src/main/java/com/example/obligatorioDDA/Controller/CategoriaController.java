package com.example.obligatorioDDA.Controller;

import com.example.obligatorioDDA.Entity.CategoriaEntity;
import com.example.obligatorioDDA.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:3000") // Cambiar para producción
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/add")
    public ResponseEntity<?> agregarCategoria(@RequestBody CategoriaEntity categoria) {
        try {
            CategoriaEntity nuevaCategoria = categoriaService.save(categoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al agregar la categoría: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoriaEntity>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable int id) {
        Optional<CategoriaEntity> categoriaExistente = categoriaService.findCategoriaById(id);

        if (categoriaExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada");
        }

        try {
            categoriaService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar la categoría: " + e.getMessage());
        }
    }
}
