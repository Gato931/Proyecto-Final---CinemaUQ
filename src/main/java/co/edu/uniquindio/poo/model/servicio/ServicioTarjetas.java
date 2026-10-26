package co.edu.uniquindio.poo.model.servicio;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Usuario;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public class ServicioTarjetas implements GestorTarjetas {

    @Override
    public void recargar(
            Usuario solicitante,
            Cliente cliente,
            double valor) {

        if (solicitante == null) {
            throw new ValidacionException(
                    "El usuario que realiza la recarga es obligatorio");
        }

        if (cliente == null) {
            throw new ValidacionException(
                    "El cliente es obligatorio");
        }

        cliente.getTarjetaVirtual().recargar(
                valor,
                "Recarga realizada por "
                        + solicitante.getNombre());
    }
}