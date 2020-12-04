package Product;

import Product.Offer.NotInDatabaseException;

import javax.persistence.EntityManager;

public class RepoProduct {
    private EntityManager em;

    public RepoProduct(EntityManager em){
        this.em = em;
    }

    public Product findProductById(int id){
        return em.find(Product.class, id);
    }

    public Product saveProduct(Product product){
        if(product.getId() == null){
            em.persist(product);
        }else{
            product = em.merge(product);
        }
        return product;
    }

    public void finishOfferByProductId(int productId){
        Product product = this.findProductById(productId);

        if(product == null){
            throw new NotInDatabaseException();
        }

        product.setCondition(Condition.NORMAL);
    }

}
