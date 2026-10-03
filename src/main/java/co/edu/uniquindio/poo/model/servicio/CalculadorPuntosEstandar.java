package co.edu.uniquindio.poo.model.servicio;

public class CalculadorPuntosEstandar implements CalculadorPuntos {
    private static final double PESOS_POR_PUNTO = 1000.0;

    @Override
    public int calcular(double valorPagado) {
        return (int) Math.floor(valorPagado / PESOS_POR_PUNTO);
    }
}
