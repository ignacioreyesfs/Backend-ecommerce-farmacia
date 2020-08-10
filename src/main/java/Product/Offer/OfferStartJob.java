package Product.Offer;

import Product.Product;
import Utilities.Persistence.GlobalEntityManagerFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import Product.Condition;
import Product.RepoProduct;

import javax.persistence.EntityManager;

public class OfferStartJob implements Job {

    EntityManager em = GlobalEntityManagerFactory.getInstance().getEntityManagerFactory().createEntityManager();
    RepoProduct repoProduct = new RepoProduct(em);

    public void execute(JobExecutionContext context){
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        Condition condition = (Condition)dataMap.get("condition");
        int productId = dataMap.getInt("productId");

        em.getTransaction().begin();

        Product product = repoProduct.findProductById(productId);
        if(product == null){
            em.getTransaction().rollback();
            throw new NotInDatabaseException();
        }
        product.setCondition(condition);
        repoProduct.saveProduct(product);

        em.getTransaction().commit();
    }
}
