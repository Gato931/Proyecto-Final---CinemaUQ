package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public class DetalleProductoCompra {
    private final Producto producto;
    private final int cantidad;

    public DetalleProductoCompra(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            throw new ValidacionException("Producto y cantidad son obligatorios");
        }
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }
}
