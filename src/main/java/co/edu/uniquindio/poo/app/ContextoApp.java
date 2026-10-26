package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.Administrador;
import co.edu.uniquindio.poo.model.Asiento;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Combo;
import co.edu.uniquindio.poo.model.ConsecutivoCompra;
import co.edu.uniquindio.poo.model.Funcion;
import co.edu.uniquindio.poo.model.Pelicula;
import co.edu.uniquindio.poo.model.Producto;
import co.edu.uniquindio.poo.model.Promocion;
import co.edu.uniquindio.poo.model.Sala;
import co.edu.uniquindio.poo.model.repositorio.RegistroCombos;
import co.edu.uniquindio.poo.model.repositorio.RegistroCompras;
import co.edu.uniquindio.poo.model.repositorio.RegistroFunciones;
import co.edu.uniquindio.poo.model.repositorio.RegistroPeliculas;
import co.edu.uniquindio.poo.model.repositorio.RegistroProductos;
import co.edu.uniquindio.poo.model.repositorio.RegistroPromociones;
import co.edu.uniquindio.poo.model.repositorio.RegistroSalas;
import co.edu.uniquindio.poo.model.repositorio.RegistroUsuarios;
import co.edu.uniquindio.poo.model.servicio.CalculadorPuntosEstandar;
import co.edu.uniquindio.poo.model.servicio.GestorTarjetas;
import co.edu.uniquindio.poo.model.servicio.ServicioAutenticacion;
import co.edu.uniquindio.poo.model.servicio.ServicioCartelera;
import co.edu.uniquindio.poo.model.servicio.ServicioCompras;
import co.edu.uniquindio.poo.model.servicio.ServicioDisponibilidadAsientos;
import co.edu.uniquindio.poo.model.servicio.ServicioFunciones;
import co.edu.uniquindio.poo.model.servicio.ServicioPuntos;
import co.edu.uniquindio.poo.model.servicio.ServicioRegistroUsuarios;
import co.edu.uniquindio.poo.model.servicio.ServicioTarjetas;
import co.edu.uniquindio.poo.model.servicio.ServicioTarjetasProxy;

import java.time.LocalDate;

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
    private final GestorTarjetas servicioTarjetas;
    private final ServicioFunciones servicioFunciones;
    private final ServicioCartelera servicioCartelera;
    private final ServicioDisponibilidadAsientos servicioDisponibilidadAsientos;
    private final ServicioPuntos servicioPuntos;

    public ContextoApp(ConsecutivoCompra consecutivoCompra) {

        this.consecutivoCompra = consecutivoCompra;

        servicioRegistroUsuarios = new ServicioRegistroUsuarios(
                registroUsuarios);

        servicioAutenticacion = new ServicioAutenticacion(
                registroUsuarios);

        servicioCompras = new ServicioCompras(
                registroCompras,
                new CalculadorPuntosEstandar());

        servicioTarjetas = new ServicioTarjetasProxy(
                new ServicioTarjetas());

        servicioFunciones = new ServicioFunciones(
                registroFunciones);

        servicioCartelera = new ServicioCartelera(
                registroPeliculas,
                registroFunciones);

        servicioDisponibilidadAsientos = new ServicioDisponibilidadAsientos();

        servicioPuntos = new ServicioPuntos();
    }

    public void cargarDatosIniciales() {

        if (!registroUsuarios.listar().isEmpty()) {
            return;
        }

        Cliente cliente = servicioRegistroUsuarios.registrarCliente(
                "C-001",
                "Laura Gomez",
                "laura@cinemauq.com",
                "1234");

        Administrador administrador = servicioRegistroUsuarios.registrarAdministrador(
                "A-001",
                "Administrador",
                "admin@cinemauq.com",
                "admin");

        servicioTarjetas.recargar(
                administrador,
                cliente,
                250000);

        Pelicula pelicula1 = new Pelicula(
                "P-001",
                "Interestelar",
                "Ciencia ficcion",
                169,
                "+12");

        Pelicula pelicula2 = new Pelicula(
                "P-002",
                "Super Mario Bros",
                "Animacion",
                92,
                "Todos");

        Pelicula pelicula3 = new Pelicula(
                "P-003",
                "Batman",
                "Accion",
                176,
                "+12");

        registroPeliculas.registrar(pelicula1);
        registroPeliculas.registrar(pelicula2);
        registroPeliculas.registrar(pelicula3);

        Sala sala = new Sala(
                "S-01",
                "Sala Principal");

        String[] filas = { "A", "B", "C", "D" };

        for (String fila : filas) {

            for (int numero = 1; numero <= 6; numero++) {

                sala.agregarAsiento(
                        new Asiento(
                                fila + numero,
                                fila,
                                numero));
            }
        }

        registroSalas.registrar(sala);

        LocalDate manana = LocalDate.now().plusDays(1);

        Funcion funcion1 = new Funcion(
                "F-001",
                pelicula1,
                sala,
                manana.atTime(14, 0),
                18000);

        Funcion funcion2 = new Funcion(
                "F-002",
                pelicula2,
                sala,
                manana.atTime(18, 0),
                16000);

        Funcion funcion3 = new Funcion(
                "F-003",
                pelicula3,
                sala,
                manana.atTime(21, 0),
                20000);

        servicioFunciones.registrar(funcion1);
        servicioFunciones.registrar(funcion2);
        servicioFunciones.registrar(funcion3);

        Producto crispetas = new Producto(
                "PR-001",
                "Crispetas grandes",
                15000,
                50);

        Producto gaseosa = new Producto(
                "PR-002",
                "Gaseosa",
                8000,
                80);

        Producto nachos = new Producto(
                "PR-003",
                "Nachos",
                12000,
                40);

        registroProductos.registrar(crispetas);
        registroProductos.registrar(gaseosa);
        registroProductos.registrar(nachos);

        Combo comboPareja = new Combo(
                "CO-001",
                "Combo Pareja",
                28000);

        comboPareja.agregarProducto(
                crispetas,
                1);

        comboPareja.agregarProducto(
                gaseosa,
                2);

        Combo comboNachos = new Combo(
                "CO-002",
                "Combo Nachos",
                18000);

        comboNachos.agregarProducto(
                nachos,
                1);

        comboNachos.agregarProducto(
                gaseosa,
                1);

        registroCombos.registrar(comboPareja);
        registroCombos.registrar(comboNachos);

        Promocion promocion = new Promocion(
                "PROMO-001",
                "Descuento CinemaUQ 10%",
                10,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(30));

        registroPromociones.registrar(promocion);
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

    public GestorTarjetas getServicioTarjetas() {
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