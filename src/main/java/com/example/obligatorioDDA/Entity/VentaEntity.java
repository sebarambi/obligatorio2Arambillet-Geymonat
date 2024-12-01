package com.example.obligatorioDDA.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Ventas")
public class VentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVenta;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private UsuarioEntity usuarioEntity;

    @OneToMany(mappedBy = "ventaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVentaEntity> listaDetalles;

    @Column
    private Date fechaDeVenta;

    @Column
    private int montoTotal;

    public VentaEntity() {

    }

    //Getters and Setters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public Date getFechaDeVenta() {
        return fechaDeVenta;
    }

    public void setFechaDeVenta(Date fechaDeVenta) {
        this.fechaDeVenta = fechaDeVenta;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<DetalleVentaEntity> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleVentaEntity> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    //Constructor
    public VentaEntity(int idVenta, UsuarioEntity usuarioEntity, Date fechaDeVenta, int montoTotal, List<DetalleVentaEntity> listaDetalles) {
        this.idVenta = idVenta;
        this.usuarioEntity = usuarioEntity;
        this.fechaDeVenta = fechaDeVenta;
        this.montoTotal = montoTotal;
        this.listaDetalles = listaDetalles;
    }
}
