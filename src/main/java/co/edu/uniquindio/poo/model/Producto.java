package co.edu.uniquindio.poo.model;

import java.util.Map;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public class Producto implements ElementoConfiteria {
    private final String codigo;
    private String nombre;
    private double precio;
    private int stock;
    private boolean activo;

    public Producto(String codigo, String nombre, double precio, int stock) {
        if (codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank()) {
            throw new ValidacionException("Codigo y nombre del producto son obligatorios");
        }
        if (precio <= 0 || stock < 0) {
            throw new ValidacionException("Precio o stock del producto no validos");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.activo = true;
    }

    @Override
    public boolean tieneStock(int cantidad) {
        return cantidad > 0 && stock >= cantidad;
    }

    @Override
    public void descontarStock(int cantidad) {
        if (!tieneStock(cantidad)) {
            throw new ValidacionException("Cantidad no disponible para " + nombre);
        }
        stock -= cantidad;
    }

    public void aumentarStock(int cantidad) {
        if (cantidad <= 0)
            throw new ValidacionException("La cantidad debe ser mayor que cero");
        stock += cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio <= 0)
            throw new ValidacionException("El precio debe ser mayor que cero");
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public void acumularStockNecesario(
            Map<Producto, Integer> necesidades,
            int cantidad) {

        necesidades.merge(
                this,
                cantidad,
                Integer::sum);
    }
}
