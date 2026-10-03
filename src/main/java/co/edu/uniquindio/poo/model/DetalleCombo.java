package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public class DetalleCombo {
    private final Producto producto;
    private final int cantidad;

    public DetalleCombo(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            throw new ValidacionException("Producto y cantidad del combo son obligatorios");
        }
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }
}
