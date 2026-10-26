package co.edu.uniquindio.poo.model.servicio;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Usuario;

public interface GestorTarjetas {

    void recargar(
            Usuario solicitante,
            Cliente cliente,
            double valor);
}