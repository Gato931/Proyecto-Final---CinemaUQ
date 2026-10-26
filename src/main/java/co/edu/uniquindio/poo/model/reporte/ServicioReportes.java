package co.edu.uniquindio.poo.model.reporte;

import co.edu.uniquindio.poo.model.Compra;

import java.util.List;

public class ServicioReportes {

    private final ExportadorReporte exportador;

    public ServicioReportes(
            ExportadorReporte exportador) {

        this.exportador = exportador;
    }

    public String generarReporteVentas(
            List<Compra> compras) {

        StringBuilder contenido = new StringBuilder();

        contenido.append(
                "Cantidad de ventas: ").append(
                        compras.size())
                .append("\n");

        double totalVentas = 0;

        for (Compra compra : compras) {

            contenido.append(
                    compra.getCodigo()).append(
                            " - $")
                    .append(
                            compra.calcularTotal())
                    .append("\n");

            totalVentas += compra.calcularTotal();
        }

        contenido.append(
                "Total vendido: $").append(totalVentas);

        return exportador.exportar(
                "Reporte de ventas CinemaUQ",
                contenido.toString());
    }
}