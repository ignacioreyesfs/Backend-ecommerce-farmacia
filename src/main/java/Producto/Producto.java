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

    public double calcularPrecio() {
        return precio * condicion.porcentajeDescuento();
    }

}