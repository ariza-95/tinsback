package com.tutorial.crud.dto;

import com.tutorial.crud.entity.Cobradores;
import com.tutorial.crud.entity.Rutas;

public class EstadisticasCobradorDto {
    private Cobradores cobradores;
    private double totalCobros;
    private double totalCobrado;

    public EstadisticasCobradorDto(){

    }
    public Cobradores getCobradores() {
        return cobradores;
    }

    public void setCobradores(Cobradores cobradores) {
        this.cobradores = cobradores;
    }

    public double getTotalCobros() {
        return totalCobros;
    }

    public void setTotalCobros(double totalCobros) {
        this.totalCobros = totalCobros;
    }

    public double getTotalCobrado() {
        return totalCobrado;
    }

    public void setTotalCobrado(double totalCobrado) {
        this.totalCobrado = totalCobrado;
    }

    public EstadisticasCobradorDto(Cobradores cobradores, double totalCobros, double totalCobrado) {
        this.cobradores = cobradores;
        this.totalCobros = totalCobros;
        this.totalCobrado = totalCobrado;
    }
}
