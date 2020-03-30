package Recomendacion.Clima;

import Recomendacion.Clima.Exceptions.ProblemaConexionApiException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenWeatherAdapter implements ProveedorClima {
    @Override
    public double obtenerTemperaturaAhora() {
        //TODO: ocultar mi api key
        //Usando httpUrlConnection
        HttpURLConnection con;
        URL url;
        String resultado = null;
        String temperaturaString;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Buenos%20Aires,Argentina&appid=3d8400ed6a740b8d6684b988a5d0b4d9");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            //me fijo que este andando la url
            if(con.getResponseCode() != 200)
                throw new ProblemaConexionApiException();

            //Leo la data como string en formato json.
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                resultado = sb.toString();
                br.close();
            }catch(Exception e){
                e.printStackTrace();
            }

            con.disconnect();

        }catch (Exception e){
            throw new ProblemaConexionApiException();
        }

        //Obtengo el objeto value de la key json en formato string deseado
        JSONParser parse = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) parse.parse(resultado);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        temperaturaString = ((JSONObject)jsonObject.get("main")).get("temp").toString();

        return this.kelvinToCelsius(Double.parseDouble(temperaturaString));
    }

    private double kelvinToCelsius(double kelvin){
        return kelvin - 273;
    }
}
