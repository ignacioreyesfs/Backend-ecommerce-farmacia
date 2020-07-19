package Recommendation;

import Product.*;
import Recommendation.WeatherConnection.OpenWeatherAdapter;

import Utilities.CronExpression.CronExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RecommendationTest {

    @Test
    public void recommendsProductBasedOnTemperature(){
        // insted of doing Mockito.mock u could add @Mock annotation and do a normal instantiation
        // but for doing that, the variable must be global for the class.
        OpenWeatherAdapter weatherMock = Mockito.mock(OpenWeatherAdapter.class);
        when(weatherMock.getTemperatureNow()).thenReturn(15.0);

        Product ibuprofeno = new Product("Ibuprofeno 400", 120, Manufacturer.BAGO, Weather.NORMAL);
        Product panuelito = new Product("Pañuelitos x20", 15, Manufacturer.TISSUE, Weather.COLD);
        Product off = new Product("Off 200ml", 150, Manufacturer.OFF, Weather.HOT);

        List<Product> products = new ArrayList<Product>();
        products.add(ibuprofeno);
        products.add(panuelito);
        products.add(off);

        Optional<Product> productOptional = products.stream()
                .filter(prod -> prod.getWeather().recommendedTemperature(weatherMock.getTemperatureNow()))
                .findFirst();

        assertEquals(panuelito, productOptional.get());
    }
}
