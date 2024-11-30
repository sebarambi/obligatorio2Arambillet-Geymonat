package com.example.obligatorioDDA.Entity;

import jakarta.persistence.*;

import java.util.Date;

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
    private Date fechaRegistro;

    public AdministradorEntity() {

    }

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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    //Constructor
    public AdministradorEntity(int idAdministrador, String nombre, String email, String contrasenia) {
        this.idAdministrador = idAdministrador;
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        fechaRegistro = new Date();
    }
}
