package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.excepcion.SaldoInsuficienteException;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TarjetaVirtual {
    private final String numero;
    private double saldo;
    private boolean activa;
    private final List<MovimientoTarjeta> movimientos = new ArrayList<>();

    public TarjetaVirtual(String numero) {
        if (numero == null || numero.isBlank()) {
            throw new ValidacionException("El numero de la tarjeta es obligatorio");
        }
        this.numero = numero;
        this.saldo = 0;
        this.activa = true;
    }

    public void debitar(double valor, String descripcion) {
        validarTarjetaActiva();
        validarValor(valor);
        if (saldo < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente en la tarjeta virtual");
        }
        saldo -= valor;
        registrarMovimiento(TipoMovimientoTarjeta.PAGO, -valor, descripcion);
    }

    public void recargar(double valor, String descripcion) {
        validarValor(valor);
        saldo += valor;
        registrarMovimiento(TipoMovimientoTarjeta.RECARGA, valor, descripcion);
    }

    public void reembolsar(double valor, String descripcion) {
        validarValor(valor);
        saldo += valor;
        registrarMovimiento(TipoMovimientoTarjeta.REEMBOLSO, valor, descripcion);
    }

    private void validarTarjetaActiva() {
        if (!activa) {
            throw new ValidacionException("La tarjeta virtual se encuentra inactiva");
        }
    }

    private void validarValor(double valor) {
        if (valor <= 0) {
            throw new ValidacionException("El valor debe ser mayor que cero");
        }
    }

    private void registrarMovimiento(TipoMovimientoTarjeta tipo, double valor, String descripcion) {
        movimientos.add(new MovimientoTarjeta(tipo, valor, LocalDateTime.now(), descripcion));
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public List<MovimientoTarjeta> getMovimientos() {
        return Collections.unmodifiableList(movimientos);
    }
}
