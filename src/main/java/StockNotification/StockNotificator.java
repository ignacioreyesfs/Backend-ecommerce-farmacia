package StockNotification;

import Product.Exceptions.CannotNotifyStock;
import Product.Product;
import Utilities.MailSender.GmailSender;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

// called when product.increaseStock() is used
public class StockNotificator {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pharmacy");
    EntityManager em = emf.createEntityManager();
    RepoAwaitedProduct repoAwaitedProduct = new RepoAwaitedProduct(em);

    public void notifyCustomers(Product product){
        Optional<AwaitedProduct> awaitedProduct = repoAwaitedProduct.findAwaitedByProduct(product);
        if(awaitedProduct.isPresent()){
            try {
                new GmailSender().newStockEmailNotification(awaitedProduct.get().getClientsEmails(), product.getName());
                repoAwaitedProduct.removeAwaited(awaitedProduct.get());
            } catch (Exception e) {
                // TODO: retry
                e.printStackTrace();
                throw new CannotNotifyStock();
            }
        }
    }
}
