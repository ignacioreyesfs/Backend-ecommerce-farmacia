package Recomendacion.Clima.Exceptions;

public class MensajeErrorApiException extends RuntimeException {
    public MensajeErrorApiException(int code){
        super("Error api code: " + code);
    }
}
