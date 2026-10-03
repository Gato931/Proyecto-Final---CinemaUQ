package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combo {
    private final String codigo;
    private String nombre;
    private double precio;
    private final List<DetalleCombo> productos = new ArrayList<>();
    private boolean activo = true;

    public Combo(String codigo, String nombre, double precio) {
        if (codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank() || precio <= 0) {
            throw new ValidacionException("Los datos del combo no son validos");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        productos.add(new DetalleCombo(producto, cantidad));
    }

    public boolean tieneStockPara(int cantidadCombos) {
        if (cantidadCombos <= 0)
            return false;
        for (DetalleCombo detalle : productos) {
            int cantidadNecesaria = detalle.getCantidad() * cantidadCombos;
            if (!detalle.getProducto().tieneStock(cantidadNecesaria)) {
                return false;
            }
        }
        return true;
    }

    public void descontarStock(int cantidadCombos) {
        if (!tieneStockPara(cantidadCombos)) {
            throw new ValidacionException("No hay stock suficiente para el combo " + nombre);
        }
        for (DetalleCombo detalle : productos) {
            detalle.getProducto().descontarStock(detalle.getCantidad() * cantidadCombos);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio <= 0)
            throw new ValidacionException("El precio debe ser mayor que cero");
        this.precio = precio;
    }

    public List<DetalleCombo> getProductos() {
        return Collections.unmodifiableList(productos);
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
