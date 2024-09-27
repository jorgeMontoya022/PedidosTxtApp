package co.edu.uniquindio.pedidos.pedidosapp.controller;

import co.edu.uniquindio.pedidos.pedidosapp.model.Pedido;
import co.edu.uniquindio.pedidos.pedidosapp.persistencia.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {

    public PedidoController() {

    }

    public boolean agregarPedido(Pedido pedido) {
        Persistencia persistencia = new Persistencia();

        try {
            persistencia.guardarPedidos(pedido);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<Pedido> mostrarListaPedidos() {
        Persistencia persistencia = new Persistencia();
        List<Pedido> pedidos = new ArrayList<>();

        try {
            pedidos = persistencia.cargarPedidos();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pedidos;
    }
}
