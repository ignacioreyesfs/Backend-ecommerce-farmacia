package StockNotification;

import Product.Exceptions.CannotNotifyStock;
import Product.Product;
import Utilities.MailSender.GmailSender;

import java.util.*;

// called when product.increaseStock() is used
public class StockNotificator {

    private StockNotificator instance;
    private HashMap<Product, Set<String>> productClients = new HashMap<Product, Set<String>>();

    public StockNotificator getInstance(){
        if(instance == null){
            instance = new StockNotificator();
        }
        return instance;
    }

    public void addClient(Product product, String email){
        if(productClients.containsKey(product))
            productClients.get(product).add(email);
        else{
            Set<String> emails = new HashSet<>();
            emails.add(email);
            productClients.put(product, emails);
        }
    }

    public void notifyCustomers(Product product){
        if(productClients.containsKey(product)){
            try {
                new GmailSender().newStockEmailNotification(productClients.get(product), product.getName());
                productClients.remove(product);
            } catch (Exception e) {
                // TODO: retry
                e.printStackTrace();
                throw new CannotNotifyStock();
            }
        }
    }
}
