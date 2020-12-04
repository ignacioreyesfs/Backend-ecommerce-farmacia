package Product;

import Product.Offer.NotInDatabaseException;
import Utilities.Persistence.GlobalEntityManagerFactory;

import javax.persistence.EntityManager;

public class ProductService {

    public void finishOfferByProductId(int productId){
        EntityManager em = GlobalEntityManagerFactory.createEntityManager();
        RepoProduct repoProduct = new RepoProduct(em);
        em.getTransaction().begin();

        Product product = repoProduct.findProductById(productId);

        if(product == null){
            em.getTransaction().rollback();
            em.close();
            throw new NotInDatabaseException();
        }

        product.setCondition(Condition.NORMAL);
        repoProduct.saveProduct(product);

        em.getTransaction().commit();
        em.close();
    }

    public void startProductOffer(int productId, Condition condition){
        EntityManager em = GlobalEntityManagerFactory.getInstance().getEntityManagerFactory().createEntityManager();
        RepoProduct repoProduct = new RepoProduct(em);
        em.getTransaction().begin();

        Product product = repoProduct.findProductById(productId);
        if(product == null){
            em.getTransaction().rollback();
            em.close();
            throw new NotInDatabaseException();
        }

        product.setCondition(condition);
        repoProduct.saveProduct(product);

        em.getTransaction().commit();
        em.close();
    }

}
