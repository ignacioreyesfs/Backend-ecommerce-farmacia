package Recomendacion.Clima.Exceptions;

public class ConnectionToWeatherException extends RuntimeException{
    public ConnectionToWeatherException(){
        super("Problem getting data from the Weather API");
    }
}
