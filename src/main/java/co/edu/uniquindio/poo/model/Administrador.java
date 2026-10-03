package co.edu.uniquindio.poo.model;

public class Administrador extends Usuario {
    public Administrador(String id, String nombre, String correo, String contrasena) {
        super(id, nombre, correo, contrasena);
    }

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }
}
