package co.edu.uniquindio.pedidos.pedidosapp.viewController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import co.edu.uniquindio.pedidos.pedidosapp.builder.PedidoBuilder;
import co.edu.uniquindio.pedidos.pedidosapp.builder.ProductoBuilder;
import co.edu.uniquindio.pedidos.pedidosapp.controller.PedidoController;
import co.edu.uniquindio.pedidos.pedidosapp.model.Pedido;
import co.edu.uniquindio.pedidos.pedidosapp.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PedidoViewController {
    PedidoController pedidoController;

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
        if(validarFormulario()){
            Pedido pedido = buildDataPedido();
            if(pedidoController.agregarPedido(pedido)){
                mostrarMensaje("Notificación del pedido ","Pedido creado", "El pedido ha sido agregado con éxito", Alert.AlertType.INFORMATION);
            }else{
                mostrarMensaje("Notificación del pedido","Pedido no agregado", "El pedido no ha sido agregado con éxito", Alert.AlertType.ERROR);
            }

        }else {
            mostrarMensaje("Notificación del pedido","Pedido no agregado", "rellene todos los espacios", Alert.AlertType.ERROR);
        }


    }

    private boolean validarFormulario() {
        return !txtNombreProducto.getText().isEmpty()
                && !txtValorProducto.getText().isEmpty()
                && dpFechaProducto.getValue()!=null
                && validarNumero(txtValorProducto.getText());

    }

    private boolean validarNumero(String texto) {
        try {
            Double.parseDouble(texto);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }


    @FXML
    void onMostrarPedido(ActionEvent event) {

    }

    @FXML
    void initialize() {
        pedidoController = new PedidoController();

    }

    private Pedido buildDataPedido() {
        // Crear la lista de productos
        List<Producto> listaProductos = new ArrayList<>();

        // Construir un producto con los valores ingresados
        Producto producto = new ProductoBuilder()
                .codigo(txtCodigoProducto.getText())
                .nombre(txtNombreProducto.getText())
                .precio(Double.parseDouble(txtValorProducto.getText()))
                .build();

        // Agregar el producto a la lista
        listaProductos.add(producto);

        // Retornar un pedido construido con el builder
        return new PedidoBuilder()
                .fechaPedido(dpFechaProducto.getValue()) // Fecha tomada del DatePicker
                .listaProductos(listaProductos) // Lista de productos
                .build();
    }

    private void mostrarMensaje(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}