package Product;

import Recommendation.Weather;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    @Test
    public void priceCalculation(){
        Product ibuprofeno = new Product("Ibuprofeno 400", 120, Manufacturer.BAGO, Condition.TWENTYPERCENT, Weather.NORMAL);
        assertEquals(96, ibuprofeno.calculatePrice(), 0);
    }
}