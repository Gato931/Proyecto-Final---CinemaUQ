package co.edu.uniquindio.poo.model;

import java.util.concurrent.atomic.AtomicInteger;

public class ConsecutivoCompra {
    private final AtomicInteger ultimo = new AtomicInteger(0);

    private ConsecutivoCompra() {
    }

    private static class Holder {
        private static final ConsecutivoCompra INSTANCIA = new ConsecutivoCompra();
    }

    public static ConsecutivoCompra getInstancia() {
        return Holder.INSTANCIA;
    }

    public String siguiente() {
        return String.format("COMP-%04d", ultimo.incrementAndGet());
    }
}
