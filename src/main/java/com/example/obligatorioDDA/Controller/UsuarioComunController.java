package com.example.obligatorioDDA.Controller;


import com.example.obligatorioDDA.Entity.UsuarioComunEntity;
import com.example.obligatorioDDA.Entity.UsuarioEntity;
import com.example.obligatorioDDA.Service.UsuarioComunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:3000") //aca cambiar cuando hagamos react
@RequestMapping("/usuarios")
public class UsuarioComunController {

    @Autowired
    private UsuarioComunService usuarioComunService;

    @PostMapping("/add")
    public ResponseEntity<?> agregarUsuarios(@RequestBody Map<String, Object> requestData) {
        try {
            // Validar que los campos necesarios no sean nulos o vacíos
            if (esNuloOInvalido(requestData.get("nombre")) ||
                    esNuloOInvalido(requestData.get("email")) ||
                    esNuloOInvalido(requestData.get("contrasenia")) ||
                    esNuloOInvalido(requestData.get("fechaRegistro"))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: Todos los campos (Nombre, Email, Contraseña) son obligatorios y no pueden ser nulos o vacíos.");
            }

            // Crear usuario
            UsuarioComunEntity usuario = new UsuarioComunEntity();
            usuario.setNombre((String) requestData.get("nombre"));
            usuario.setEmail((String) requestData.get("email"));
            usuario.setContrasenia((String) requestData.get("contrasenia"));

            // Intentar parsear la fecha
            usuario.setFechaRegistro(parseFechaRegistro(requestData.get("fechaRegistro")));

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

    private Date parseFechaRegistro(Object fecha) throws Exception {
        if (fecha instanceof String) {
            return new SimpleDateFormat("yyyy-MM-dd").parse((String) fecha);
        } else if (fecha instanceof Date) {
            return (Date) fecha;
        } else {
            throw new IllegalArgumentException("El formato de la fecha debe ser 'yyyy-MM-dd'.");
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<UsuarioComunEntity>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioComunService.getAll());
    }
}
