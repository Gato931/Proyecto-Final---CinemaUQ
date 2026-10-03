package co.edu.uniquindio.poo.model.servicio;

import co.edu.uniquindio.poo.model.Asiento;
import co.edu.uniquindio.poo.model.Funcion;
import java.util.ArrayList;
import java.util.List;

public class ServicioDisponibilidadAsientos {
    public List<Asiento> consultarDisponibles(Funcion funcion) {
        List<Asiento> disponibles = new ArrayList<>();
        for (Asiento asiento : funcion.getMapaAsientos()) {
            if (asiento.estaDisponible())
                disponibles.add(asiento);
        }
        return disponibles;
    }
}
