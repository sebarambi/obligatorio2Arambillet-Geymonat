package com.example.obligatorioDDA.EntitiesDTOs;

public class VideoJuegoDTO {
    private  int idVideojuego;
    private String nombreVideojuego;
    private String descripcion;
    private int precio;
    private String imagen;
    private int stock;
    private int idAdministrador;
    private int idCategoria;

    public VideoJuegoDTO(int idVideojuego, String nombreVideojuego, String descripcion, int precio) {
    }

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

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public VideoJuegoDTO(int idVideojuego, String nombreVideojuego, String descripcion, int precio, String imagen, int stock, int idAdministrador, int idCategoria) {
        this.idVideojuego = idVideojuego;
        this.nombreVideojuego = nombreVideojuego;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.stock = stock;
        this.idAdministrador = idAdministrador;
        this.idCategoria = idCategoria;
    }
}

