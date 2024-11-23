package com.example.obligatorioDDA.Controller;


import com.example.obligatorioDDA.Entity.UsuarioComunEntity;
import com.example.obligatorioDDA.Service.UsuarioComunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:3000") //aca cambiar cuando hagamos react
@RequestMapping("/usuarios")
public class UsuarioComunController {

    @Autowired
    private UsuarioComunService usuarioComunService;

    @PostMapping
    public ResponseEntity<?> agregarUsuarios(@RequestBody Map<String, Object> requestData){
        UsuarioComunEntity usuario = new UsuarioComunEntity();
        usuario.setNombre((String) requestData.get("nombre"));
        usuario.setEmail((String) requestData.get("email"));
        usuario.setContrasenia((String) requestData.get("contrasenia"));
        usuario.setFechaRegistro((Date) requestData.get("fechaRegistro"));

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
}
