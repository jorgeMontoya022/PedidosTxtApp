package co.edu.uniquindio.pedidos.pedidosapp.builder;

import co.edu.uniquindio.pedidos.pedidosapp.builder.services.IBuilder;
import co.edu.uniquindio.pedidos.pedidosapp.model.Pedido;
import co.edu.uniquindio.pedidos.pedidosapp.model.Producto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoBuilder implements IBuilder<Pedido> {

    private LocalDate fechaPedido;

    private List<Producto> listaProductos = new ArrayList<>();


    public PedidoBuilder fechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
        return this;
    }



    public PedidoBuilder listaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
        return this;
    }

    @Override
    public Pedido build() {
        return new Pedido(fechaPedido, listaProductos);
    }
}
