package Recomendacion.Clima;

import Recomendacion.Clima.Exceptions.MensajeErrorApiException;
import Recomendacion.Clima.Exceptions.ProblemaConexionApiException;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenWeatherAdapter implements ProveedorClima {

    //TODO: ocultar mi api key
    private static String API_KEY = "3d8400ed6a740b8d6684b988a5d0b4d9";
    private String urlTemperaturaAhora = "http://api.openweathermap.org/data/2.5/weather?q=Buenos%20Aires,Argentina&appid=" + API_KEY;

    //Usando HttpUrlConnection
    //Usar OkHttp Library (abajo), es mas simple. Dejo este como ejemplo de HttpUrlConnection
    public double obtenerTemperaturaAhoraHttpURlConnection() {
        HttpURLConnection con;
        URL url;
        String resultado = null;
        String temperaturaString;
        try {
            url = new URL(urlTemperaturaAhora);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            //me fijo que este andando la url
            if(con.getResponseCode() != 200)
                throw new ProblemaConexionApiException();

            //Paso la data de inputStream a string en formato json.
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String linea;
            try {
                while ((linea = br.readLine()) != null) {
                    sb.append(linea);
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

        //Obtengo el value de la key json en formato string
        JSONParser parse = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) parse.parse(resultado);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Aca fijarse como viene el JSON, si es un array usar JSONArray
        temperaturaString = ((JSONObject)jsonObject.get("main")).get("temp").toString();

        return this.kelvinACelsius(Double.parseDouble(temperaturaString));
    }

    //Usando OkHttp library
    @Override
    public double obtenerTemperaturaAhora(){
        double temperatura;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(urlTemperaturaAhora).build();
        Response response;
        String jsonBody;
        try {
             response = client.newCall(request).execute();

             //Verifico que la api responda 200 (OK)
            if(!response.isSuccessful())
                throw new MensajeErrorApiException(response.code());

             // este es el body del json
             jsonBody = response.body().string();

        } catch (IOException e) {
            throw new ProblemaConexionApiException();
        }

        //obtengo el campo temperatura como string del body json
        JSONParser parse = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)parse.parse(jsonBody);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Aca fijarse como viene el JSON, si es un array usar JSONArray
        String temperaturaString = ((JSONObject)jsonObject.get("main")).get("temp").toString();

        return kelvinACelsius(Double.parseDouble(temperaturaString));
    }

    private double kelvinACelsius(double kelvin){
        return kelvin - 273;
    }
}
