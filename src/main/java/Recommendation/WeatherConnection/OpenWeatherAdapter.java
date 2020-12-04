package Recommendation.WeatherConnection;

import Recommendation.WeatherConnection.Exceptions.KeyPathException;

import Utilities.APIUtility.APIUtility;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class OpenWeatherAdapter implements WeatherProvider {
    private final String KEY_PATH = "weather_keys.txt";
    private final String KEY_NAME = "OPEN_WEATHER";
    private final String URL_BUENOSAIRES = "http://api.openweathermap.org/data/2.5/weather?q=Buenos%20Aires,Argentina&appid=";
    private final String URL_TEMPERATURE_BSAS_NOW = URL_BUENOSAIRES + this.getOpenWeatherKey();

    private String getOpenWeatherKey(){
        Map<String, String> properties = this.getPropertiesFromFile(KEY_PATH);
        return properties.get(KEY_NAME);
    }

    private Map<String, String> getPropertiesFromFile(String filePath){
        String delimiter = "=";
        Map<String, String> properties = new HashMap<>();

        try{
            Stream<String> lines = Files.lines(Paths.get(this.getClass().getResource(KEY_PATH).getPath()));
            lines.filter(line -> line.contains(delimiter))
                    .forEach(line -> properties.putIfAbsent(line.split(delimiter)[0], line.split(delimiter)[1]));
        }catch(IOException e){
            throw new KeyPathException();
        }

        return properties;
    }

    public double getTemperatureNow(){
        String stringTemperature = this.getValueOfTempFieldFromAPI(URL_TEMPERATURE_BSAS_NOW);

        return this.kelvinToCelsius(Double.parseDouble(stringTemperature));
    }

    private String getValueOfTempFieldFromAPI(String urlGet){
        APIUtility apiUtility = new APIUtility();

        JSONObject jobjResponse = apiUtility.getJSONObjectResponse(urlGet);
        return ((JSONObject)jobjResponse.get("main")).get("temp").toString();
    }

    private double kelvinToCelsius(double kelvin){
        return kelvin - 273;
    }
}
