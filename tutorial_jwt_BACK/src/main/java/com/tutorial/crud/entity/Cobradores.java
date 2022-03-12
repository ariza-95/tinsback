package com.tutorial.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tins_cobradores")
public class Cobradores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_COBRADOR")
    private int id;
    @Column(name = "DS_NOMBRE")
    private String nombre;
    @Column(name = "DS_CEDULA")
    private String cedula;
    @Column(name = "DS_TELEFONO")
    private String telefono;
    @Column(name = "DS_SALDO")
    private float saldo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CD_RUTA")
    private Rutas rutas;

    public Cobradores(){

    }
    public Cobradores(String nombre, String cedula, String telefono, float saldo, Rutas rutas) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.saldo = saldo;
        this.rutas = rutas;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Rutas getRutas() {
        return rutas;
    }

    public void setRutas(Rutas rutas) {
        this.rutas = rutas;
    }
}
