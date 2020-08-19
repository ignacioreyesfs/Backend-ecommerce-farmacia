package StockNotification;

import Product.Exceptions.CannotNotifyStock;
import Product.Product;
import StockNotification.Notification.Notifier;
import Utilities.Persistence.GlobalEntityManagerFactory;

import javax.persistence.EntityManager;
import java.util.*;

// call after product.increaseStock() is called
public class StockNotificator {

    private EntityManager em = GlobalEntityManagerFactory.getInstance().getEntityManagerFactory().createEntityManager();
    private RepoAwaitedProduct repoAwaitedProduct = new RepoAwaitedProduct(em);
    private Notifier notifier;

    public StockNotificator(Notifier notifier){
        this.notifier = notifier;
    }

    public void notifyCustomers(Product product){
        Optional<AwaitedProduct> awaitedProduct = repoAwaitedProduct.findAwaitedByProduct(product);
        if(awaitedProduct.isPresent()){
            try {
                notifier.stockNotification(awaitedProduct.get().getClientsEmails(), product.getName());
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
