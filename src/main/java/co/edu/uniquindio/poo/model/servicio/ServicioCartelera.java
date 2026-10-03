package co.edu.uniquindio.poo.model.servicio;

import co.edu.uniquindio.poo.model.EstadoFuncion;
import co.edu.uniquindio.poo.model.Funcion;
import co.edu.uniquindio.poo.model.Pelicula;
import co.edu.uniquindio.poo.model.repositorio.RegistroFunciones;
import co.edu.uniquindio.poo.model.repositorio.RegistroPeliculas;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioCartelera {
    private final RegistroPeliculas registroPeliculas;
    private final RegistroFunciones registroFunciones;

    public ServicioCartelera(RegistroPeliculas registroPeliculas, RegistroFunciones registroFunciones) {
        this.registroPeliculas = registroPeliculas;
        this.registroFunciones = registroFunciones;
    }

    public List<Pelicula> consultarPeliculasActivas() {
        List<Pelicula> resultado = new ArrayList<>();
        for (Pelicula pelicula : registroPeliculas.listar()) {
            if (pelicula.isActiva())
                resultado.add(pelicula);
        }
        return resultado;
    }

    public List<Funcion> consultarFuncionesDisponibles(Pelicula pelicula) {
        List<Funcion> resultado = new ArrayList<>();
        for (Funcion funcion : registroFunciones.listarPorPelicula(pelicula)) {
            if (funcion.getEstado() == EstadoFuncion.PROGRAMADA
                    && funcion.getFechaHora().isAfter(LocalDateTime.now())) {
                resultado.add(funcion);
            }
        }
        return resultado;
    }
}
