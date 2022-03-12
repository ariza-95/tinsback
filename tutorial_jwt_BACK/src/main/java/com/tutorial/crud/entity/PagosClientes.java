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
@Table(name = "tins_movimientos_pagos")
public class PagosClientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_PAGO ")
    private int id;
    @Column(name = "VR_PAGO")
    private float valor;
    @Column(name = "FCH_PAGO")
    private Date fchPago;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CD_COBRADORES")
    private Cobradores cobrador;
    @Column(name = "DS_TIPO_MOVIMIENTO")
    private String movimiento;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CD_CLIENTE")
    private Clientes cliente;
    public PagosClientes(){

    }
    public PagosClientes(float valor, Date fchPago, Cobradores cobrador, String movimiento, Clientes cliente) {
        this.valor = valor;
        this.fchPago = fchPago;
        this.cobrador = cobrador;
        this.movimiento = movimiento;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getFchPago() {
        return fchPago;
    }

    public void setFchPago(Date fchPago) {
        this.fchPago = fchPago;
    }

    public Cobradores getCobrador() {
        return cobrador;
    }

    public void setCobrador(Cobradores cobrador) {
        this.cobrador = cobrador;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
}
