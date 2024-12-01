package com.example.obligatorioDDA.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class DetalleVentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    @JsonBackReference // Evita ciclos entre VentaEntity y DetalleVentaEntity
    private VentaEntity ventaEntity;

    @ManyToOne
    @JoinColumn(name = "idVideojuego")
    private VideoJuegoEntity videojuegoEntity;

    @Column
    private int cantidad;

    @Column
    private int precioUnitario;

    public DetalleVentaEntity() {

    }

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

    public VideoJuegoEntity getVideojuegoEntity() {
        return videojuegoEntity;
    }

    public void setVideojuegoEntity(VideoJuegoEntity videojuegoEntity) {
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
    public DetalleVentaEntity(VentaEntity ventaEntity, VideoJuegoEntity videojuegoEntity, int cantidad, int precioUnitario) {
        this.ventaEntity = ventaEntity;
        this.videojuegoEntity = videojuegoEntity;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
}

