package Product;

import javax.persistence.EntityManager;
import Utilities.Persistence.GlobalEntityManager;

public class RepoProduct {
    private static RepoProduct instance;
    private EntityManager em = GlobalEntityManager.getInstance().getEntityManager();

    public static RepoProduct getInstance(){
        if(instance == null)
            instance = new RepoProduct();

        return instance;
    }

    public Product findProductById(int id){
        return em.find(Product.class, id);
    }

    public Product saveProduct(Product product){
        return em.merge(product);
    }

}
