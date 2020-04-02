package Recomendacion;

import Producto.*;
import Recomendacion.Clima.WeatherMock;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecomendacionTest {

    @Test
    public void recomiendaProductosEnBaseATemperatura(){
        WeatherMock weatherMock = new WeatherMock(15);
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
