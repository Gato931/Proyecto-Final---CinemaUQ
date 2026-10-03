package co.edu.uniquindio.poo.model.servicio;

import co.edu.uniquindio.poo.model.Usuario;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import co.edu.uniquindio.poo.model.repositorio.BuscadorUsuarios;

public class ServicioAutenticacion {
    private final BuscadorUsuarios buscadorUsuarios;

    public ServicioAutenticacion(BuscadorUsuarios buscadorUsuarios) {
        this.buscadorUsuarios = buscadorUsuarios;
    }

    public Usuario iniciarSesion(String correo, String contrasena) {
        Usuario usuario = buscadorUsuarios.buscarPorCorreo(correo);
        if (usuario == null || !usuario.validarContrasena(contrasena)) {
            throw new ValidacionException("Correo o contrasena incorrectos");
        }
        return usuario;
    }
}
