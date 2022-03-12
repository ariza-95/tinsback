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
@Table(name = "tins_movimientos_inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_INVENTARIO")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CD_PRODUCTO ")
    private Producto producto;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CD_VENDEDOR")
    private Vendedor vendedor;
    @Column(name = "FCH_ENTREGA")
    private Date fchEntrega;
    @Column(name = "DS_ESTADO")
    private String estado;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CD_CLIENTE")
    private Clientes cliente;
    @Column(name = "DS_TIPO_MOVIMIENTO")
    private String tipoMovimiento;
    @Column(name = "CD_CANTIDAD")
    private int cantidadProducto;
    @Column(name = "CD_TOTAL")
    private int totalPrecio;
    public Inventario(){

    }

    public Inventario(Producto producto, Vendedor vendedor, Date fchEntrega, String estado, Clientes cliente, String tipoMovimiento, int cantidadProducto, int totalPrecio) {
        this.producto = producto;
        this.vendedor = vendedor;
        this.fchEntrega = fchEntrega;
        this.estado = estado;
        this.cliente = cliente;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidadProducto = cantidadProducto;
        this.totalPrecio = totalPrecio;
    }

    public int getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Date getFchEntrega() {
        return fchEntrega;
    }

    public void setFchEntrega(Date fchEntrega) {
        this.fchEntrega = fchEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public int getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(int totalPrecio) {
        this.totalPrecio = totalPrecio;
    }
}
