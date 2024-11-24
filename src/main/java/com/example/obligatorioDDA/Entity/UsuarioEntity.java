package com.example.obligatorioDDA.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Usuarios")
public abstract class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column(unique = true)
    private String email;

    @Column
    private Date fechaRegistro;

    @OneToMany(mappedBy = "usuarioEntity", cascade = CascadeType.ALL)
    private List<VentaEntity> compras;

    @Column
    private String contrasenia;


    //Getters and Setters
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<VentaEntity> getCompras() {
        return compras;
    }

    public void setCompras(List<VentaEntity> compras) {
        this.compras = compras;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }


    //Constructor


    public UsuarioEntity(int id, String nombre, String email, String contrasenia) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        fechaRegistro = new Date();
        compras = new ArrayList<VentaEntity>();
        this.contrasenia = contrasenia;
    }

    public UsuarioEntity() {
    }
}


