package co.edu.uniquindio.poo.model.repositorio;

import co.edu.uniquindio.poo.model.Asiento;
import co.edu.uniquindio.poo.model.Compra;
import co.edu.uniquindio.poo.model.Funcion;

public interface AlmacenCompras {
    void guardar(Compra compra);

    boolean asientoVendido(Funcion funcion, Asiento asiento);
}
