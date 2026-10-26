package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.controller.MainController;
import co.edu.uniquindio.poo.model.ConsecutivoCompra;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CinemaUQApp
        extends Application {

    private static ContextoApp contexto;

    @Override
    public void start(
            Stage stage)
            throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass()
                        .getResource(
                                "/co/edu/uniquindio/poo/view/main-view.fxml"));

        Parent root = loader.load();

        MainController controller = loader.getController();

        controller.setContexto(
                contexto);

        stage.setTitle(
                "CinemaUQ - Entrega 2");

        stage.setScene(
                new Scene(
                        root,
                        1100,
                        720));

        stage.setMinWidth(950);
        stage.setMinHeight(650);

        stage.show();
    }

    public static void main(
            String[] args) {

        contexto = new ContextoApp(
                ConsecutivoCompra
                        .getInstancia());

        contexto.cargarDatosIniciales();

        launch(args);
    }
}