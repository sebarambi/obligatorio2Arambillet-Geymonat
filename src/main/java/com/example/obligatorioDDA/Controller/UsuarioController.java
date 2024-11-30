package com.example.obligatorioDDA.Controller;


import com.example.obligatorioDDA.Entity.UsuarioComunEntity;
import com.example.obligatorioDDA.Entity.UsuarioPremiumEntity;
import com.example.obligatorioDDA.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:3000") //aca cambiar cuando hagamos react
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioComunService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioService usuarioPremiumService;

    @PostMapping("/add")
    public ResponseEntity<?> agregarUsuarios(@RequestBody Map<String, Object> requestData) {
        try {
            // Validar que los campos necesarios no sean nulos o vacíos
            if (esNuloOInvalido(requestData.get("nombre")) ||
                    esNuloOInvalido(requestData.get("email")) ||
                    esNuloOInvalido(requestData.get("contrasenia"))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: Todos los campos (Nombre, Email, Contraseña) son obligatorios y no pueden ser nulos o vacíos.");
            }

            // Crear usuario
            UsuarioComunEntity usuario = new UsuarioComunEntity();
            usuario.setNombre((String) requestData.get("nombre"));
            usuario.setEmail((String) requestData.get("email"));
            usuario.setContrasenia((String) requestData.get("contrasenia"));
            usuario.setFechaRegistro(LocalDate.now());

            // Guardar usuario
            UsuarioComunEntity nuevoUsuario = usuarioComunService.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error inesperado: " + e.getMessage());
        }
    }

    private boolean esNuloOInvalido(Object valor) {
        return valor == null || (valor instanceof String && ((String) valor).trim().isEmpty());
    }

    @GetMapping("/allComun")
    public ResponseEntity<List<UsuarioComunEntity>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioComunService.getAllc());
    }

    @GetMapping("/allPremium")
    public ResponseEntity<List<UsuarioPremiumEntity>> getAllUsuariosPremium() {
        return ResponseEntity.ok(usuarioPremiumService.getAllp());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable int id) {
        usuarioComunService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/convertir-a-premium/{id}")
    public ResponseEntity<?> convertirAUsuarioPremium(
            @PathVariable int id, @RequestBody Map<String, Object> requestData) {
        try {
            String fechaMembresia = (String) requestData.get("fechaMembresia");
            UsuarioPremiumEntity usuarioPremium = usuarioComunService.convertirAUsuarioPremium(id, fechaMembresia);
            return ResponseEntity.ok(usuarioPremium);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/convertir-a-comun/{id}")
    public ResponseEntity<?> convertirAUsuarioComun(@PathVariable int id) {
        try {
            UsuarioComunEntity usuarioComun = usuarioService.convertirAUsuarioComun(id);
            return ResponseEntity.ok(usuarioComun);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
