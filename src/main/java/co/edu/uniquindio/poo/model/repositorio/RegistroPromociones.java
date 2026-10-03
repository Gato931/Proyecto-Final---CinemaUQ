package co.edu.uniquindio.poo.model.repositorio;

import co.edu.uniquindio.poo.model.Promocion;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroPromociones {
    private final List<Promocion> promociones = new ArrayList<>();

    public void registrar(Promocion promocion) {
        if (buscarPorCodigo(promocion.getCodigo()) != null) {
            throw new ValidacionException("Ya existe una promocion con ese codigo");
        }
        promociones.add(promocion);
    }

    public Promocion buscarPorCodigo(String codigo) {
        for (Promocion promocion : promociones) {
            if (promocion.getCodigo().equalsIgnoreCase(codigo))
                return promocion;
        }
        return null;
    }

    public List<Promocion> listar() {
        return Collections.unmodifiableList(promociones);
    }
}
