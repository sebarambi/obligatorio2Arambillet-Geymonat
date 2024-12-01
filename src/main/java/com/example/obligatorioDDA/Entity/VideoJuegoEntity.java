package com.example.obligatorioDDA.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Videojuegos")
public class VideoJuegoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVideojuego;

    @Column
    private String nombreVideojuego;

    @Column
    private String descripcion;

    @Column
    private int precio;

    @Column
    private String imagen;

    @Column
    private int stock;

    //Getters and Setters
    public int getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(int idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public String getNombreVideojuego() {
        return nombreVideojuego;
    }

    public void setNombreVideojuego(String nombreVideojuego) {
        this.nombreVideojuego = nombreVideojuego;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    //Constructor

    public VideoJuegoEntity(int idVideojuego, String nombreVideojuego, String descripcion, int precio, String imagen, int stock) {
        this.idVideojuego = idVideojuego;
        this.nombreVideojuego = nombreVideojuego;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.stock = stock;
    }

    public VideoJuegoEntity() {
    }
}
