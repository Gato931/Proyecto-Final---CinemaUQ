package co.edu.uniquindio.poo.model;

import java.time.LocalDateTime;

public class MovimientoTarjeta {
    private final TipoMovimientoTarjeta tipo;
    private final double valor;
    private final LocalDateTime fecha;
    private final String descripcion;

    public MovimientoTarjeta(TipoMovimientoTarjeta tipo, double valor,
            LocalDateTime fecha, String descripcion) {
        this.tipo = tipo;
        this.valor = valor;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public TipoMovimientoTarjeta getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
