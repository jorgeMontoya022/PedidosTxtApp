package co.edu.uniquindio.pedidos.pedidosapp.persistencia;

import co.edu.uniquindio.pedidos.pedidosapp.model.Pedido;
import co.edu.uniquindio.pedidos.pedidosapp.model.Producto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

        textoPedido.append(pedido.getFechaPedido() + ",");
        textoPedido.append(pedido.getTotal() + ",");
        for (Producto producto : pedido.getListaProductos()) {
            textoPedido.append(producto.getCodigo() + ",");
            textoPedido.append(producto.getNombre() + ",");
            textoPedido.append(producto.getPrecio() + ",");
        }
        if (textoPedido.length() > 0) {
            textoPedido.setLength(textoPedido.length() - 1);
        }
        textoPedido.append("\n");

        ArchivoUtil.guardarArchivo(rutaArchivoPedidos, textoPedido.toString(), true);
    }


    public List<Pedido> cargarPedidos() throws IOException {
        String rutaArchivoPedidos = obtenerRutaProperties();
        List<String> contenido = ArchivoUtil.leerArchivo(rutaArchivoPedidos);
        List<Pedido> listaPedidos = new ArrayList<>();

        for (String linea : contenido) {
            String[] datos = linea.split(",");

            // Validar que al menos se tenga una fecha y un producto
            if (datos.length < 3) {
                System.out.println("Formato incorrecto en la línea: " + linea);
                continue; // Saltar esta línea si no tiene suficientes datos
            }

            List<Producto> productos = new ArrayList<>();
            for (int i = 2; i < datos.length; i += 3) {
                // Validar que el índice no se salga de los límites del arreglo
                if (i + 2 >= datos.length) {
                    System.out.println("Datos de producto incompletos en la línea: " + linea);
                    continue; // Saltar este producto si no tiene suficientes datos
                }

                Producto producto = new Producto(datos[i], datos[i + 1], Double.parseDouble(datos[i + 2]));
                productos.add(producto);
            }

            Pedido pedido = new Pedido(LocalDate.parse(datos[0]), productos);
            listaPedidos.add(pedido);
        }

        return listaPedidos;
    }


}
