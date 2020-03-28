package Producto;

public class Producto {

    public Producto(String nombre, double precio, Laboratorio laboratorio, Condicion condicion) {
        this.nombre = nombre;
        this.precio = precio;
        this.laboratorio = laboratorio;
        this.condicion = condicion;
    }

    String nombre;
    double precio;
    Laboratorio laboratorio;
    Condicion condicion;
    int stock;

    public double calcularPrecio() {
        return precio * condicion.porcentajeDescuento();
    }

    public void aumentarStock(int cantidad){
        this.stock += cantidad;
    }

    public void disminuirStock(int cantidad){
        this.stock -= cantidad;
    }

    public int getStock() {
        return stock;
    }

}