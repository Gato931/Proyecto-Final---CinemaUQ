package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Combo implements ElementoConfiteria {

    private final String codigo;
    private String nombre;
    private double precio;

    private final List<DetalleCombo> elementos = new ArrayList<>();

    private boolean activo = true;

    public Combo(String codigo, String nombre, double precio) {

        if (codigo == null || codigo.isBlank()
                || nombre == null || nombre.isBlank()
                || precio <= 0) {

            throw new ValidacionException(
                    "Los datos del combo no son validos");
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public void agregarElemento(
            ElementoConfiteria elemento,
            int cantidad) {

        elementos.add(
                new DetalleCombo(elemento, cantidad));
    }

    // Lo conservamos para no romper el código anterior
    public void agregarProducto(
            Producto producto,
            int cantidad) {

        agregarElemento(producto, cantidad);
    }

    @Override
    public boolean tieneStock(int cantidadCombos) {

        if (cantidadCombos <= 0) {
            return false;
        }

        for (DetalleCombo detalle : elementos) {

            int cantidadNecesaria = detalle.getCantidad() * cantidadCombos;

            if (!detalle.getElemento()
                    .tieneStock(cantidadNecesaria)) {

                return false;
            }
        }

        return true;
    }

    // Lo conservamos porque ya existía
    public boolean tieneStockPara(int cantidadCombos) {
        return tieneStock(cantidadCombos);
    }

    @Override
    public void descontarStock(int cantidadCombos) {

        if (!tieneStock(cantidadCombos)) {

            throw new ValidacionException(
                    "No hay stock suficiente para el combo "
                            + nombre);
        }

        for (DetalleCombo detalle : elementos) {

            detalle.getElemento().descontarStock(
                    detalle.getCantidad()
                            * cantidadCombos);
        }
    }

    @Override
    public void acumularStockNecesario(
            Map<Producto, Integer> necesidades,
            int cantidadCombos) {

        for (DetalleCombo detalle : elementos) {

            detalle.getElemento()
                    .acumularStockNecesario(
                            necesidades,
                            detalle.getCantidad()
                                    * cantidadCombos);
        }
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

        if (precio <= 0) {
            throw new ValidacionException(
                    "El precio debe ser mayor que cero");
        }

        this.precio = precio;
    }

    public List<DetalleCombo> getElementos() {
        return Collections.unmodifiableList(elementos);
    }

    @Override
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}