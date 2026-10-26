package co.edu.uniquindio.poo.model.reporte;

public class LibreriaPdfExterna {

    public String crearDocumentoPdf(
            String nombreDocumento,
            String[] lineas) {

        StringBuilder resultado = new StringBuilder();

        resultado.append(
                "PDF generado: "
                        + nombreDocumento
                        + "\n");

        for (String linea : lineas) {
            resultado.append(linea)
                    .append("\n");
        }

        return resultado.toString();
    }
}