package StockNotification.Exceptions;

public class ClientNotNotifiedException extends RuntimeException {
    public ClientNotNotifiedException(){
        super("The new product stock was not notified to the costumer because the selected notified method failed");
    }
}
