package co.edu.uniquindio.pedidos.pedidosapp.persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUtil {
    public static void guardarArchivo(String rutaArchivoPedidos, String contenido, boolean flagAnexarContenido) throws IOException {

        FileWriter fw = new FileWriter(rutaArchivoPedidos, flagAnexarContenido);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(contenido);
        bfw.close();
        fw.close();
    }

    public static ArrayList<String> leerArchivo(String ruta) throws IOException {
        ArrayList<String> contenido = new ArrayList<>();

        FileReader fr = new FileReader(ruta);
        BufferedReader bfr = new BufferedReader(fr);

        String linea;
        while ((linea = bfr.readLine()) != null) {
            contenido.add(linea);
        }

        bfr.close();
        fr.close();
        return contenido;
    }

}
