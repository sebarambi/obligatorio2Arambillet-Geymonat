package com.example.obligatorioDDA.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Categorias")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;

    @Column(unique = true)
    private String nombre;

    //Getters and Setters
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaEntity(String nombre) {
        this.nombre = nombre;
    }
}
