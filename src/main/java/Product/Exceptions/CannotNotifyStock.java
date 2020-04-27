package Product.Exceptions;

public class CannotNotifyStock extends RuntimeException {
    public CannotNotifyStock(){
        super("The email to notify new stock to clients could't be sent");
    }
}
