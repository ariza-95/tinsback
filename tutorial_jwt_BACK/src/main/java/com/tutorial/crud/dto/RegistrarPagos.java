package com.tutorial.crud.dto;

public class RegistrarPagos {
    private FechasDto fecha;
    private Pagos pagos;

    public FechasDto getFecha() {
        return fecha;
    }

    public void setFecha(FechasDto fecha) {
        this.fecha = fecha;
    }

    public Pagos getPagos() {
        return pagos;
    }

    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
    }

    public RegistrarPagos(FechasDto fecha, Pagos pagos) {
        this.fecha = fecha;
        this.pagos = pagos;
    }
}
