package Producto;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductoTest {
    @Test
    public void calculoPrecio(){
        Producto desodorante = new Producto("Rexona Clinical", 120, Laboratorio.BAGO, Condicion.VEINTEPORCIENTO);
        assertEquals(96, desodorante.calcularPrecio(), 0);
    }
}