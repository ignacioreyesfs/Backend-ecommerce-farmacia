package Recommendation.WeatherConnection;

import Recommendation.WeatherConnection.Exceptions.KeyPathException;
import Utilities.APIUtility.APIUtility;

import Recommendation.WeatherConnection.Exceptions.ConnectionToWeatherException;
import okhttp3.Response;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class OpenWeatherAdapter implements WeatherProvider {

    //TODO: hide api key (put it on a text file and read it)
    private final String KEY_PATH = "src/main/resources/weather_keys.txt";
    private final String KEY_NAME = "OPEN_WEATHER";
    private final String URL_BUENOSAIRES = "http://api.openweathermap.org/data/2.5/weather?q=Buenos%20Aires,Argentina&appid=";
    private final String URL_TEMPERATURE_BSAS_NOW = URL_BUENOSAIRES + this.getKey();

    public double getTemperatureNow(){
        APIUtility apiu = new APIUtility();
        Response response;
        JSONObject jobjResponse = null;
        // TODO: try again instead of throwing an exception
        try {
            response = apiu.sendGetRequest(URL_TEMPERATURE_BSAS_NOW, null);
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

    private String getKey(){
        String delimiter = ":";
        Map<String, String> map = new HashMap<>();
        try{
            Stream<String> lines = Files.lines(Paths.get(KEY_PATH));
            lines.filter(line -> line.contains(delimiter))
                    .forEach(line -> map.putIfAbsent(line.split(delimiter)[0], line.split(delimiter)[1]));
        }catch(IOException e){
            throw new KeyPathException();
        }

        return map.get(KEY_NAME);

    }
}
