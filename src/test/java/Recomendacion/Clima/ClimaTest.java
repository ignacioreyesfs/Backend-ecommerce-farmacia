package Recomendacion.Clima;

import org.junit.Test;

public class ClimaTest {
    @Test
    public void correctoUsoApi(){
        OpenWeatherAdapter ow = new OpenWeatherAdapter();
        System.out.println(ow.obtenerTemperaturaAhora());
    }
}
