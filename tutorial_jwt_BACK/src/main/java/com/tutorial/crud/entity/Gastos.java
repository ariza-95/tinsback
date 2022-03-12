package com.tutorial.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tins_gastos")
public class Gastos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_GASTOS")
    private int id;
    @Column(name = "DS_DESCRIPCION")
    private String descripcion;
    @Column(name = "CD_PRECIO")
    private float precio;
    @Column(name = "DSTIPO_EMPLEADO")
    private String tipoEmpleado;
    @Column(name = "FCHPAGO")
    private Date fchPago;
    public Gastos() {
    }
    public Gastos(String descripcion, float precio, String tipoEmpleado, Date fchPago) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipoEmpleado = tipoEmpleado;
        this.fchPago = fchPago;
    }

    public Date getFchPago() {
        return fchPago;
    }

    public void setFchPago(Date fchPago) {
        this.fchPago = fchPago;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
