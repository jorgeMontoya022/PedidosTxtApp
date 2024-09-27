package co.edu.uniquindio.pedidos.pedidosapp.model;

import co.edu.uniquindio.pedidos.pedidosapp.builder.ProductoBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private LocalDate fechaPedido;
    private double total;
    private List<Producto> listaProductos = new ArrayList<>();

    public Pedido() {
        // No llamamos a inicializarDatos() para evitar problemas.
        // Solo inicializa el pedido vacío.
    }

    // Constructor para inicializar un pedido con datos quemados (predefinidos)
    public Pedido(LocalDate fechaPedido, List<Producto> productos) {
        this.fechaPedido = fechaPedido;
        this.listaProductos = productos;
        calcularTotal();  // Calcula el total automáticamente al inicializar
    }

    // Método para inicializar una lista de pedidos predefinidos
    public static List<Pedido> crearPedidosPredefinidos() {
        List<Pedido> pedidos = new ArrayList<>();

        // Crea los productos para el primer pedido
        List<Producto> productos1 = new ArrayList<>();
        productos1.add(new ProductoBuilder().nombre("Pala").codigo("1234").precio(1000000).build());
        productos1.add(new ProductoBuilder().nombre("Taladro").codigo("5678").precio(250000).build());
        productos1.add(new ProductoBuilder().nombre("Martillo").codigo("9101").precio(75000).build());

        // Crea un primer pedido con los productos1 y lo agrega a la lista de pedidos
        pedidos.add(new Pedido(LocalDate.of(2024, 9, 21), productos1));

        // Crea los productos para el segundo pedido
        List<Producto> productos2 = new ArrayList<>();
        productos2.add(new ProductoBuilder().nombre("Sierra Eléctrica").codigo("1121").precio(500000).build());
        productos2.add(new ProductoBuilder().nombre("Destornillador").codigo("3141").precio(15000).build());

        // Crea un segundo pedido con los productos2 y lo agrega a la lista de pedidos
        pedidos.add(new Pedido(LocalDate.of(2024, 9, 22), productos2));


        return pedidos;  // Retorna la lista de pedidos predefinidos
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
        calcularTotal();  // Recalcula el total si la lista cambia
    }

    public void calcularTotal() {
        total = 0;
        for (Producto producto : listaProductos) {
            total += producto.getPrecio();
        }
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "fechaPedido=" + fechaPedido +
                ", total=" + total +
                ", listaProductos=" + listaProductos +
                '}';
    }
}
