package co.edu.uniquindio.pedidos.pedidosapp.model;

import co.edu.uniquindio.pedidos.pedidosapp.builder.ProductoBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private LocalDate fechaPedido;
    private double total;
    private List<Producto> listaProductos = new ArrayList<>();

    public Pedido(){
        inicializarDatos();

    }



    private void inicializarDatos() {
        Producto producto1 = new ProductoBuilder()
                .nombre("Pala")
                .codigo("1234")
                .precio(1000000)
                .build();

        Producto producto2 = new ProductoBuilder()
                .nombre("Taladro")
                .codigo("5678")
                .precio(250000)
                .build();

        Producto producto3 = new ProductoBuilder()
                .nombre("Martillo")
                .codigo("9101")
                .precio(75000)
                .build();

        Producto producto4 = new ProductoBuilder()
                .nombre("Sierra Eléctrica")
                .codigo("1121")
                .precio(500000)
                .build();

        Producto producto5 = new ProductoBuilder()
                .nombre("Destornillador")
                .codigo("3141")
                .precio(15000)
                .build();

        Producto producto6 = new ProductoBuilder()
                .nombre("Llave Inglesa")
                .codigo("5161")
                .precio(45000)
                .build();

        Producto producto7 = new ProductoBuilder()
                .nombre("Alicates")
                .codigo("7181")
                .precio(25000)
                .build();

        Producto producto8 = new ProductoBuilder()
                .nombre("Cinta Métrica")
                .codigo("9202")
                .precio(10000)
                .build();

        listaProductos.add(producto1);
        listaProductos.add(producto2);
        listaProductos.add(producto3);
        listaProductos.add(producto4);
        listaProductos.add(producto5);
        listaProductos.add(producto6);
        listaProductos.add(producto7);
        listaProductos.add(producto8);


    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    private void calcularTotal() {
        total = 0; // Reinicia el total
        for (Producto producto : listaProductos) {
            total += producto.getPrecio(); // Suma los precios de los productos
        }
    }
}
