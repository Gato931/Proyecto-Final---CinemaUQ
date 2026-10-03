package co.edu.uniquindio.poo.model.repositorio;

import co.edu.uniquindio.poo.model.Usuario;

public interface BuscadorUsuarios {
    Usuario buscarPorCorreo(String correo);
}
