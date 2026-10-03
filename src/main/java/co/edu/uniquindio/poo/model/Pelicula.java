package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public class Pelicula {
    private final String codigo;
    private String titulo;
    private String genero;
    private int duracionMinutos;
    private String clasificacion;
    private boolean activa;

    public Pelicula(String codigo, String titulo, String genero, int duracionMinutos, String clasificacion) {
        if (codigo == null || codigo.isBlank() || titulo == null || titulo.isBlank()) {
            throw new ValidacionException("Codigo y titulo de la pelicula son obligatorios");
        }
        if (duracionMinutos <= 0) {
            throw new ValidacionException("La duracion de la pelicula debe ser mayor que cero");
        }
        this.codigo = codigo;
        this.titulo = titulo;
        this.genero = genero;
        this.duracionMinutos = duracionMinutos;
        this.clasificacion = clasificacion;
        this.activa = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        if (duracionMinutos <= 0)
            throw new ValidacionException("La duracion debe ser mayor que cero");
        this.duracionMinutos = duracionMinutos;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
