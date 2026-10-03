package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public abstract class Usuario {
    private final String id;
    private String nombre;
    private final String correo;
    private String contrasena;

    protected Usuario(String id, String nombre, String correo, String contrasena) {
        validarTexto(id, "El id del usuario es obligatorio");
        validarTexto(nombre, "El nombre del usuario es obligatorio");
        validarTexto(correo, "El correo del usuario es obligatorio");
        validarTexto(contrasena, "La contrasena del usuario es obligatoria");
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    private void validarTexto(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new ValidacionException(mensaje);
        }
    }

    public boolean validarContrasena(String contrasena) {
        return this.contrasena.equals(contrasena);
    }

    public void cambiarContrasena(String nuevaContrasena) {
        validarTexto(nuevaContrasena, "La nueva contrasena es obligatoria");
        this.contrasena = nuevaContrasena;
    }

    public abstract String getTipoUsuario();

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        validarTexto(nombre, "El nombre del usuario es obligatorio");
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return nombre + " - " + getTipoUsuario();
    }
}
