package co.edu.uniquindio.poo.model.servicio;

import co.edu.uniquindio.poo.model.Compra;
import co.edu.uniquindio.poo.model.DetalleComboCompra;
import co.edu.uniquindio.poo.model.DetalleProductoCompra;
import co.edu.uniquindio.poo.model.Entrada;
import co.edu.uniquindio.poo.model.EstadoCompra;
import co.edu.uniquindio.poo.model.EstadoFuncion;
import co.edu.uniquindio.poo.model.excepcion.AsientoNoDisponibleException;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import co.edu.uniquindio.poo.model.repositorio.AlmacenCompras;
import co.edu.uniquindio.poo.model.Producto;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

public class ServicioCompras {
    private final AlmacenCompras almacenCompras;
    private final CalculadorPuntos calculadorPuntos;

    public ServicioCompras(AlmacenCompras almacenCompras, CalculadorPuntos calculadorPuntos) {
        this.almacenCompras = almacenCompras;
        this.calculadorPuntos = calculadorPuntos;
    }

    public void confirmarCompra(Compra compra) {
        if (compra == null)
            throw new ValidacionException("La compra es obligatoria");
        if (compra.getEstado() != EstadoCompra.PENDIENTE) {
            throw new ValidacionException("La compra ya fue procesada");
        }

        validarEntradas(compra);
        validarConfiteria(compra);

        double total = compra.calcularTotal();
        compra.getCliente().getTarjetaVirtual().debitar(total, "Pago de compra " + compra.getCodigo());

        descontarStock(compra);
        compra.setEstado(EstadoCompra.PAGADA);
        almacenCompras.guardar(compra);
        for (Entrada entrada : compra.getEntradas()) {
            entrada.getAsiento().ocupar();
        }

        int puntos = calculadorPuntos.calcular(total);
        compra.getCliente().getCuentaPuntos().acumular(puntos, LocalDate.now());
    }

    private void validarEntradas(Compra compra) {
        for (Entrada entrada : compra.getEntradas()) {
            if (entrada.getFuncion().getEstado() != EstadoFuncion.PROGRAMADA) {
                throw new ValidacionException("La funcion de la entrada no se encuentra programada");
            }
            if (!entrada.getAsiento().estaDisponible()) {
                throw new AsientoNoDisponibleException("El asiento " + entrada.getAsiento() + " no esta disponible");
            }
            if (almacenCompras.asientoVendido(entrada.getFuncion(), entrada.getAsiento())) {
                throw new AsientoNoDisponibleException(
                        "El asiento " + entrada.getAsiento() + " ya fue vendido para esa funcion");
            }
        }
    }

    private void validarConfiteria(Compra compra) {

        Map<Producto, Integer> necesidades = new HashMap<>();

        for (DetalleProductoCompra detalle : compra.getProductos()) {

            detalle.getProducto()
                    .acumularStockNecesario(
                            necesidades,
                            detalle.getCantidad());
        }

        for (DetalleComboCompra detalle : compra.getCombos()) {

            if (!detalle.getCombo().isActivo()) {

                throw new ValidacionException(
                        "Combo inactivo: "
                                + detalle.getCombo()
                                        .getNombre());
            }

            detalle.getCombo()
                    .acumularStockNecesario(
                            necesidades,
                            detalle.getCantidad());
        }

        for (Map.Entry<Producto, Integer> necesidad : necesidades.entrySet()) {

            Producto producto = necesidad.getKey();
            int cantidad = necesidad.getValue();

            if (!producto.isActivo()) {

                throw new ValidacionException(
                        "Producto inactivo: "
                                + producto.getNombre());
            }

            if (!producto.tieneStock(cantidad)) {

                throw new ValidacionException(
                        "Stock insuficiente para "
                                + producto.getNombre());
            }
        }
    }

    private void descontarStock(Compra compra) {
        for (DetalleProductoCompra detalle : compra.getProductos()) {
            detalle.getProducto().descontarStock(detalle.getCantidad());
        }
        for (DetalleComboCompra detalle : compra.getCombos()) {
            detalle.getCombo().descontarStock(detalle.getCantidad());
        }
    }
}
