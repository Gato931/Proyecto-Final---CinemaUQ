package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public class DetalleComboCompra {
    private final Combo combo;
    private final int cantidad;

    public DetalleComboCompra(Combo combo, int cantidad) {
        if (combo == null || cantidad <= 0) {
            throw new ValidacionException("Combo y cantidad son obligatorios");
        }
        this.combo = combo;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        return combo.getPrecio() * cantidad;
    }

    public Combo getCombo() {
        return combo;
    }

    public int getCantidad() {
        return cantidad;
    }
}
