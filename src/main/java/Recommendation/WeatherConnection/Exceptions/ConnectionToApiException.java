package Recommendation.WeatherConnection.Exceptions;

public class ConnectionToApiException extends RuntimeException{
    public ConnectionToApiException(){
        super("Problem getting data from the API requested");
    }
}
