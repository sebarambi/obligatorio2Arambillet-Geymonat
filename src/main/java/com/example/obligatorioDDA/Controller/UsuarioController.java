package com.example.obligatorioDDA.Controller;


import com.example.obligatorioDDA.EntitiesDTOs.UsuarioComunDTO;
import com.example.obligatorioDDA.EntitiesDTOs.UsuarioDTO;
import com.example.obligatorioDDA.EntitiesDTOs.UsuarioPremiumDTO;
import com.example.obligatorioDDA.Entity.UsuarioComunEntity;
import com.example.obligatorioDDA.Entity.UsuarioEntity;
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
import java.util.Optional;

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
    public ResponseEntity<List<UsuarioComunDTO>> getAllUsuariosComun() {
        return ResponseEntity.ok(usuarioComunService.getAllc());
    }

    @GetMapping("/allPremium")
    public ResponseEntity<List<UsuarioPremiumDTO>> getAllUsuariosPremium() {
        return ResponseEntity.ok(usuarioPremiumService.getAllp());
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable int id) {
        try {
            // Buscar el usuario por ID
            Optional<UsuarioEntity> usuarioOpt = usuarioService.findById(id);

            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }

            UsuarioEntity usuario = usuarioOpt.get();

            // Determinar el tipo de usuario
            if (usuario instanceof UsuarioPremiumEntity) {
                UsuarioPremiumEntity usuarioPremium = (UsuarioPremiumEntity) usuario;

                // Crear respuesta con datos específicos del usuario premium
                Map<String, Object> response = Map.of(
                        "id", usuarioPremium.getId(),
                        "nombre", usuarioPremium.getNombre(),
                        "email", usuarioPremium.getEmail(),
                        "tipoUsuario", "Premium"

                );
                return ResponseEntity.ok(response);

            } else if (usuario instanceof UsuarioComunEntity) {
                UsuarioComunEntity usuarioComun = (UsuarioComunEntity) usuario;

                // Crear respuesta con datos específicos del usuario común
                Map<String, Object> response = Map.of(
                        "id", usuarioComun.getId(),
                        "nombre", usuarioComun.getNombre(),
                        "email", usuarioComun.getEmail(),
                        "tipoUsuario", "Común"

                );
                return ResponseEntity.ok(response);
            }

            // Si el tipo de usuario no es reconocido
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Tipo de usuario desconocido.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el usuario: " + e.getMessage());
        }
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
            String tarjeta = (String) requestData.get("tarjeta");

            if (tarjeta == null || tarjeta.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El número de tarjeta es obligatorio.");
            }

            UsuarioPremiumEntity usuarioPremium = usuarioComunService.convertirAUsuarioPremium(id, fechaMembresia, tarjeta);
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

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editarUsuario(@PathVariable int id, @RequestBody Map<String, Object> requestData) {
        try {
            // Buscar el usuario por ID
            Optional<UsuarioEntity> usuarioOpt = usuarioService.findById(id);

            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }

            UsuarioEntity usuario = usuarioOpt.get();

            // Actualizar los campos según el contenido del mapa
            if (requestData.containsKey("nombre") && requestData.get("nombre") instanceof String) {
                usuario.setNombre((String) requestData.get("nombre"));
            }
            if (requestData.containsKey("email") && requestData.get("email") instanceof String) {
                usuario.setEmail((String) requestData.get("email"));
            }
            if (requestData.containsKey("contrasenia") && requestData.get("contrasenia") instanceof String) {
                usuario.setContrasenia((String) requestData.get("contrasenia"));
            }
            if (requestData.containsKey("tarjeta") && requestData.get("tarjeta") instanceof String) {
                usuario.setTarjeta((String) requestData.get("tarjeta")); // Asegúrate de que este campo exista en la entidad
            }

            // Guardar los cambios
            UsuarioEntity usuarioActualizado = usuarioService.save(usuario);
            return ResponseEntity.ok(usuarioActualizado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar el usuario: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/compras")
    public ResponseEntity<?> getComprasByUsuario(@PathVariable int id) {
        try {
            // Buscar el usuario por ID
            Optional<UsuarioEntity> usuarioOpt = usuarioService.findById(id);

            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }

            // Obtener el usuario
            UsuarioEntity usuario = usuarioOpt.get();

            // Devolver la lista de compras
            return ResponseEntity.ok(usuario.getCompras());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener las compras: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        try {
            // Validar campos de entrada
            String email = request.get("email");
            String password = request.get("password");

            if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("status", 400, "message", "Email y contraseña son obligatorios"));
            }

            // Llamar al servicio para autenticar al usuario
            Optional<UsuarioEntity> usuarioOpt = usuarioService.login(email, password);

            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("status", 401, "message", "Credenciales inválidas"));
            }

            // Usuario encontrado
            UsuarioEntity usuario = usuarioOpt.get();
            Map<String, Object> response = Map.of(
                    "status", 200,
                    "message", "User logged in successfully",
                    "data", Map.of(
                            "user", Map.of(
                                    "id", usuario.getId(),
                                    "name", usuario.getNombre(),
                                    "email", usuario.getEmail(),
                                    "password", usuario.getContrasenia(),
                                    "createdAt", usuario.getFechaRegistro()
                            )
                    )
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", 500, "message", "Error en el servidor", "error", e.getMessage()));
        }
    }

}
