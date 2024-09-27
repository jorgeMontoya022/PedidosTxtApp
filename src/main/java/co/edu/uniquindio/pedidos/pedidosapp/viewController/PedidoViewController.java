package co.edu.uniquindio.pedidos.pedidosapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PedidoViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dpFechaProducto;

    @FXML
    private TextArea txtAreaResultado;

    @FXML
    private TextField txtCodigoProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtValorProducto;

    @FXML
    void onAgregarPedido(ActionEvent event) {


    }

    @FXML
    void onMostrarPedido(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}