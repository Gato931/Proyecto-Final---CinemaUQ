package co.edu.uniquindio.poo.model.repositorio;

import co.edu.uniquindio.poo.model.Usuario;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroUsuarios implements BuscadorUsuarios, RegistradorUsuarios {
    private final List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void registrar(Usuario usuario) {
        if (usuario == null)
            throw new ValidacionException("El usuario es obligatorio");
        if (buscarPorCorreo(usuario.getCorreo()) != null) {
            throw new ValidacionException("Ya existe un usuario con ese correo");
        }
        usuarios.add(usuario);
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo))
                return usuario;
        }
        return null;
    }

    public Usuario buscarPorId(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equalsIgnoreCase(id))
                return usuario;
        }
        return null;
    }

    public List<Usuario> listar() {
        return Collections.unmodifiableList(usuarios);
    }
}
