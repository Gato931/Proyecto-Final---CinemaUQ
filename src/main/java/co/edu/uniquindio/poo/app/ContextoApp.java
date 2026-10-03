package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.ConsecutivoCompra;
import co.edu.uniquindio.poo.model.repositorio.RegistroCombos;
import co.edu.uniquindio.poo.model.repositorio.RegistroCompras;
import co.edu.uniquindio.poo.model.repositorio.RegistroFunciones;
import co.edu.uniquindio.poo.model.repositorio.RegistroPeliculas;
import co.edu.uniquindio.poo.model.repositorio.RegistroProductos;
import co.edu.uniquindio.poo.model.repositorio.RegistroPromociones;
import co.edu.uniquindio.poo.model.repositorio.RegistroSalas;
import co.edu.uniquindio.poo.model.repositorio.RegistroUsuarios;
import co.edu.uniquindio.poo.model.servicio.CalculadorPuntosEstandar;
import co.edu.uniquindio.poo.model.servicio.ServicioAutenticacion;
import co.edu.uniquindio.poo.model.servicio.ServicioCartelera;
import co.edu.uniquindio.poo.model.servicio.ServicioCompras;
import co.edu.uniquindio.poo.model.servicio.ServicioDisponibilidadAsientos;
import co.edu.uniquindio.poo.model.servicio.ServicioFunciones;
import co.edu.uniquindio.poo.model.servicio.ServicioPuntos;
import co.edu.uniquindio.poo.model.servicio.ServicioRegistroUsuarios;
import co.edu.uniquindio.poo.model.servicio.ServicioTarjetas;

public class ContextoApp {
    private final ConsecutivoCompra consecutivoCompra;

    private final RegistroUsuarios registroUsuarios = new RegistroUsuarios();
    private final RegistroCompras registroCompras = new RegistroCompras();
    private final RegistroPeliculas registroPeliculas = new RegistroPeliculas();
    private final RegistroSalas registroSalas = new RegistroSalas();
    private final RegistroFunciones registroFunciones = new RegistroFunciones();
    private final RegistroProductos registroProductos = new RegistroProductos();
    private final RegistroCombos registroCombos = new RegistroCombos();
    private final RegistroPromociones registroPromociones = new RegistroPromociones();

    private final ServicioRegistroUsuarios servicioRegistroUsuarios;
    private final ServicioAutenticacion servicioAutenticacion;
    private final ServicioCompras servicioCompras;
    private final ServicioTarjetas servicioTarjetas;
    private final ServicioFunciones servicioFunciones;
    private final ServicioCartelera servicioCartelera;
    private final ServicioDisponibilidadAsientos servicioDisponibilidadAsientos;
    private final ServicioPuntos servicioPuntos;

    public ContextoApp(ConsecutivoCompra consecutivoCompra) {
        this.consecutivoCompra = consecutivoCompra;
        servicioRegistroUsuarios = new ServicioRegistroUsuarios(registroUsuarios);
        servicioAutenticacion = new ServicioAutenticacion(registroUsuarios);
        servicioCompras = new ServicioCompras(registroCompras, new CalculadorPuntosEstandar());
        servicioTarjetas = new ServicioTarjetas();
        servicioFunciones = new ServicioFunciones(registroFunciones);
        servicioCartelera = new ServicioCartelera(registroPeliculas, registroFunciones);
        servicioDisponibilidadAsientos = new ServicioDisponibilidadAsientos();
        servicioPuntos = new ServicioPuntos();
    }

    public String siguienteCodigoCompra() {
        return consecutivoCompra.siguiente();
    }

    public RegistroUsuarios getRegistroUsuarios() {
        return registroUsuarios;
    }

    public RegistroCompras getRegistroCompras() {
        return registroCompras;
    }

    public RegistroPeliculas getRegistroPeliculas() {
        return registroPeliculas;
    }

    public RegistroSalas getRegistroSalas() {
        return registroSalas;
    }

    public RegistroFunciones getRegistroFunciones() {
        return registroFunciones;
    }

    public RegistroProductos getRegistroProductos() {
        return registroProductos;
    }

    public RegistroCombos getRegistroCombos() {
        return registroCombos;
    }

    public RegistroPromociones getRegistroPromociones() {
        return registroPromociones;
    }

    public ServicioRegistroUsuarios getServicioRegistroUsuarios() {
        return servicioRegistroUsuarios;
    }

    public ServicioAutenticacion getServicioAutenticacion() {
        return servicioAutenticacion;
    }

    public ServicioCompras getServicioCompras() {
        return servicioCompras;
    }

    public ServicioTarjetas getServicioTarjetas() {
        return servicioTarjetas;
    }

    public ServicioFunciones getServicioFunciones() {
        return servicioFunciones;
    }

    public ServicioCartelera getServicioCartelera() {
        return servicioCartelera;
    }

    public ServicioDisponibilidadAsientos getServicioDisponibilidadAsientos() {
        return servicioDisponibilidadAsientos;
    }

    public ServicioPuntos getServicioPuntos() {
        return servicioPuntos;
    }
}
