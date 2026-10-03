package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Funcion implements Cloneable {
    private final String codigo;
    private final Pelicula pelicula;
    private final Sala sala;
    private final LocalDateTime fechaHora;
    private final double precioEntrada;
    private final List<Asiento> mapaAsientos;
    private EstadoFuncion estado;

    public Funcion(String codigo, Pelicula pelicula, Sala sala, LocalDateTime fechaHora, double precioEntrada) {
        this(codigo, pelicula, sala, fechaHora, precioEntrada, copiarAsientos(sala));
    }

    private Funcion(String codigo, Pelicula pelicula, Sala sala, LocalDateTime fechaHora,
            double precioEntrada, List<Asiento> mapaAsientos) {
        if (codigo == null || codigo.isBlank() || pelicula == null || sala == null || fechaHora == null) {
            throw new ValidacionException("Los datos principales de la funcion son obligatorios");
        }
        if (fechaHora.isBefore(LocalDateTime.now())) {
            throw new ValidacionException("La funcion no puede programarse en una fecha pasada");
        }
        if (precioEntrada <= 0) {
            throw new ValidacionException("El precio de la entrada debe ser mayor que cero");
        }
        this.codigo = codigo;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.precioEntrada = precioEntrada;
        this.mapaAsientos = mapaAsientos;
        this.estado = EstadoFuncion.PROGRAMADA;
    }

    private static List<Asiento> copiarAsientos(Sala sala) {
        if (sala == null)
            return new ArrayList<>();
        List<Asiento> copia = new ArrayList<>();
        for (Asiento asiento : sala.getAsientos()) {
            copia.add(asiento.clone());
        }
        return copia;
    }

    private static List<Asiento> copiarAsientos(List<Asiento> asientos) {
        List<Asiento> copia = new ArrayList<>();
        for (Asiento asiento : asientos) {
            copia.add(asiento.clone());
        }
        return copia;
    }

    @Override
    public Funcion clone() {
        Funcion copia = new Funcion(codigo, pelicula, sala, fechaHora,
                precioEntrada, copiarAsientos(mapaAsientos));
        copia.estado = this.estado;
        return copia;
    }

    public Funcion copiarPara(String nuevoCodigo, LocalDateTime nuevaFechaHora) {
        List<Asiento> copiaMapa = copiarAsientos(mapaAsientos);
        for (Asiento asiento : copiaMapa)
            asiento.liberar();
        return new Funcion(nuevoCodigo, pelicula, sala, nuevaFechaHora, precioEntrada, copiaMapa);
    }

    public Asiento buscarAsiento(String codigoAsiento) {
        for (Asiento asiento : mapaAsientos) {
            if (asiento.getCodigo().equalsIgnoreCase(codigoAsiento))
                return asiento;
        }
        return null;
    }

    public boolean contieneAsiento(Asiento asiento) {
        return mapaAsientos.contains(asiento);
    }

    public LocalDateTime calcularHoraFin() {
        return fechaHora.plusMinutes(pelicula.getDuracionMinutos());
    }

    public void cancelar() {
        estado = EstadoFuncion.CANCELADA;
    }

    public String getCodigo() {
        return codigo;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public EstadoFuncion getEstado() {
        return estado;
    }

    public void setEstado(EstadoFuncion estado) {
        this.estado = estado;
    }

    public List<Asiento> getMapaAsientos() {
        return Collections.unmodifiableList(mapaAsientos);
    }
}
