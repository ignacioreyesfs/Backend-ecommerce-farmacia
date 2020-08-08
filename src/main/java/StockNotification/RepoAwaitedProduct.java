package StockNotification;

import Product.Product;

import javax.persistence.*;
import java.util.Optional;

public final class RepoAwaitedProduct {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pharmacy");
    private EntityManager em = emf.createEntityManager();
    private static RepoAwaitedProduct instance;

    public static RepoAwaitedProduct getInstance(){
        if(instance == null)
            instance = new RepoAwaitedProduct();

        return instance;
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
