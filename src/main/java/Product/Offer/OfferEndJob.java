package Product.Offer;

import Product.Product;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import Product.Condition;
import Product.RepoProduct;

public class OfferEndJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException{
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        int productId = dataMap.getInt("productId");
        Product product = RepoProduct.getInstance().findProductById(productId);

        if(product == null)
            throw new NotInDatabaseException();

        product.setCondition(Condition.NORMAL);
    }
}
