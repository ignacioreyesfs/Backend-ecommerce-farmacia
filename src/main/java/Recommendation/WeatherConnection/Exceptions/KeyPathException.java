package Recommendation.WeatherConnection.Exceptions;

public class KeyPathException extends RuntimeException {
    public KeyPathException(){
        super("Cannot find the key path");
    }
}
