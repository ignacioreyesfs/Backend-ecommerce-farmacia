package StockNotification;

import Product.Product;

import javax.persistence.*;
import java.util.Optional;

public final class RepoAwaitedProduct {
    private EntityManager em;

    public RepoAwaitedProduct(EntityManager em){
        this.em = em;
    }

    public Optional<AwaitedProduct> findAwaitedByProduct(Product product){
        String queryString = "SELECT * FROM AwaitedProduct WHERE product_id = :productID";
        Query query = em.createQuery(queryString);
        query.setParameter("productID", product.getId());
        return query.getResultList().stream().findFirst();
    }

    public void removeAwaited(AwaitedProduct awaitedProduct){
        em.remove(awaitedProduct);
    }
}
