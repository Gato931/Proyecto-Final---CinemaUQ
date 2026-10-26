package co.edu.uniquindio.poo.model.reporte;

public class ReporteConFirmaDecorator
        extends ExportadorReporteDecorator {

    public ReporteConFirmaDecorator(
            ExportadorReporte exportador) {

        super(exportador);
    }

    @Override
    public String exportar(
            String titulo,
            String contenido) {

        String contenidoDecorado = contenido
                + "\n"
                + "Generado por CinemaUQ";

        return exportador.exportar(
                titulo,
                contenidoDecorado);
    }
}