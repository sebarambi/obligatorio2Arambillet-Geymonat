package com.example.obligatorioDDA.Controller;

import com.example.obligatorioDDA.Entity.AdministradorEntity;
import com.example.obligatorioDDA.Service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:3000") // Cambiar para producción
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/add")
    public ResponseEntity<?> agregarAdministrador(@RequestBody Map<String, Object> requestData) {
        try {
            // Validar campos obligatorios
            if (esNuloOInvalido(requestData.get("nombre")) ||
                    esNuloOInvalido(requestData.get("email")) ||
                    esNuloOInvalido(requestData.get("contrasenia"))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: Todos los campos (Nombre, Email, Contraseña) son obligatorios.");
            }

            // Crear administrador
            AdministradorEntity administrador = new AdministradorEntity();
            administrador.setNombre((String) requestData.get("nombre"));
            administrador.setEmail((String) requestData.get("email"));
            administrador.setContrasenia((String) requestData.get("contrasenia"));
            administrador.setFechaRegistro(new Date());

            // Guardar administrador
            AdministradorEntity nuevoAdministrador = administradorService.save(administrador);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAdministrador);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error inesperado: " + e.getMessage());
        }
    }

    private boolean esNuloOInvalido(Object valor) {
        return valor == null || (valor instanceof String && ((String) valor).trim().isEmpty());
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdministradorEntity>> getAllAdministradores() {
        return ResponseEntity.ok(administradorService.getAllAdministradores());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdministrador(@PathVariable int id) {
        administradorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editarAdministrador(@PathVariable int id, @RequestBody Map<String, Object> requestData) {
        try {
            // Buscar el administrador por ID
            Optional<AdministradorEntity> administradorOpt = administradorService.findAdministradorById(id);

            if (administradorOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado.");
            }

            AdministradorEntity administrador = administradorOpt.get();

            // Actualizar los campos según el contenido del mapa
            if (requestData.containsKey("nombre") && requestData.get("nombre") instanceof String) {
                administrador.setNombre((String) requestData.get("nombre"));
            }
            if (requestData.containsKey("email") && requestData.get("email") instanceof String) {
                administrador.setEmail((String) requestData.get("email"));
            }
            if (requestData.containsKey("contrasenia") && requestData.get("contrasenia") instanceof String) {
                administrador.setContrasenia((String) requestData.get("contrasenia"));
            }

            // Guardar los cambios
            AdministradorEntity administradorActualizado = administradorService.save(administrador);
            return ResponseEntity.ok(administradorActualizado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar el administrador: " + e.getMessage());
        }
    }
}
