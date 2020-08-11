package StockNotification;

import Product.Exceptions.CannotNotifyStock;
import Product.Product;
import Utilities.MailSender.GmailSender;
import Utilities.Persistence.GlobalEntityManagerFactory;

import javax.persistence.EntityManager;
import java.util.*;

// call after product.increaseStock() is called
public class StockNotificator {

    EntityManager em = GlobalEntityManagerFactory.getInstance().getEntityManagerFactory().createEntityManager();
    RepoAwaitedProduct repoAwaitedProduct = new RepoAwaitedProduct(em);

    public void notifyCustomers(Product product){
        Optional<AwaitedProduct> awaitedProduct = repoAwaitedProduct.findAwaitedByProduct(product);
        if(awaitedProduct.isPresent()){
            try {
                new GmailSender().newStockEmailNotification(awaitedProduct.get().getClientsEmails(), product.getName());
                em.getTransaction().begin();
                repoAwaitedProduct.removeAwaited(awaitedProduct.get());
                em.getTransaction().commit();
            } catch (Exception e) {
                // TODO: retry
                e.printStackTrace();
                throw new CannotNotifyStock();
            }
        }
    }
}
