package co.edu.uniquindio.poo.controller;

import co.edu.uniquindio.poo.app.ContextoApp;
import co.edu.uniquindio.poo.model.Asiento;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Combo;
import co.edu.uniquindio.poo.model.Compra;
import co.edu.uniquindio.poo.model.Entrada;
import co.edu.uniquindio.poo.model.Funcion;
import co.edu.uniquindio.poo.model.Pelicula;
import co.edu.uniquindio.poo.model.Producto;
import co.edu.uniquindio.poo.model.Promocion;
import co.edu.uniquindio.poo.model.Usuario;
import co.edu.uniquindio.poo.model.excepcion.DominioException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainController
        implements ContextoController {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabCartelera;

    @FXML
    private Tab tabAsientos;

    @FXML
    private Tab tabConfiteria;

    @FXML
    private Tab tabCompra;

    @FXML
    private ComboBox<Pelicula> cmbPeliculas;

    @FXML
    private ListView<Funcion> lstFunciones;

    @FXML
    private Label lblInfoPelicula;

    @FXML
    private Label lblFuncionAsientos;

    @FXML
    private GridPane gridAsientos;

    @FXML
    private Label lblAsientosSeleccionados;

    @FXML
    private ComboBox<Producto> cmbProductos;

    @FXML
    private Spinner<Integer> spnProducto;

    @FXML
    private ComboBox<Combo> cmbCombos;

    @FXML
    private Spinner<Integer> spnCombo;

    @FXML
    private TextArea txtCarrito;

    @FXML
    private Label lblCliente;

    @FXML
    private Label lblSaldo;

    @FXML
    private Label lblPromocion;

    @FXML
    private Label lblSubtotal;

    @FXML
    private Label lblTotal;

    @FXML
    private TextArea txtResumen;

    private ContextoApp contexto;
    private Cliente clienteActual;
    private Funcion funcionSeleccionada;

    private final List<Asiento> asientosSeleccionados = new ArrayList<>();

    private final Map<Producto, Integer> productosSeleccionados = new LinkedHashMap<>();

    private final Map<Combo, Integer> combosSeleccionados = new LinkedHashMap<>();

    private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(
            "dd/MM/yyyy HH:mm");

    @FXML
    private void initialize() {

        spnProducto.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        1,
                        10,
                        1));

        spnCombo.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        1,
                        10,
                        1));

        configurarConversores();
    }

    private void configurarConversores() {

        cmbPeliculas.setConverter(
                new StringConverter<>() {

                    @Override
                    public String toString(
                            Pelicula pelicula) {

                        if (pelicula == null) {
                            return "";
                        }

                        return pelicula.getTitulo();
                    }

                    @Override
                    public Pelicula fromString(
                            String string) {

                        return null;
                    }
                });

        cmbProductos.setConverter(
                new StringConverter<>() {

                    @Override
                    public String toString(
                            Producto producto) {

                        if (producto == null) {
                            return "";
                        }

                        return producto.getNombre()
                                + " - "
                                + moneda(
                                        producto.getPrecio());
                    }

                    @Override
                    public Producto fromString(
                            String string) {

                        return null;
                    }
                });

        cmbCombos.setConverter(
                new StringConverter<>() {

                    @Override
                    public String toString(
                            Combo combo) {

                        if (combo == null) {
                            return "";
                        }

                        return combo.getNombre()
                                + " - "
                                + moneda(
                                        combo.getPrecio());
                    }

                    @Override
                    public Combo fromString(
                            String string) {

                        return null;
                    }
                });

        lstFunciones.setCellFactory(
                lista -> new ListCell<>() {

                    @Override
                    protected void updateItem(
                            Funcion funcion,
                            boolean empty) {

                        super.updateItem(
                                funcion,
                                empty);

                        if (empty
                                || funcion == null) {

                            setText(null);

                        } else {

                            setText(
                                    funcion.getFechaHora()
                                            .format(
                                                    formatoFecha)
                                            + " | "
                                            + funcion
                                                    .getSala()
                                                    .getNombre()
                                            + " | "
                                            + moneda(
                                                    funcion
                                                            .getPrecioEntrada()));
                        }
                    }
                });
    }

    @Override
    public void setContexto(
            ContextoApp contexto) {

        this.contexto = contexto;

        contexto.cargarDatosIniciales();

        buscarCliente();

        cargarDatosVista();
    }

    private void buscarCliente() {

        for (Usuario usuario : contexto
                .getRegistroUsuarios()
                .listar()) {

            if (usuario instanceof Cliente) {

                clienteActual = (Cliente) usuario;

                break;
            }
        }
    }

    private void cargarDatosVista() {

        cmbPeliculas.setItems(
                FXCollections.observableArrayList(
                        contexto
                                .getServicioCartelera()
                                .consultarPeliculasActivas()));

        cmbProductos.setItems(
                FXCollections.observableArrayList(
                        contexto
                                .getRegistroProductos()
                                .listar()));

        cmbCombos.setItems(
                FXCollections.observableArrayList(
                        contexto
                                .getRegistroCombos()
                                .listar()));

        if (!cmbPeliculas
                .getItems()
                .isEmpty()) {

            cmbPeliculas
                    .getSelectionModel()
                    .selectFirst();

            cargarFunciones();
        }

        if (!cmbProductos
                .getItems()
                .isEmpty()) {

            cmbProductos
                    .getSelectionModel()
                    .selectFirst();
        }

        if (!cmbCombos
                .getItems()
                .isEmpty()) {

            cmbCombos
                    .getSelectionModel()
                    .selectFirst();
        }

        actualizarDatosCliente();
        actualizarCarrito();
    }

    @FXML
    public void cargarFunciones() {

        if (contexto == null) {
            return;
        }

        Pelicula pelicula = cmbPeliculas.getValue();

        if (pelicula == null) {
            return;
        }

        lblInfoPelicula.setText(
                pelicula.getGenero()
                        + " | "
                        + pelicula
                                .getDuracionMinutos()
                        + " min | Clasificacion: "
                        + pelicula
                                .getClasificacion());

        List<Funcion> funciones = contexto
                .getServicioCartelera()
                .consultarFuncionesDisponibles(
                        pelicula);

        lstFunciones.setItems(
                FXCollections.observableArrayList(
                        funciones));

        if (!funciones.isEmpty()) {
            lstFunciones
                    .getSelectionModel()
                    .selectFirst();
        }
    }

    @FXML
    public void continuarAAsientos() {

        Funcion funcion = lstFunciones
                .getSelectionModel()
                .getSelectedItem();

        if (funcion == null) {

            mostrarError(
                    "Debe seleccionar una funcion");

            return;
        }

        funcionSeleccionada = funcion;

        asientosSeleccionados.clear();

        dibujarAsientos();

        tabPane
                .getSelectionModel()
                .select(tabAsientos);
    }

    private void dibujarAsientos() {

        gridAsientos.getChildren().clear();

        lblFuncionAsientos.setText(
                funcionSeleccionada
                        .getPelicula()
                        .getTitulo()
                        + " - "
                        + funcionSeleccionada
                                .getFechaHora()
                                .format(formatoFecha));

        Label pantalla = new Label(
                "PANTALLA");

        pantalla.setStyle(
                "-fx-font-weight: bold;"
                        + "-fx-background-color: #dddddd;"
                        + "-fx-padding: 8 80 8 80;");

        gridAsientos.add(
                pantalla,
                1,
                0,
                6,
                1);

        String filaAnterior = "";
        int filaGrid = 0;

        for (Asiento asiento : funcionSeleccionada
                .getMapaAsientos()) {

            if (!asiento
                    .getFila()
                    .equals(filaAnterior)) {

                filaAnterior = asiento.getFila();

                filaGrid++;

                Label lblFila = new Label(
                        filaAnterior);

                lblFila.setStyle(
                        "-fx-font-weight: bold;");

                gridAsientos.add(
                        lblFila,
                        0,
                        filaGrid);
            }

            Button boton = new Button(
                    asiento.toString());

            boton.setPrefSize(
                    60,
                    45);

            if (!asiento.estaDisponible()) {

                boton.setDisable(true);

                boton.setStyle(
                        "-fx-background-color: #777777;"
                                + "-fx-text-fill: white;");

            } else {

                estiloDisponible(boton);

                boton.setOnAction(
                        evento -> seleccionarAsiento(
                                asiento,
                                boton));
            }

            gridAsientos.add(
                    boton,
                    asiento.getNumero(),
                    filaGrid);
        }

        actualizarTextoAsientos();
    }

    private void seleccionarAsiento(
            Asiento asiento,
            Button boton) {

        if (asientosSeleccionados
                .contains(asiento)) {

            asientosSeleccionados
                    .remove(asiento);

            estiloDisponible(boton);

        } else {

            asientosSeleccionados
                    .add(asiento);

            boton.setStyle(
                    "-fx-background-color: #1565c0;"
                            + "-fx-text-fill: white;"
                            + "-fx-font-weight: bold;");
        }

        actualizarTextoAsientos();
    }

    private void estiloDisponible(
            Button boton) {

        boton.setStyle(
                "-fx-background-color: #2e7d32;"
                        + "-fx-text-fill: white;");
    }

    private void actualizarTextoAsientos() {

        if (asientosSeleccionados
                .isEmpty()) {

            lblAsientosSeleccionados
                    .setText(
                            "Ningun asiento seleccionado");

            return;
        }

        StringBuilder texto = new StringBuilder(
                "Seleccionados: ");

        for (Asiento asiento : asientosSeleccionados) {

            texto.append(
                    asiento).append(" ");
        }

        lblAsientosSeleccionados
                .setText(
                        texto.toString());
    }

    @FXML
    public void continuarAConfiteria() {

        if (asientosSeleccionados
                .isEmpty()) {

            mostrarError(
                    "Debe seleccionar al menos un asiento");

            return;
        }

        tabPane
                .getSelectionModel()
                .select(tabConfiteria);
    }

    @FXML
    public void agregarProducto() {

        Producto producto = cmbProductos.getValue();

        if (producto == null) {
            return;
        }

        int cantidad = spnProducto.getValue();

        productosSeleccionados.merge(
                producto,
                cantidad,
                Integer::sum);

        actualizarCarrito();
    }

    @FXML
    public void agregarCombo() {

        Combo combo = cmbCombos.getValue();

        if (combo == null) {
            return;
        }

        int cantidad = spnCombo.getValue();

        combosSeleccionados.merge(
                combo,
                cantidad,
                Integer::sum);

        actualizarCarrito();
    }

    @FXML
    public void limpiarConfiteria() {

        productosSeleccionados.clear();
        combosSeleccionados.clear();

        actualizarCarrito();
    }

    private void actualizarCarrito() {

        if (txtCarrito == null) {
            return;
        }

        StringBuilder texto = new StringBuilder();

        for (Map.Entry<Producto, Integer> item : productosSeleccionados
                .entrySet()) {

            texto.append(
                    item.getValue()).append(
                            " x ")
                    .append(
                            item.getKey()
                                    .getNombre())
                    .append(
                            " = ")
                    .append(
                            moneda(
                                    item.getKey()
                                            .getPrecio()
                                            * item.getValue()))
                    .append("\n");
        }

        for (Map.Entry<Combo, Integer> item : combosSeleccionados
                .entrySet()) {

            texto.append(
                    item.getValue()).append(
                            " x ")
                    .append(
                            item.getKey()
                                    .getNombre())
                    .append(
                            " = ")
                    .append(
                            moneda(
                                    item.getKey()
                                            .getPrecio()
                                            * item.getValue()))
                    .append("\n");
        }

        if (texto.length() == 0) {

            texto.append(
                    "Sin productos de confiteria");
        }

        txtCarrito.setText(
                texto.toString());
    }

    @FXML
    public void continuarACompra() {

        actualizarResumenCompra();

        tabPane
                .getSelectionModel()
                .select(tabCompra);
    }

    private void actualizarResumenCompra() {

        double subtotal = calcularSubtotal();

        Promocion promocion = obtenerPromocion();

        double total = subtotal;

        if (promocion != null) {

            total = promocion.aplicar(
                    subtotal,
                    LocalDate.now());

            lblPromocion.setText(
                    promocion.getNombre()
                            + " ("
                            + promocion
                                    .getPorcentajeDescuento()
                            + "%)");

        } else {

            lblPromocion.setText(
                    "Sin promocion");
        }

        lblSubtotal.setText(
                "Subtotal: "
                        + moneda(subtotal));

        lblTotal.setText(
                "Total a pagar: "
                        + moneda(total));

        StringBuilder resumen = new StringBuilder();

        resumen.append(
                "Pelicula: ").append(
                        funcionSeleccionada
                                .getPelicula()
                                .getTitulo())
                .append("\n");

        resumen.append(
                "Funcion: ").append(
                        funcionSeleccionada
                                .getFechaHora()
                                .format(formatoFecha))
                .append("\n");

        resumen.append(
                "Asientos: ");

        for (Asiento asiento : asientosSeleccionados) {

            resumen.append(
                    asiento).append(" ");
        }

        resumen.append(
                "\n\nConfiteria:\n");

        if (productosSeleccionados.isEmpty()
                && combosSeleccionados
                        .isEmpty()) {

            resumen.append(
                    "Sin productos adicionales\n");
        }

        for (Map.Entry<Producto, Integer> item : productosSeleccionados
                .entrySet()) {

            resumen.append(
                    "- ").append(
                            item.getValue())
                    .append(
                            " x ")
                    .append(
                            item.getKey()
                                    .getNombre())
                    .append("\n");
        }

        for (Map.Entry<Combo, Integer> item : combosSeleccionados
                .entrySet()) {

            resumen.append(
                    "- ").append(
                            item.getValue())
                    .append(
                            " x ")
                    .append(
                            item.getKey()
                                    .getNombre())
                    .append("\n");
        }

        txtResumen.setText(
                resumen.toString());

        actualizarDatosCliente();
    }

    private double calcularSubtotal() {

        double total = asientosSeleccionados.size()
                * funcionSeleccionada
                        .getPrecioEntrada();

        for (Map.Entry<Producto, Integer> item : productosSeleccionados
                .entrySet()) {

            total += item.getKey()
                    .getPrecio()
                    * item.getValue();
        }

        for (Map.Entry<Combo, Integer> item : combosSeleccionados
                .entrySet()) {

            total += item.getKey()
                    .getPrecio()
                    * item.getValue();
        }

        return total;
    }

    private Promocion obtenerPromocion() {

        for (Promocion promocion : contexto
                .getRegistroPromociones()
                .listar()) {

            if (promocion.estaVigente(
                    LocalDate.now())) {

                return promocion;
            }
        }

        return null;
    }

    @FXML
    public void confirmarCompra() {

        try {

            String codigoCompra = contexto
                    .siguienteCodigoCompra();

            Compra.Builder builder = new Compra.Builder()
                    .conCodigo(
                            codigoCompra)
                    .conCliente(
                            clienteActual);

            for (Asiento asiento : asientosSeleccionados) {

                Entrada entrada = new Entrada(
                        "ENT-"
                                + codigoCompra
                                + "-"
                                + asiento
                                        .getCodigo(),
                        funcionSeleccionada,
                        asiento);

                builder.conEntrada(
                        entrada);
            }

            for (Map.Entry<Producto, Integer> item : productosSeleccionados
                    .entrySet()) {

                builder.conProducto(
                        item.getKey(),
                        item.getValue());
            }

            for (Map.Entry<Combo, Integer> item : combosSeleccionados
                    .entrySet()) {

                builder.conCombo(
                        item.getKey(),
                        item.getValue());
            }

            Promocion promocion = obtenerPromocion();

            if (promocion != null) {

                builder.conPromocion(
                        promocion);
            }

            Compra compra = builder.build();

            contexto
                    .getServicioCompras()
                    .confirmarCompra(
                            compra);

            mostrarInformacion(
                    "Compra realizada",
                    "Compra "
                            + compra.getCodigo()
                            + " pagada correctamente.\n"
                            + "Total: "
                            + moneda(
                                    compra.calcularTotal()));

            limpiarCompra();

        } catch (DominioException exception) {

            mostrarError(
                    exception.getMessage());

        } catch (Exception exception) {

            mostrarError(
                    "No fue posible completar la compra: "
                            + exception.getMessage());
        }
    }

    private void limpiarCompra() {

        asientosSeleccionados.clear();
        productosSeleccionados.clear();
        combosSeleccionados.clear();

        actualizarCarrito();
        actualizarDatosCliente();

        if (funcionSeleccionada != null) {
            dibujarAsientos();
        }

        tabPane
                .getSelectionModel()
                .select(tabCartelera);
    }

    private void actualizarDatosCliente() {

        if (clienteActual == null) {
            return;
        }

        lblCliente.setText(
                "Cliente: "
                        + clienteActual
                                .getNombre());

        lblSaldo.setText(
                "Saldo tarjeta: "
                        + moneda(
                                clienteActual
                                        .getTarjetaVirtual()
                                        .getSaldo()));
    }

    private String moneda(
            double valor) {

        return String.format(
                "$%,.0f",
                valor);
    }

    private void mostrarError(
            String mensaje) {

        Alert alerta = new Alert(
                Alert.AlertType.ERROR);

        alerta.setTitle(
                "CinemaUQ");

        alerta.setHeaderText(
                "No se pudo realizar la operacion");

        alerta.setContentText(
                mensaje);

        alerta.showAndWait();
    }

    private void mostrarInformacion(
            String titulo,
            String mensaje) {

        Alert alerta = new Alert(
                Alert.AlertType.INFORMATION);

        alerta.setTitle(
                "CinemaUQ");

        alerta.setHeaderText(
                titulo);

        alerta.setContentText(
                mensaje);

        alerta.showAndWait();
    }

    @Override
    public void refrescar() {

        if (contexto != null) {
            cargarDatosVista();
        }
    }
}