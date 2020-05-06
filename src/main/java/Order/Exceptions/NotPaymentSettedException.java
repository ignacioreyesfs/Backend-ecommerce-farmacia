package Order.Exceptions;

public class NotPaymentSettedException extends RuntimeException{
    public NotPaymentSettedException(){
        super("Payment method not setted, please choose one.");
    }
}
