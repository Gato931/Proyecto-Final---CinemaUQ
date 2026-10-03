package co.edu.uniquindio.poo.model.repositorio;

import co.edu.uniquindio.poo.model.Funcion;
import co.edu.uniquindio.poo.model.Pelicula;
import co.edu.uniquindio.poo.model.Sala;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroFunciones {
    private final List<Funcion> funciones = new ArrayList<>();

    public void registrar(Funcion funcion) {
        if (buscarPorCodigo(funcion.getCodigo()) != null) {
            throw new ValidacionException("Ya existe una funcion con ese codigo");
        }
        funciones.add(funcion);
    }

    public Funcion buscarPorCodigo(String codigo) {
        for (Funcion funcion : funciones) {
            if (funcion.getCodigo().equalsIgnoreCase(codigo))
                return funcion;
        }
        return null;
    }

    public List<Funcion> listarPorPelicula(Pelicula pelicula) {
        List<Funcion> resultado = new ArrayList<>();
        for (Funcion funcion : funciones) {
            if (funcion.getPelicula() == pelicula)
                resultado.add(funcion);
        }
        return resultado;
    }

    public List<Funcion> listarPorSala(Sala sala) {
        List<Funcion> resultado = new ArrayList<>();
        for (Funcion funcion : funciones) {
            if (funcion.getSala() == sala)
                resultado.add(funcion);
        }
        return resultado;
    }

    public List<Funcion> listar() {
        return Collections.unmodifiableList(funciones);
    }
}
