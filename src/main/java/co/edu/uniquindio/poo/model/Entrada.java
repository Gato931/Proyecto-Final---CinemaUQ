package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public class Entrada {
    private final String codigo;
    private final Funcion funcion;
    private final Asiento asiento;
    private final double precio;

    public Entrada(String codigo, Funcion funcion, Asiento asiento) {
        if (codigo == null || codigo.isBlank() || funcion == null || asiento == null) {
            throw new ValidacionException("Codigo, funcion y asiento son obligatorios para la entrada");
        }
        if (!funcion.contieneAsiento(asiento)) {
            throw new ValidacionException("El asiento debe pertenecer al mapa de la funcion");
        }
        this.codigo = codigo;
        this.funcion = funcion;
        this.asiento = asiento;
        this.precio = funcion.getPrecioEntrada();
    }

    public String getCodigo() {
        return codigo;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public double getPrecio() {
        return precio;
    }
}
