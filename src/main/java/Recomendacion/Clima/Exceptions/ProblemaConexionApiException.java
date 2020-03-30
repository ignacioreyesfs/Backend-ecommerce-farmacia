package Recomendacion.Clima.Exceptions;

public class ProblemaConexionApiException extends RuntimeException{
    public ProblemaConexionApiException() {
        super("Problemas con obtener data de la API");
    }
}
