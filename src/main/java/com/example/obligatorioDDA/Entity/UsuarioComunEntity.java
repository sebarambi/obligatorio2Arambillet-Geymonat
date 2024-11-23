package com.example.obligatorioDDA.Entity;

import java.util.Date;

public class UsuarioComunEntity extends UsuarioEntity {

    //Constructor
    public UsuarioComunEntity(String nombre, String email, Date fechaRegistro, String contrasenia) {
        super(nombre, email, fechaRegistro, contrasenia);
    }

    public UsuarioComunEntity() {
    }
}
