package co.edu.uniquindio.poo.model;

import java.util.Map;

public interface ElementoConfiteria {

    String getNombre();

    double getPrecio();

    boolean isActivo();

    boolean tieneStock(int cantidad);

    void descontarStock(int cantidad);

    void acumularStockNecesario(
            Map<Producto, Integer> necesidades,
            int cantidad);
}