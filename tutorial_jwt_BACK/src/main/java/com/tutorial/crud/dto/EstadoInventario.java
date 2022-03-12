package com.tutorial.crud.dto;

public enum EstadoInventario {
    ENTREGADO(1, "ENTREGADO"),
    RETIRADO(2, "RETIRADO"),
    PERDIDO(3, "PERDIDO"),
    DEVOLUCION(4, "DEVOLUCION"),
    ENTREGA(5, "ENTREGA"),
    PAGO(6, "PERDIDO"),
    ENCOBRO(7, "DEVOLUCION"),
    NOPAGO(8, "ENTREGA");
    private int id;
    private String nombre;

    private EstadoInventario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {

        return id;
    }

    public String getNombre() {

        return nombre;
    }

    /**
     * Permite obtener el item de esta enumeraci�n que corresponde al identificador que se pasa como par�metro.
     *
     * @param idTipoCuenta Identificador del tipo de cuenta que se requiere obtener.
     * @return Instancia de un item de esta enumeraci�n en caso de que exista uno asociado al identificador recibido,
     * null en cualquier otro caso.
     */
    public static EstadoInventario findById(Integer idTipoCuenta) {

        EstadoInventario aRet = null;

        for (EstadoInventario tipoDoc : EstadoInventario.values()) {
            if (tipoDoc.getId() == idTipoCuenta) {
                aRet = tipoDoc;
                break;
            }
        }

        return aRet;
    }
}
