package com.example.obligatorioDDA.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class UsuarioPremiumEntity extends UsuarioEntity {
    @Column
    private Date fechaMembresia;

    //Getters and Setters
    public Date getFechaMembresia() {
        return fechaMembresia;
    }

    public void setFechaMembresia(Date fechaMembresia) {
        this.fechaMembresia = fechaMembresia;
    }

    //Constructor

    public UsuarioPremiumEntity(int id, String nombre, String email, String contrasenia, Date fechaMembresia) {
        super(id, nombre, email, contrasenia);
        this.fechaMembresia = fechaMembresia;
    }
}
