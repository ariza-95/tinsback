package com.tutorial.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tins_productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_PRUDUCTOS")
    private int id;
    @Column(name = "DS_PRODUCTO")
    private String descripcion;
    @Column(name = "DS_NOMBRE")
    private String nombre;
    @Column(name = "CD_PRECIO")
    private float precio;
    @Column(name = "CD_CANTIDAD_INICIAL")
    private int cantidadInicial;
    @Column(name = "CD_CANTIDAD_DISPONIBLE")
    private int cantidadDisponible;
    @Column(name = "FCH_INGRESO")
    private Date fchIngreso;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(int cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public Producto(String descripcion, String nombre, float precio, int cantidadInicial, int cantidadDisponible, Date fchIngreso) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadInicial = cantidadInicial;
        this.cantidadDisponible = cantidadDisponible;
        this.fchIngreso = fchIngreso;
    }
    public Producto() {
    }
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Date getFchIngreso() {
        return fchIngreso;
    }

    public void setFchIngreso(Date fchIngreso) {
        this.fchIngreso = fchIngreso;
    }
}
