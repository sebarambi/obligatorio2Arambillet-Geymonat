package com.example.obligatorioDDA.Entity;

import jakarta.persistence.Column;

import java.util.Date;

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

    public UsuarioPremiumEntity(String nombre, String email, Date fechaRegistro, String contrasenia, Date fechaMembresia) {
        super(nombre, email, fechaRegistro, contrasenia);
        this.fechaMembresia = fechaMembresia;
        setEsPremium(true);
    }
}
