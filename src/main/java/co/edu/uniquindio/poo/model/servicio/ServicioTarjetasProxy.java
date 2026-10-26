package co.edu.uniquindio.poo.model.servicio;

import co.edu.uniquindio.poo.model.Administrador;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Usuario;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public class ServicioTarjetasProxy implements GestorTarjetas {

    private final GestorTarjetas servicioReal;

    public ServicioTarjetasProxy(
            GestorTarjetas servicioReal) {

        if (servicioReal == null) {
            throw new ValidacionException(
                    "El servicio real es obligatorio");
        }

        this.servicioReal = servicioReal;
    }

    @Override
    public void recargar(
            Usuario solicitante,
            Cliente cliente,
            double valor) {

        if (!(solicitante instanceof Administrador)) {

            throw new ValidacionException(
                    "Solo un administrador puede realizar recargas");
        }

        servicioReal.recargar(
                solicitante,
                cliente,
                valor);
    }
}