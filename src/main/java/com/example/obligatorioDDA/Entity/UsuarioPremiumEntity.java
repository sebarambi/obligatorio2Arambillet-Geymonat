package com.example.obligatorioDDA.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios_premium")
public class UsuarioPremiumEntity extends UsuarioEntity {
    @Column
    private LocalDate fechaMembresia;

    public UsuarioPremiumEntity() {

    }

    //Getters and Setters
    public LocalDate getFechaMembresia() {
        return fechaMembresia;
    }

    public void setFechaMembresia(LocalDate fechaMembresia) {
        this.fechaMembresia = fechaMembresia;
    }

    //Constructor
    public UsuarioPremiumEntity(int id, String nombre, String email, String contrasenia, LocalDate fechaMembresia) {
        super(id, nombre, email, contrasenia);
        this.fechaMembresia = fechaMembresia;
    }
}
