package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public class Asiento implements Cloneable {
    private final String codigo;
    private final String fila;
    private final int numero;
    private boolean activo;
    private boolean ocupado;

    public Asiento(String codigo, String fila, int numero) {
        if (codigo == null || codigo.isBlank() || fila == null || fila.isBlank() || numero <= 0) {
            throw new ValidacionException("Los datos del asiento no son validos");
        }
        this.codigo = codigo;
        this.fila = fila;
        this.numero = numero;
        this.activo = true;
        this.ocupado = false;
    }

    public boolean estaDisponible() {
        return activo && !ocupado;
    }

    public void ocupar() {
        if (!estaDisponible()) {
            throw new ValidacionException("El asiento " + this + " no esta disponible");
        }
        ocupado = true;
    }

    public void liberar() {
        ocupado = false;
    }

    @Override
    public Asiento clone() {
        Asiento copia = new Asiento(codigo, fila, numero);
        copia.activo = this.activo;
        copia.ocupado = this.ocupado;
        return copia;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getFila() {
        return fila;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    @Override
    public String toString() {
        return fila + numero;
    }
}
