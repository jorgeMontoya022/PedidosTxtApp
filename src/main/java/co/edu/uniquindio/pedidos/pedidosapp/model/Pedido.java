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
