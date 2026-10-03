package co.edu.uniquindio.poo.model.repositorio;

import co.edu.uniquindio.poo.model.Sala;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroSalas {
    private final List<Sala> salas = new ArrayList<>();

    public void registrar(Sala sala) {
        if (buscarPorCodigo(sala.getCodigo()) != null) {
            throw new ValidacionException("Ya existe una sala con ese codigo");
        }
        salas.add(sala);
    }

    public Sala buscarPorCodigo(String codigo) {
        for (Sala sala : salas) {
            if (sala.getCodigo().equalsIgnoreCase(codigo))
                return sala;
        }
        return null;
    }

    public List<Sala> listar() {
        return Collections.unmodifiableList(salas);
    }
}
