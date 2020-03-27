package Pedido;

import Producto.Producto;

public class ItemProducto {

    public ItemProducto(Producto producto, int cantidad, String observaciones){
        this.producto = producto;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
    }
    private Producto producto;
    private int cantidad;
    private String observaciones;

    public void aumentarCantidad(int cantidad){
        this.cantidad += cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }
}
