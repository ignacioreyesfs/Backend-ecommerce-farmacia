package Producto;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductoTest {
    @Test
    public void calculoPrecio(){
        Producto ibuprofeno = new Producto("Ibuprofeno 400", 120, Fabricante.BAGO, Condicion.VEINTEPORCIENTO);
        assertEquals(96, ibuprofeno.calcularPrecio(), 0);
    }
}