module co.edu.uniquindio.pedidos.pedidosapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.pedidos.pedidosapp to javafx.fxml;
    exports co.edu.uniquindio.pedidos.pedidosapp;

    opens co.edu.uniquindio.pedidos.pedidosapp.viewController;
    exports co.edu.uniquindio.pedidos.pedidosapp.viewController;
}