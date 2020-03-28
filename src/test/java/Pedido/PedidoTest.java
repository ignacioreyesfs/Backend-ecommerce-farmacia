package Pedido;

import Producto.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class PedidoTest {

    Producto ibuprofeno = new Producto("Ibuprofeno 400", 120, Fabricante.BAGO, Condicion.VEINTEPORCIENTO);
    ItemProducto ibuprofenos3 = new ItemProducto(ibuprofeno, 3, "");
    ItemProducto ibuprofenos4 = new ItemProducto(ibuprofeno, 4, "");
    Pedido pedido = new Pedido("Ayacucho", 341, "Tocar timbre y esperar");

    @Test
    public void agregarProductoExisteteNoAñadeOtro(){
        pedido.agregarItemProducto(ibuprofenos3);
        pedido.agregarItemProducto(ibuprofenos4);
        assertEquals(1, pedido.getItemProductos().size());
    }

    @Test
    public void cantidadCorrectaAlAgregarProductoExistente(){
        pedido.agregarItemProducto(ibuprofenos3);
        pedido.agregarItemProducto(ibuprofenos4);
        assertEquals(7, ibuprofenos3.getCantidad());
    }
}
