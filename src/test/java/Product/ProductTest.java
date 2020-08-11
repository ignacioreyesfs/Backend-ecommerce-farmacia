package Product;

import Recommendation.Weather;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    @Test
    public void priceCalculation(){
        Product ibuprofeno = new Product("Ibuprofeno", Unit.MG, 400, 120, Manufacturer.BAGO, Weather.NORMAL);
        ibuprofeno.setCondition(Condition.TWENTYPERCENT);
        assertEquals(96, ibuprofeno.calculatePrice(), 0);
    }
}