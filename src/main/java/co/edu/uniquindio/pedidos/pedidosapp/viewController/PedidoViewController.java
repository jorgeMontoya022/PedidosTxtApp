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
    private List<Producto> listaProductosTemp = new ArrayList<>();

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
    void initialize() {
        pedidoController = new PedidoController();


    }

    @FXML
    void onAgregarProducto(ActionEvent event) {
        if (validarFormulario()) {
            // Crear un producto con los datos ingresados y añadirlo a la lista temporal
            Producto producto = new ProductoBuilder()
                    .codigo(txtCodigoProducto.getText())
                    .nombre(txtNombreProducto.getText())
                    .precio(Double.parseDouble(txtValorProducto.getText()))
                    .build();

            listaProductosTemp.add(producto);
            mostrarMensaje("Notificación de producto", "Producto agregado", "El producto ha sido añadido a la lista", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Notificación de producto", "Producto no agregado", "Por favor complete los campos correctamente", Alert.AlertType.WARNING);
        }
    }



    @FXML
    void onAgregarPedido(ActionEvent event) {
        if (!listaProductosTemp.isEmpty() && dpFechaProducto.getValue() != null) {
            Pedido pedido = new PedidoBuilder()
                    .fechaPedido(dpFechaProducto.getValue())
                    .listaProductos(listaProductosTemp) // Usar la lista temporal
                    .build();

            if (pedidoController.agregarPedido(pedido)) {
                mostrarMensaje("Notificación del pedido", "Pedido creado", "El pedido ha sido agregado con éxito", Alert.AlertType.INFORMATION);
                listaProductosTemp.clear(); // Limpiar la lista temporal para un nuevo pedido
                limpiarCampos();
            } else {
                mostrarMensaje("Notificación del pedido", "Pedido no agregado", "El pedido no ha sido agregado con éxito", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación del pedido", "Pedido no agregado", "Debe ingresar productos y una fecha para el pedido", Alert.AlertType.WARNING);
        }
    }



    private void limpiarCampos() {
        txtValorProducto.setText("");
        txtNombreProducto.setText("");
        txtCodigoProducto.setText("");
        dpFechaProducto.setValue(null);
    }


    private boolean validarFormulario() {
        return !txtNombreProducto.getText().isEmpty()
                && !txtValorProducto.getText().isEmpty()
                && validarNumero(txtValorProducto.getText());
    }

    private boolean validarNumero(String texto) {
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }




    @FXML
    void onMostrarPedido(ActionEvent event) {

    }





    private void mostrarMensaje(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}