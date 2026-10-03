package co.edu.uniquindio.poo.model.repositorio;

import co.edu.uniquindio.poo.model.Combo;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroCombos {
    private final List<Combo> combos = new ArrayList<>();

    public void registrar(Combo combo) {
        if (buscarPorCodigo(combo.getCodigo()) != null) {
            throw new ValidacionException("Ya existe un combo con ese codigo");
        }
        combos.add(combo);
    }

    public Combo buscarPorCodigo(String codigo) {
        for (Combo combo : combos) {
            if (combo.getCodigo().equalsIgnoreCase(codigo))
                return combo;
        }
        return null;
    }

    public List<Combo> listar() {
        return Collections.unmodifiableList(combos);
    }
}
