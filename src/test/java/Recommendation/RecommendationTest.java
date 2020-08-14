package Recommendation;

import Product.*;
import Recommendation.WeatherConnection.OpenWeatherAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RecommendationTest {

    @Test
    public void recommendsProductBasedOnTemperature(){
        OpenWeatherAdapter weatherMock = Mockito.mock(OpenWeatherAdapter.class);
        when(weatherMock.getTemperatureNow()).thenReturn(15.0);

        Product ibuprofeno = new Product("Ibuprofeno", Unit.MG, 400, 120, Manufacturer.BAGO, RecommendedWeather.NORMAL);
        Product panuelito = new Product("Pañuelitos", Unit.UNITS, 20, 15, Manufacturer.TISSUE, RecommendedWeather.COLD);
        Product off = new Product("Off", Unit.ML, 200, 150, Manufacturer.OFF, RecommendedWeather.HOT);

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
