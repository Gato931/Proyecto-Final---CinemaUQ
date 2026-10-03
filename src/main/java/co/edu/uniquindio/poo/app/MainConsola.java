package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.Administrador;
import co.edu.uniquindio.poo.model.Asiento;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Compra;
import co.edu.uniquindio.poo.model.Entrada;
import co.edu.uniquindio.poo.model.Funcion;
import co.edu.uniquindio.poo.model.Pelicula;
import co.edu.uniquindio.poo.model.Sala;
import co.edu.uniquindio.poo.model.excepcion.DominioException;
import co.edu.uniquindio.poo.model.repositorio.RegistroCompras;
import co.edu.uniquindio.poo.model.repositorio.RegistroUsuarios;
import co.edu.uniquindio.poo.model.servicio.CalculadorPuntosEstandar;
import co.edu.uniquindio.poo.model.servicio.ServicioCompras;
import co.edu.uniquindio.poo.model.servicio.ServicioRegistroUsuarios;
import co.edu.uniquindio.poo.model.servicio.ServicioTarjetas;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MainConsola {
    public static void main(String[] args) {
        RegistroUsuarios registroUsuarios = new RegistroUsuarios();
        ServicioRegistroUsuarios servicioUsuarios = new ServicioRegistroUsuarios(registroUsuarios);
        RegistroCompras registroCompras = new RegistroCompras();
        ServicioCompras servicioCompras = new ServicioCompras(registroCompras, new CalculadorPuntosEstandar());
        ServicioTarjetas servicioTarjetas = new ServicioTarjetas();

        Cliente cliente = servicioUsuarios.registrarCliente(
                "C-001", "Laura", "laura@correo.com", "1234");
        Administrador administrador = servicioUsuarios.registrarAdministrador(
                "A-001", "Administrador", "admin@cinemauq.com", "admin");
        servicioTarjetas.recargar(administrador, cliente, 100000);

        Pelicula pelicula = new Pelicula("P-001", "Interestelar", "Ciencia ficcion", 169, "+12");
        Sala sala = new Sala("S-01", "Sala 1");
        sala.agregarAsiento(new Asiento("A1", "A", 1));
        sala.agregarAsiento(new Asiento("A2", "A", 2));

        Funcion funcion = new Funcion("F-001", pelicula, sala,
                LocalDateTime.now().plusDays(2), 18000);

        Funcion funcionCopia = funcion.copiarPara("F-002", LocalDateTime.now().plusDays(3));

        Asiento asientoA1 = funcion.buscarAsiento("A1");
        Entrada entrada = new Entrada("E-001", funcion, asientoA1);

        Compra compra = new Compra.Builder()
                .conCodigo("COMP-PRUEBA-001")
                .conCliente(cliente)
                .conEntrada(entrada)
                .build();

        servicioCompras.confirmarCompra(compra);

        System.out.println("Compra confirmada: " + compra.getCodigo());
        System.out.println("Saldo: $" + cliente.getTarjetaVirtual().getSaldo());
        System.out.println("Puntos: " + cliente.getCuentaPuntos().consultarDisponibles(LocalDate.now()));
        System.out.println("A1 ocupada en F-001: " + funcion.buscarAsiento("A1").isOcupado());
        System.out.println("A1 ocupada en F-002: " + funcionCopia.buscarAsiento("A1").isOcupado());

        try {
            Compra repetida = new Compra.Builder()
                    .conCodigo("COMP-PRUEBA-002")
                    .conCliente(cliente)
                    .conEntrada(new Entrada("E-002", funcion, asientoA1))
                    .build();
            servicioCompras.confirmarCompra(repetida);
        } catch (DominioException exception) {
            System.out.println("Asiento duplicado rechazado: " + exception.getMessage());
        }
    }
}
