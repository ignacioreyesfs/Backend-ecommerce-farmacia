package Order;

import Order.Exceptions.InsufficientStockException;
import Order.Exceptions.ZeroUnitsException;
import Product.*;
import Recommendation.RecommendedWeather;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {
    Product ibuprofeno;
    ItemProduct ibuprofenoItem1;
    ItemProduct ibuprofenoItem2;
    Order order;

    @Before
    public void initialize(){
        ibuprofeno = new Product("Ibuprofeno", Unit.MG, 400, 120, Manufacturer.BAGO, RecommendedWeather.NORMAL);
        ibuprofeno.increaseStock(100);
        ibuprofenoItem1 = new ItemProduct(ibuprofeno, 3, "");
        ibuprofenoItem2 = new ItemProduct(ibuprofeno, 4, "");
        order = new Order("Ayacucho", 341, "Ring the bell and wait");
    }

    @Test
    public void AddingAnExistingProductDoesNotAddAnother(){
        order.addItemProduct(ibuprofenoItem1);
        order.addItemProduct(ibuprofenoItem2);
        assertEquals(1, order.getItemProducts().size());
    }

    @Test
    public void CorrectQuantityWhenAddingSameProduct(){
        order.addItemProduct(ibuprofenoItem1);
        order.addItemProduct(ibuprofenoItem2);
        assertEquals(7, ibuprofenoItem1.getUnits());
    }

    @Test(expected = InsufficientStockException.class)
    public void itemCannotAddMoreUnitsThanStock(){
        ibuprofenoItem1.incrementUnits(100);
    }

    @Test(expected = ZeroUnitsException.class)
    public void itemCannotHasZeroUnits(){
        ibuprofenoItem1.decrementUnit();
        ibuprofenoItem1.decrementUnit();
        ibuprofenoItem1.decrementUnit();
    }
}
