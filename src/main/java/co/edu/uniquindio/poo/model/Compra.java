package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Compra {
    private final String codigo;
    private final Cliente cliente;
    private final LocalDateTime fecha;
    private final List<Entrada> entradas;
    private final List<DetalleProductoCompra> productos;
    private final List<DetalleComboCompra> combos;
    private final Promocion promocion;
    private EstadoCompra estado;
    private boolean reembolsoRealizado;

    private Compra(Builder builder) {
        this.codigo = builder.codigo;
        this.cliente = builder.cliente;
        this.fecha = LocalDateTime.now();
        this.entradas = List.copyOf(builder.entradas);
        this.productos = List.copyOf(builder.productos);
        this.combos = List.copyOf(builder.combos);
        this.promocion = builder.promocion;
        this.estado = EstadoCompra.PENDIENTE;
        this.reembolsoRealizado = false;
    }

    public double calcularSubtotal() {
        double total = 0;
        for (Entrada entrada : entradas)
            total += entrada.getPrecio();
        for (DetalleProductoCompra detalle : productos)
            total += detalle.calcularSubtotal();
        for (DetalleComboCompra detalle : combos)
            total += detalle.calcularSubtotal();
        return total;
    }

    public double calcularTotal() {
        double subtotal = calcularSubtotal();
        if (promocion == null)
            return subtotal;
        return promocion.aplicar(subtotal, fecha.toLocalDate());
    }

    public String getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public List<Entrada> getEntradas() {
        return Collections.unmodifiableList(entradas);
    }

    public List<DetalleProductoCompra> getProductos() {
        return Collections.unmodifiableList(productos);
    }

    public List<DetalleComboCompra> getCombos() {
        return Collections.unmodifiableList(combos);
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public EstadoCompra getEstado() {
        return estado;
    }

    public void setEstado(EstadoCompra estado) {
        this.estado = estado;
    }

    public boolean isReembolsoRealizado() {
        return reembolsoRealizado;
    }

    public void setReembolsoRealizado(boolean reembolsoRealizado) {
        this.reembolsoRealizado = reembolsoRealizado;
    }

    public static class Builder {
        private String codigo;
        private Cliente cliente;
        private final List<Entrada> entradas = new ArrayList<>();
        private final List<DetalleProductoCompra> productos = new ArrayList<>();
        private final List<DetalleComboCompra> combos = new ArrayList<>();
        private Promocion promocion;

        public Builder conCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder conCliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder conEntrada(Entrada entrada) {
            if (entrada != null)
                entradas.add(entrada);
            return this;
        }

        public Builder conProducto(Producto producto, int cantidad) {
            productos.add(new DetalleProductoCompra(producto, cantidad));
            return this;
        }

        public Builder conCombo(Combo combo, int cantidad) {
            combos.add(new DetalleComboCompra(combo, cantidad));
            return this;
        }

        public Builder conPromocion(Promocion promocion) {
            this.promocion = promocion;
            return this;
        }

        public Compra build() {
            if (codigo == null || codigo.isBlank())
                throw new ValidacionException("La compra requiere un codigo");
            if (cliente == null)
                throw new ValidacionException("La compra requiere un cliente");
            if (entradas.isEmpty() && productos.isEmpty() && combos.isEmpty()) {
                throw new ValidacionException("La compra debe contener al menos un item");
            }
            validarUnaMismaFuncion();
            return new Compra(this);
        }

        private void validarUnaMismaFuncion() {
            if (entradas.size() <= 1)
                return;
            Funcion funcion = entradas.get(0).getFuncion();
            for (Entrada entrada : entradas) {
                if (entrada.getFuncion() != funcion) {
                    throw new ValidacionException("Una compra solo puede incluir entradas de una misma funcion");
                }
            }
        }
    }
}
