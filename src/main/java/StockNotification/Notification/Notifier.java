package StockNotification.Notification;

import Customer.Customer;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

public interface Notifier {
    public void notifyNewProductStock(Customer customer, String product) throws GeneralSecurityException, IOException, MessagingException;
}
