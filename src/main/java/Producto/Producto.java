package Producto;

public class Producto {

    public Producto(String nombre, double precio, Fabricante fabricante, Condicion condicion) {
        this.nombre = nombre;
        this.precio = precio;
        this.fabricante = fabricante;
        this.condicion = condicion;
    }

    String nombre;
    double precio;
    Fabricante fabricante;
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

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }
}