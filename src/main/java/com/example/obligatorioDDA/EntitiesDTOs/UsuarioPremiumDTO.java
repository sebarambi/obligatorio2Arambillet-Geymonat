package com.example.obligatorioDDA.EntitiesDTOs;

import java.time.LocalDate;

public class UsuarioPremiumDTO {
    private int id;
    private String nombre;
    private String email;
    private LocalDate fechaRegistro;
    private LocalDate fechaMembresia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaMembresia() {
        return fechaMembresia;
    }

    public void setFechaMembresia(LocalDate fechaMembresia) {
        this.fechaMembresia = fechaMembresia;
    }

    public UsuarioPremiumDTO(int id, LocalDate fechaRegistro, String email, String nombre, LocalDate fechaMembresia) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.email = email;
        this.nombre = nombre;
        this.fechaMembresia = fechaMembresia;
    }
}
