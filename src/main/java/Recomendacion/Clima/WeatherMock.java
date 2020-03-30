package Recomendacion.Clima;

public class WeatherMock implements ProveedorClima {
    public WeatherMock(double temperatura){
        this.temperatura = temperatura;
    }

    double temperatura;

    @Override
    public double obtenerTemperaturaAhora() {
        return temperatura;
    }
}
