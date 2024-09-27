package co.edu.uniquindio.pedidos.pedidosapp.persistencia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoUtil {
    public static void guardarArchivo(String rutaArchivoPedidos, String contenido, boolean flagAnexarContenido) throws IOException {

        FileWriter fw = new FileWriter(rutaArchivoPedidos, flagAnexarContenido);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(contenido);
        bfw.close();
        fw.close();
    }
}
