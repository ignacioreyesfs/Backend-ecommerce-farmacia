package Recommendation.WeatherConnection;

import Utilities.APIUtility.APIUtility;

import Recommendation.WeatherConnection.Exceptions.ConnectionToWeatherException;
import okhttp3.Response;
import org.json.simple.JSONObject;

import java.io.IOException;

public class OpenWeatherAdapter implements WeatherProvider {

    //TODO: hide api key (put it on a text file and read it)
    private static final String API_KEY = "3d8400ed6a740b8d6684b988a5d0b4d9";
    private final String urlTemperatureNow = "http://api.openweathermap.org/data/2.5/weather?q=Buenos%20Aires,Argentina&appid=" + API_KEY;

    //Usando HttpUrlConnection
    //Usar OkHttp Library (abajo), es mas simple. Dejo este como ejemplo de HttpUrlConnection
    public double getTemperatureNow(){
        APIUtility apiu = new APIUtility();
        Response response;
        JSONObject jobjResponse = null;
        // TODO: try again instead of throwing an exception
        try {
            response = apiu.sendGetRequest(urlTemperatureNow, null);
            jobjResponse = apiu.getJsonObjectResponse(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectionToWeatherException();
        }

        String stringTemperature = ((JSONObject)jobjResponse.get("main")).get("temp").toString();

        return this.kelvinToCelsius(Double.parseDouble(stringTemperature));
    }


    private double kelvinToCelsius(double kelvin){
        return kelvin - 273;
    }
}
