package co.edu.uniquindio.poo.model.servicio;

import co.edu.uniquindio.poo.model.Administrador;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public class ServicioTarjetas {
    public void recargar(Administrador administrador, Cliente cliente, double valor) {
        if (administrador == null) {
            throw new ValidacionException("Solo un administrador puede realizar la recarga");
        }
        if (cliente == null) {
            throw new ValidacionException("El cliente es obligatorio");
        }
        cliente.getTarjetaVirtual().recargar(valor, "Recarga realizada por " + administrador.getNombre());
    }
}
