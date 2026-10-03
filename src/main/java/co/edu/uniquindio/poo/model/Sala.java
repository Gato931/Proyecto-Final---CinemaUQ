package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sala {
    private final String codigo;
    private String nombre;
    private final List<Asiento> asientos = new ArrayList<>();
    private boolean activa = true;

    public Sala(String codigo, String nombre) {
        if (codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank()) {
            throw new ValidacionException("Codigo y nombre de la sala son obligatorios");
        }
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public void agregarAsiento(Asiento asiento) {
        if (asiento == null) {
            throw new ValidacionException("El asiento es obligatorio");
        }
        if (buscarAsiento(asiento.getCodigo()) != null) {
            throw new ValidacionException("Ya existe un asiento con ese codigo en la sala");
        }
        asientos.add(asiento);
    }

    public Asiento buscarAsiento(String codigo) {
        for (Asiento asiento : asientos) {
            if (asiento.getCodigo().equalsIgnoreCase(codigo)) {
                return asiento;
            }
        }
        return null;
    }

    public boolean contieneAsiento(Asiento asiento) {
        return asientos.contains(asiento);
    }

    public int getCapacidad() {
        return asientos.size();
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

    public List<Asiento> getAsientos() {
        return Collections.unmodifiableList(asientos);
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
