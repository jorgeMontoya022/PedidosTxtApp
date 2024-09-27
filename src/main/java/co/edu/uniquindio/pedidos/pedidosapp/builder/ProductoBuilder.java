package co.edu.uniquindio.pedidos.pedidosapp.builder;

import co.edu.uniquindio.pedidos.pedidosapp.builder.services.IBuilder;
import co.edu.uniquindio.pedidos.pedidosapp.model.Producto;

public class ProductoBuilder implements IBuilder {

    private String codigo;
    private String nombre;
    private double precio;

    public ProductoBuilder codigo(String codigo){
        this.codigo = codigo;
        return this;
    }
    public ProductoBuilder nombre(String nombre){
        this.nombre = nombre;
        return this;
    }
    public ProductoBuilder precio(double precio){
        this.precio = precio;
        return this;
    }

    @Override
    public Producto build() {
        return new Producto(codigo, nombre, precio);
    }

}
