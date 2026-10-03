package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.time.LocalDate;

public class Promocion {
    private final String codigo;
    private String nombre;
    private double porcentajeDescuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activa;

    public Promocion(String codigo, String nombre, double porcentajeDescuento, LocalDate fechaInicio,
            LocalDate fechaFin) {
        if (codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank()) {
            throw new ValidacionException("Codigo y nombre de la promocion son obligatorios");
        }
        validarDatos(porcentajeDescuento, fechaInicio, fechaFin);
        this.codigo = codigo;
        this.nombre = nombre;
        this.porcentajeDescuento = porcentajeDescuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activa = true;
    }

    private void validarDatos(double porcentaje, LocalDate inicio, LocalDate fin) {
        if (porcentaje < 0 || porcentaje > 100) {
            throw new ValidacionException("El porcentaje debe estar entre 0 y 100");
        }
        if (inicio == null || fin == null || fin.isBefore(inicio)) {
            throw new ValidacionException("Las fechas de la promocion no son validas");
        }
    }

    public boolean estaVigente(LocalDate fecha) {
        return activa && !fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin);
    }

    public double aplicar(double subtotal, LocalDate fecha) {
        if (!estaVigente(fecha))
            return subtotal;
        return subtotal * (1 - porcentajeDescuento / 100.0);
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

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
