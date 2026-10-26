package co.edu.uniquindio.poo.model.reporte;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public abstract class ExportadorReporteDecorator
        implements ExportadorReporte {

    protected final ExportadorReporte exportador;

    public ExportadorReporteDecorator(
            ExportadorReporte exportador) {

        if (exportador == null) {
            throw new ValidacionException(
                    "El exportador de reporte es obligatorio");
        }

        this.exportador = exportador;
    }

    @Override
    public String exportar(
            String titulo,
            String contenido) {

        return exportador.exportar(
                titulo,
                contenido);
    }
}