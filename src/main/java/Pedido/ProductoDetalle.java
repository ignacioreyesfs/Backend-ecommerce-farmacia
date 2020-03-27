package Pedido;

import Producto.Producto;

public class ProductoDetalle {

    public ProductoDetalle(Producto producto, int cantidad, String observaciones){
        this.producto = producto;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
    }
    private Producto producto;
    private int cantidad;
    private String observaciones;

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
