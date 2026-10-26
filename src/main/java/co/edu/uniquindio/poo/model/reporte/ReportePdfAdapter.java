package co.edu.uniquindio.poo.model.reporte;

import co.edu.uniquindio.poo.model.excepcion.ValidacionException;

public class ReportePdfAdapter
        implements ExportadorReporte {

    private final LibreriaPdfExterna libreriaPdf;

    public ReportePdfAdapter(
            LibreriaPdfExterna libreriaPdf) {

        if (libreriaPdf == null) {
            throw new ValidacionException(
                    "La libreria PDF es obligatoria");
        }

        this.libreriaPdf = libreriaPdf;
    }

    @Override
    public String exportar(
            String titulo,
            String contenido) {

        if (titulo == null
                || titulo.isBlank()) {

            throw new ValidacionException(
                    "El titulo del reporte es obligatorio");
        }

        if (contenido == null
                || contenido.isBlank()) {

            throw new ValidacionException(
                    "El contenido del reporte es obligatorio");
        }

        String[] lineas = contenido.split("\\R");

        return libreriaPdf
                .crearDocumentoPdf(
                        titulo,
                        lineas);
    }
}