package co.edu.uniquindio.poo.model.reporte;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReporteConFechaDecorator
        extends ExportadorReporteDecorator {

    public ReporteConFechaDecorator(
            ExportadorReporte exportador) {

        super(exportador);
    }

    @Override
    public String exportar(
            String titulo,
            String contenido) {

        String fecha = LocalDateTime.now()
                .format(
                        DateTimeFormatter.ofPattern(
                                "dd/MM/yyyy HH:mm"));

        String contenidoDecorado = "Fecha de generacion: "
                + fecha
                + "\n"
                + contenido;

        return exportador.exportar(
                titulo,
                contenidoDecorado);
    }
}