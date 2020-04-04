package Recomendacion;

import Producto.*;
import Recomendacion.Clima.OpenWeatherAdapter;

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
public class RecomendacionTest {

    @Test
    public void recomiendaProductosEnBaseATemperatura(){
        //en vez de hacer Mockito.mock podrias agregar la annotation @Mock e instanciarlo normal
        //pero para hacer eso, la variable debe ser de la clase
        OpenWeatherAdapter weatherMock = Mockito.mock(OpenWeatherAdapter.class);
        when(weatherMock.obtenerTemperaturaAhora()).thenReturn(15.0);

        Producto ibuprofeno = new Producto("Ibuprofeno 400", 120, Fabricante.BAGO, Condicion.VEINTEPORCIENTO, ClimaPredominante.NORMAL);
        Producto panuelito = new Producto("Pañuelitos x20", 15, Fabricante.TISSUE, Condicion.NORMAL, ClimaPredominante.FRIO);
        Producto off = new Producto("Off 200ml", 150, Fabricante.OFF, Condicion.NORMAL, ClimaPredominante.CALOR);

        List<Producto> productos = new ArrayList<Producto>();
        productos.add(ibuprofeno);
        productos.add(panuelito);
        productos.add(off);

        Optional<Producto> productoOptional = productos.stream()
                .filter(prod -> prod.getClimaPredominante().recomendadoTemperatura(weatherMock.obtenerTemperaturaAhora()))
                .findFirst();

        assertEquals(panuelito, productoOptional.get());
    }
}
