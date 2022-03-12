package com.tutorial.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tins_clientes")
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_CLIENTE")
    private int id;
    @Column(name = "DS_FACTURA")
    private String codigo;
    @Column(name = "DS_NOMBRE")
    private String nombre;
    @Column(name = "DS_DIRECCION")
    private String direccion;
    @Column(name = "DS_TELEFONO")
    private String telefono;
    @Column(name = "DS_ANTIGUEDAD")
    private String antiguo;
    @Column(name = "DS_ESTADO")
    private String estado;
    @Column(name = "FCH_VENTA")
    private Date fchVenta;
    @Column(name = "FCH_FIN_PAGO")
    private Date fchFinPago;
    @Column(name = "CD_SALDO")
    private float saldo;
    @Column(name = "CD_DEUDA")
    private float deuda;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CD_RUTA")
    private Rutas rutas;

    public Clientes(){

    }
    public Clientes(String codigo, String nombre, String direccion, String telefono, String antiguo, String estado, Date fchVenta, Date fchFinPago, float saldo, float deuda, Rutas rutas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.antiguo = antiguo;
        this.estado = estado;
        this.fchVenta = fchVenta;
        this.fchFinPago = fchFinPago;
        this.saldo = saldo;
        this.deuda = deuda;
        this.rutas = rutas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAntiguo() {
        return antiguo;
    }

    public void setAntiguo(String antiguo) {
        this.antiguo = antiguo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFchVenta() {
        return fchVenta;
    }

    public void setFchVenta(Date fchVenta) {
        this.fchVenta = fchVenta;
    }

    public Date getFchFinPago() {
        return fchFinPago;
    }

    public void setFchFinPago(Date fchFinPago) {
        this.fchFinPago = fchFinPago;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getDeuda() {
        return deuda;
    }

    public void setDeuda(float deuda) {
        this.deuda = deuda;
    }

    public Rutas getRutas() {
        return rutas;
    }

    public void setRutas(Rutas rutas) {
        this.rutas = rutas;
    }
}
