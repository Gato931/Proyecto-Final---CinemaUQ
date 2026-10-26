package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.reporte.ExportadorReporte;
import co.edu.uniquindio.poo.model.reporte.LibreriaPdfExterna;
import co.edu.uniquindio.poo.model.reporte.ReportePdfAdapter;
import co.edu.uniquindio.poo.model.reporte.ServicioReportes;

import java.util.ArrayList;

public class PruebaAdapter {

    public static void main(String[] args) {

        LibreriaPdfExterna libreria = new LibreriaPdfExterna();

        ExportadorReporte exportador = new ReportePdfAdapter(
                libreria);

        ServicioReportes servicio = new ServicioReportes(
                exportador);

        String reporte = servicio.generarReporteVentas(
                new ArrayList<>());

        System.out.println(reporte);
    }
}