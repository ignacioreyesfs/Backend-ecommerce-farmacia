package Order;

import Product.*;
import Recommendation.RecommendedWeather;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {

    Product ibuprofeno = new Product("Ibuprofeno", Unit.MG, 400, 120, Manufacturer.BAGO, RecommendedWeather.NORMAL);
    ItemProduct ibuprofenos3 = new ItemProduct(ibuprofeno, 3, "");
    ItemProduct ibuprofenos4 = new ItemProduct(ibuprofeno, 4, "");
    Order order = new Order("Ayacucho", 341, "Ring the bell and wait");

    @Test
    public void AddingAnExistingProductDoesNotAddAnother(){
        order.addItemProduct(ibuprofenos3);
        order.addItemProduct(ibuprofenos4);
        assertEquals(1, order.getItemProducts().size());
    }

    @Test
    public void CorrectQuantityWhenAddingSameProduct(){
        order.addItemProduct(ibuprofenos3);
        order.addItemProduct(ibuprofenos4);
        assertEquals(7, ibuprofenos3.getUnits());
    }
}
