package co.edu.uniquindio.poo.model;

public class Cliente extends Usuario {
    private final TarjetaVirtual tarjetaVirtual;
    private final CuentaPuntos cuentaPuntos;

    public Cliente(String id, String nombre, String correo, String contrasena) {
        super(id, nombre, correo, contrasena);
        this.tarjetaVirtual = new TarjetaVirtual("TV-" + id);
        this.cuentaPuntos = new CuentaPuntos();
    }

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }

    public TarjetaVirtual getTarjetaVirtual() {
        return tarjetaVirtual;
    }

    public CuentaPuntos getCuentaPuntos() {
        return cuentaPuntos;
    }
}
