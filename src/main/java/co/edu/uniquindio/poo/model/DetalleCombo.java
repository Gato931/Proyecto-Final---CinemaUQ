package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public class DetalleCombo {

    private final ElementoConfiteria elemento;
    private final int cantidad;

    public DetalleCombo(ElementoConfiteria elemento, int cantidad) {
        if (elemento == null || cantidad <= 0) {
            throw new ValidacionException(
                    "Elemento y cantidad del combo son obligatorios");
        }

        this.elemento = elemento;
        this.cantidad = cantidad;
    }

    public ElementoConfiteria getElemento() {
        return elemento;
    }

    public int getCantidad() {
        return cantidad;
    }
}