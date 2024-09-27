package co.edu.uniquindio.pedidos.pedidosapp.viewController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private List<Producto> listaProductos = new ArrayList<>();

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
    void onLimpiarCampos(ActionEvent event) {
        limpiarCampos();

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

            listaProductos.add(producto);
            mostrarMensaje("Notificación de producto", "Producto agregado", "El producto ha sido añadido a la lista", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Notificación de producto", "Producto no agregado", "Por favor complete los campos correctamente", Alert.AlertType.WARNING);
        }
    }


    @FXML
    void onAgregarPedido(ActionEvent event) {
        if (!listaProductos.isEmpty() && dpFechaProducto.getValue() != null) {
            Pedido pedido = new PedidoBuilder()
                    .fechaPedido(dpFechaProducto.getValue())
                    .listaProductos(listaProductos)
                    .build();

            if (pedidoController.agregarPedido(pedido)) {
                mostrarMensaje("Notificación del pedido", "Pedido creado", "El pedido ha sido agregado con éxito", Alert.AlertType.INFORMATION);
                listaProductos.clear(); // Limpiar la lista para un nuevo pedido
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
        txtAreaResultado.setText("");
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
        List<Pedido> pedidos = pedidoController.mostrarListaPedidos();
        StringBuilder resultado = new StringBuilder();

        for (Pedido pedido : pedidos) {
            resultado.append("Fecha: ").append(pedido.getFechaPedido()).append("\n");
            resultado.append("Total: ").append(pedido.getTotal()).append("\n");
            resultado.append("Productos:\n");

            for (Producto producto : pedido.getListaProductos()) {
                resultado.append(" - Código: ").append(producto.getCodigo())
                        .append(", Nombre: ").append(producto.getNombre())
                        .append(", Precio: ").append(producto.getPrecio()).append("\n");
            }
            resultado.append("-----------------------------\n");
        }

        txtAreaResultado.setText(resultado.toString());
    }



    private void mostrarMensaje(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}