package co.edu.uniquindio.poo.model.repositorio;

import co.edu.uniquindio.poo.model.Producto;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroProductos {
    private final List<Producto> productos = new ArrayList<>();

    public void registrar(Producto producto) {
        if (buscarPorCodigo(producto.getCodigo()) != null) {
            throw new ValidacionException("Ya existe un producto con ese codigo");
        }
        productos.add(producto);
    }

    public Producto buscarPorCodigo(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo))
                return producto;
        }
        return null;
    }

    public List<Producto> listar() {
        return Collections.unmodifiableList(productos);
    }
}
