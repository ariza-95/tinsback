package com.tutorial.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class ProductoDto {
    private String descripcion;
    @NotBlank
    private String nombre;
    @Min(0)
    private float precio;
    @Min(0)
    private int cantidadInicial;
    @Min(0)
    private int cantidadDisponible;
    private Date fchIngreso;
    public ProductoDto() {
    }

    public ProductoDto(String descripcion, @NotBlank String nombre, @Min(0) float precio, @Min(0) int cantidadInicial, @Min(0) int cantidadDisponible, Date fchIngreso) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadInicial = cantidadInicial;
        this.cantidadDisponible = cantidadDisponible;
        this.fchIngreso = fchIngreso;
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

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public void setCantidadInicial(int cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }


    public Date getFchIngreso() {
        return fchIngreso;
    }

    public void setFchIngreso(Date fchIngreso) {
        this.fchIngreso = fchIngreso;
    }
}
