package co.edu.uniquindio.pedidos.pedidosapp.controller;

import co.edu.uniquindio.pedidos.pedidosapp.model.Pedido;
import co.edu.uniquindio.pedidos.pedidosapp.persistencia.Persistencia;

import java.io.IOException;

public class PedidoController {

    public PedidoController() {

    }

    public boolean agregarPedido(Pedido pedido) {
        Persistencia persistencia = new Persistencia();

        try{
            persistencia.guardarPedidos(pedido);
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }
}
