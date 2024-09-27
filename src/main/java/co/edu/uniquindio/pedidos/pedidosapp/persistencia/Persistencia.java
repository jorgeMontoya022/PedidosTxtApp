package co.edu.uniquindio.pedidos.pedidosapp.persistencia;

import co.edu.uniquindio.pedidos.pedidosapp.model.Pedido;
import co.edu.uniquindio.pedidos.pedidosapp.model.Producto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Persistencia {

    private String obtenerRutaProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("C:/Users/57324/Documents/PedidosTxtApp/src/main/java/co/edu/uniquindio/pedidos/pedidosapp/properties/ruta.properties")));
            return properties.get("rutaArchivoPedidos").toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public void guardarPedidos(Pedido pedido) throws IOException {
        String rutaArchivoPedidos = obtenerRutaProperties();
        StringBuilder textoPedido = new StringBuilder();

        textoPedido.append(pedido.getFechaPedido()+ ",");
        textoPedido.append(pedido.getTotal()+ ",");
        for(Producto producto: pedido.getListaProductos()){
            textoPedido.append(producto.getCodigo()+ ",");
            textoPedido.append(producto.getNombre()+ ",");
            textoPedido.append(producto.getPrecio()+ ",");
        }
        if (textoPedido.length() > 0) {
            textoPedido.setLength(textoPedido.length()-1);
        }
        textoPedido.append("\n");

        ArchivoUtil.guardarArchivo(rutaArchivoPedidos, textoPedido.toString(), true);
    }


}
