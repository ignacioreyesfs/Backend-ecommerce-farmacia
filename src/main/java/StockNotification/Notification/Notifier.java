package StockNotification.Notification;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Set;

public interface Notifier {
    public void stockNotification(Set<String> recipients, String product) throws GeneralSecurityException, IOException, MessagingException;
}
