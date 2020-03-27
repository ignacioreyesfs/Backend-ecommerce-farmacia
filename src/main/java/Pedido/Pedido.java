package Pedido;

import Producto.Producto;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Pedido {

    String calleEntrega;
    int alturaCalleEntrega;
    double precioPedido;
    String observacionesGenerales;
    Set<ItemProducto> itemProductos = new HashSet<ItemProducto>();

    public Pedido(String calleEntrega, int alturaCalleEntrega, String observacionesGenerales){
        this.calleEntrega = calleEntrega;
        this.alturaCalleEntrega = alturaCalleEntrega;
        this.observacionesGenerales = observacionesGenerales;
    }

    public void agregarItemProducto(ItemProducto itemProducto){
        Optional<ItemProducto> itemBuscadoOptional = buscarProducto(itemProducto.getProducto());

        itemBuscadoOptional.ifPresentOrElse(
                (itemReal) ->
                    {itemReal.aumentarCantidad(itemProducto.getCantidad());},
                () ->
                    {
                        itemProductos.add(itemProducto);});
    }

    private Optional<ItemProducto> buscarProducto(Producto producto){
        return itemProductos.stream().filter(prod -> prod.getProducto() == producto)
                .findFirst();
    }

    public double precio(){
        return itemProductos.stream().map(producto -> producto.getProducto().calcularPrecio()).reduce(0.0, Double::sum);
    }

    public String getCalleEntrega() {
        return calleEntrega;
    }

    public int getAlturaCalleEntrega() {
        return alturaCalleEntrega;
    }

    public double getPrecioPedido() {
        return precioPedido;
    }

    public String getObservacionesGenerales() {
        return observacionesGenerales;
    }

    public Set<ItemProducto> getItemProductos() {
        return itemProductos;
    }
}
