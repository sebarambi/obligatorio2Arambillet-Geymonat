package com.example.obligatorioDDA.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Categorias")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;

    @Column(unique = true)
    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoJuegoEntity> videojuegos;

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

    public CategoriaEntity(int idCategoria, String nombre, List<VideoJuegoEntity> videojuegos) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.videojuegos = videojuegos;
    }

    public CategoriaEntity() {
    }
}

