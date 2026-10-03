package co.edu.uniquindio.poo.model.servicio;

import co.edu.uniquindio.poo.model.Administrador;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Usuario;
import co.edu.uniquindio.poo.model.repositorio.RegistradorUsuarios;

public class ServicioRegistroUsuarios {
    private final RegistradorUsuarios registradorUsuarios;

    public ServicioRegistroUsuarios(RegistradorUsuarios registradorUsuarios) {
        this.registradorUsuarios = registradorUsuarios;
    }

    public Cliente registrarCliente(String id, String nombre, String correo, String contrasena) {
        Cliente cliente = new Cliente(id, nombre, correo, contrasena);
        registradorUsuarios.registrar(cliente);
        return cliente;
    }

    public Administrador registrarAdministrador(String id, String nombre, String correo, String contrasena) {
        Administrador administrador = new Administrador(id, nombre, correo, contrasena);
        registradorUsuarios.registrar(administrador);
        return administrador;
    }

    public Usuario registrar(Usuario usuario) {
        registradorUsuarios.registrar(usuario);
        return usuario;
    }
}
