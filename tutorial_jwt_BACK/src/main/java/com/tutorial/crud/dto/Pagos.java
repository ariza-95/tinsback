package com.tutorial.crud.dto;

public class Pagos {
    private float saldo;
    private float semana;
    private double totalPagar;
    private float devolucion;
    private double totalDeducir;
    private float abono;
    private float otrasDeducir;
    private float totalPrecio;
    public Pagos(float saldo, float semana, double totalPagar, float devolucion, double totalDeducir, float abono, float otrasDeducir, float totalPrecio) {
        this.saldo = saldo;
        this.semana = semana;
        this.totalPagar = totalPagar;
        this.devolucion = devolucion;
        this.totalDeducir = totalDeducir;
        this.abono = abono;
        this.otrasDeducir = otrasDeducir;
        this.totalPrecio = totalPrecio;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getSemana() {
        return semana;
    }

    public void setSemana(float semana) {
        this.semana = semana;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public float getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(float devolucion) {
        this.devolucion = devolucion;
    }

    public double getTotalDeducir() {
        return totalDeducir;
    }

    public void setTotalDeducir(double totalDeducir) {
        this.totalDeducir = totalDeducir;
    }

    public float getAbono() {
        return abono;
    }

    public void setAbono(float abono) {
        this.abono = abono;
    }

    public float getOtrasDeducir() {
        return otrasDeducir;
    }

    public void setOtrasDeducir(float otrasDeducir) {
        this.otrasDeducir = otrasDeducir;
    }

    public float getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(float totalPrecio) {
        this.totalPrecio = totalPrecio;
    }
}
