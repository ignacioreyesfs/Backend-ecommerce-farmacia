package StockNotification;

import Product.Exceptions.CannotNotifyStock;
import Product.Product;
import Utilities.MailSender.GmailSender;

import java.util.*;

// called when product.increaseStock() is used
public class StockNotificator {

    public void notifyCustomers(Product product){
        Optional<AwaitedProduct> awaitedProduct = RepoAwaitedProduct.getInstance().findAwaitedByProduct(product);
        if(awaitedProduct.isPresent()){
            try {
                new GmailSender().newStockEmailNotification(awaitedProduct.get().getClientsEmails(), product.getName());
                RepoAwaitedProduct.getInstance().removeAwaited(awaitedProduct.get());
            } catch (Exception e) {
                // TODO: retry
                e.printStackTrace();
                throw new CannotNotifyStock();
            }
        }
    }
}
