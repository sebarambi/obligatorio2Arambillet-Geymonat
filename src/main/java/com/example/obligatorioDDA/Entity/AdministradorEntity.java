package com.example.obligatorioDDA.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Administradores")
public class AdministradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdministrador;

    @Column
    private String nombre;

    @Column(unique = true)
    private String email;

    @Column
    private String contrasenia;

    @Column
    private String fechaRegistro;

    //Getters and Setters
    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    //Constructor
    public AdministradorEntity(int idAdministrador, String nombre, String email, String contrasenia, String fechaRegistro) {
        this.idAdministrador = idAdministrador;
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.fechaRegistro = fechaRegistro;
    }
}
