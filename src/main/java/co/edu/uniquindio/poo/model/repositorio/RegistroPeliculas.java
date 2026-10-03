package co.edu.uniquindio.poo.model.repositorio;

import co.edu.uniquindio.poo.model.Pelicula;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroPeliculas {
    private final List<Pelicula> peliculas = new ArrayList<>();

    public void registrar(Pelicula pelicula) {
        if (buscarPorCodigo(pelicula.getCodigo()) != null) {
            throw new ValidacionException("Ya existe una pelicula con ese codigo");
        }
        peliculas.add(pelicula);
    }

    public Pelicula buscarPorCodigo(String codigo) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getCodigo().equalsIgnoreCase(codigo))
                return pelicula;
        }
        return null;
    }

    public List<Pelicula> listar() {
        return Collections.unmodifiableList(peliculas);
    }
}
