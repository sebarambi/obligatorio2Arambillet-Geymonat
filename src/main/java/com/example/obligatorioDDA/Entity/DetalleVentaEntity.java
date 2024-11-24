package com.example.obligatorioDDA.Entity;

import jakarta.persistence.*;

@Entity
public class DetalleVentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle;

    @ManyToOne
    @JoinColumn(name = "idVenta")
    private VentaEntity ventaEntity;

    @ManyToOne
    @JoinColumn(name = "idVideojuego")
    private VideojuegoEntity videojuegoEntity;

    @Column
    private int cantidad;

    @Column
    private int precioUnitario;

    // Getters, Setters
    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public VideojuegoEntity getVideojuegoEntity() {
        return videojuegoEntity;
    }

    public void setVideojuegoEntity(VideojuegoEntity videojuegoEntity) {
        this.videojuegoEntity = videojuegoEntity;
    }

    public VentaEntity getVentaEntity() {
        return ventaEntity;
    }

    public void setVentaEntity(VentaEntity ventaEntity) {
        this.ventaEntity = ventaEntity;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    // Constructor
    public DetalleVentaEntity(int idDetalle, VentaEntity ventaEntity, VideojuegoEntity videojuegoEntity, int cantidad, int precioUnitario) {
        this.idDetalle = idDetalle;
        this.ventaEntity = ventaEntity;
        this.videojuegoEntity = videojuegoEntity;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
}

