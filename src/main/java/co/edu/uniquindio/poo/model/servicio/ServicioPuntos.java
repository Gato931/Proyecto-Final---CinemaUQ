package co.edu.uniquindio.poo.model.servicio;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.excepcion.ValidacionException;
import java.time.LocalDate;

public class ServicioPuntos {
    private static final double PESOS_POR_PUNTO_REDIMIDO = 10.0;

    public int calcularPuntosNecesarios(double valorBeneficio) {
        if (valorBeneficio <= 0)
            throw new ValidacionException("El valor del beneficio debe ser mayor que cero");
        return (int) Math.ceil(valorBeneficio / PESOS_POR_PUNTO_REDIMIDO);
    }

    public void redimirBeneficioCompleto(Cliente cliente, double valorBeneficio, LocalDate fecha) {
        int puntosNecesarios = calcularPuntosNecesarios(valorBeneficio);
        cliente.getCuentaPuntos().redimir(puntosNecesarios, fecha);
    }
}
