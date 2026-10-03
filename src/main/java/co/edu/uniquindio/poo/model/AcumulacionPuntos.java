package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.time.LocalDate;

public class AcumulacionPuntos {
    private int puntosDisponibles;
    private final LocalDate fechaObtencion;
    private final LocalDate fechaVencimiento;

    public AcumulacionPuntos(int puntos, LocalDate fechaObtencion) {
        if (puntos <= 0) {
            throw new ValidacionException("Los puntos acumulados deben ser mayores que cero");
        }
        if (fechaObtencion == null) {
            throw new ValidacionException("La fecha de obtencion es obligatoria");
        }
        this.puntosDisponibles = puntos;
        this.fechaObtencion = fechaObtencion;
        this.fechaVencimiento = fechaObtencion.plusYears(1);
    }

    public boolean estaVencida(LocalDate fecha) {
        return fecha.isAfter(fechaVencimiento);
    }

    public int consumir(int cantidad) {
        int usados = Math.min(cantidad, puntosDisponibles);
        puntosDisponibles -= usados;
        return usados;
    }

    public int getPuntosDisponibles() {
        return puntosDisponibles;
    }

    public LocalDate getFechaObtencion() {
        return fechaObtencion;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }
}
