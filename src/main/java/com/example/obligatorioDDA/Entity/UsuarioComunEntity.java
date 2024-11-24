package com.example.obligatorioDDA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;
@Entity
public class UsuarioComunEntity extends UsuarioEntity {

    //Constructor


    public UsuarioComunEntity(int id, String nombre, String email, String contrasenia) {
        super(id, nombre, email, contrasenia);
    }

    public UsuarioComunEntity() {
    }
}
