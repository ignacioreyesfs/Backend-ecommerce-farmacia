package Pedido;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Pedido {

    String calleEntrega;
    int alturaCalleEntrega;
    double precioPedido;
    Set<ProductoDetalle> productos = new HashSet<ProductoDetalle>();

    public void agregarProducto(ProductoDetalle producto){
        this.productos.add(producto);
    }

    public double precio(){
        return productos.stream().map(producto -> producto.getProducto().calcularPrecio()).reduce(0.0, Double::sum);
    }
}
