package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CuentaPuntos {
    private final List<AcumulacionPuntos> acumulaciones = new ArrayList<>();

    public void acumular(int puntos, LocalDate fecha) {
        if (puntos <= 0) {
            return;
        }
        acumulaciones.add(new AcumulacionPuntos(puntos, fecha));
    }

    public int consultarDisponibles(LocalDate fecha) {
        int total = 0;
        for (AcumulacionPuntos acumulacion : acumulaciones) {
            if (!acumulacion.estaVencida(fecha)) {
                total += acumulacion.getPuntosDisponibles();
            }
        }
        return total;
    }

    public void redimir(int puntos, LocalDate fecha) {
        if (puntos <= 0) {
            throw new ValidacionException("La cantidad de puntos a redimir debe ser mayor que cero");
        }
        if (consultarDisponibles(fecha) < puntos) {
            throw new ValidacionException("El cliente no tiene puntos suficientes");
        }

        List<AcumulacionPuntos> vigentes = new ArrayList<>();
        for (AcumulacionPuntos acumulacion : acumulaciones) {
            if (!acumulacion.estaVencida(fecha) && acumulacion.getPuntosDisponibles() > 0) {
                vigentes.add(acumulacion);
            }
        }
        vigentes.sort(Comparator.comparing(AcumulacionPuntos::getFechaVencimiento));

        int pendientes = puntos;
        for (AcumulacionPuntos acumulacion : vigentes) {
            int usados = acumulacion.consumir(pendientes);
            pendientes -= usados;
            if (pendientes == 0) {
                break;
            }
        }
    }

    public List<AcumulacionPuntos> getAcumulaciones() {
        return Collections.unmodifiableList(acumulaciones);
    }
}
