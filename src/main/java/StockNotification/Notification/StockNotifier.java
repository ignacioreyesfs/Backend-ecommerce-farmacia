package StockNotification.Notification;

import Customer.Customer;
import StockNotification.Exceptions.ClientNotNotifiedException;

public class StockNotifier {

    public void notifyNewStock(Customer customer, String productName) throws ClientNotNotifiedException{
        try{
            customer.getNotifier().notifyNewProductStock(customer, productName);
        }catch(Exception e){
            throw new ClientNotNotifiedException();
        }
    }
}
