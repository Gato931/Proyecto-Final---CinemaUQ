package co.edu.uniquindio.poo.controller;

import co.edu.uniquindio.poo.app.ContextoApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController implements ContextoController {
    @FXML
    private Label lblEstado;

    private ContextoApp contexto;

    @FXML
    private void initialize() {
        refrescar();
    }

    @Override
    public void setContexto(ContextoApp contexto) {
        this.contexto = contexto;
        refrescar();
    }

    @Override
    public void refrescar() {
        if (lblEstado == null)
            return;

        if (contexto == null) {
            lblEstado.setText("Cargando datos de CinemaUQ");
            return;
        }

        lblEstado.setText("Base de Entrega 1 cargada - usuarios: "
                + contexto.getRegistroUsuarios().listar().size()
                + " | peliculas: " + contexto.getRegistroPeliculas().listar().size()
                + " | funciones: " + contexto.getRegistroFunciones().listar().size());
    }
}
