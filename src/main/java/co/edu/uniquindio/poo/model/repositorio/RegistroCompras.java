package co.edu.uniquindio.poo.model.repositorio;

import co.edu.uniquindio.poo.model.Asiento;
import co.edu.uniquindio.poo.model.Compra;
import co.edu.uniquindio.poo.model.Entrada;
import co.edu.uniquindio.poo.model.EstadoCompra;
import co.edu.uniquindio.poo.model.Funcion;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroCompras implements AlmacenCompras {
    private final List<Compra> compras = new ArrayList<>();

    @Override
    public void guardar(Compra compra) {
        if (buscarPorCodigo(compra.getCodigo()) != null) {
            throw new ValidacionException("Ya existe una compra con ese codigo");
        }
        compras.add(compra);
    }

    @Override
    public boolean asientoVendido(Funcion funcion, Asiento asiento) {
        for (Compra compra : compras) {
            if (compra.getEstado() == EstadoCompra.PAGADA) {
                for (Entrada entrada : compra.getEntradas()) {
                    if (entrada.getFuncion() == funcion && entrada.getAsiento() == asiento)
                        return true;
                }
            }
        }
        return false;
    }

    public Compra buscarPorCodigo(String codigo) {
        for (Compra compra : compras) {
            if (compra.getCodigo().equalsIgnoreCase(codigo))
                return compra;
        }
        return null;
    }

    public List<Compra> listarPorCliente(String clienteId) {
        List<Compra> resultado = new ArrayList<>();
        for (Compra compra : compras) {
            if (compra.getCliente().getId().equals(clienteId))
                resultado.add(compra);
        }
        return resultado;
    }

    public List<Compra> listar() {
        return Collections.unmodifiableList(compras);
    }
}
