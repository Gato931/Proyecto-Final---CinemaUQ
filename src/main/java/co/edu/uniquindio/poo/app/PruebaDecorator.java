package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.reporte.ExportadorReporte;
import co.edu.uniquindio.poo.model.reporte.LibreriaPdfExterna;
import co.edu.uniquindio.poo.model.reporte.ReporteConFechaDecorator;
import co.edu.uniquindio.poo.model.reporte.ReporteConFirmaDecorator;
import co.edu.uniquindio.poo.model.reporte.ReportePdfAdapter;
import co.edu.uniquindio.poo.model.reporte.ServicioReportes;

import java.util.ArrayList;

public class PruebaDecorator {

    public static void main(String[] args) {

        ExportadorReporte reporteBase = new ReportePdfAdapter(
                new LibreriaPdfExterna());

        ExportadorReporte reporteConFecha = new ReporteConFechaDecorator(
                reporteBase);

        ExportadorReporte reporteCompleto = new ReporteConFirmaDecorator(
                reporteConFecha);

        ServicioReportes servicioReportes = new ServicioReportes(
                reporteCompleto);

        String resultado = servicioReportes
                .generarReporteVentas(
                        new ArrayList<>());

        System.out.println(resultado);
    }
}