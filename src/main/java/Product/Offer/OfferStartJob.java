package Product.Offer;

import Product.Product;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import Product.Condition;
import Product.RepoProduct;

public class OfferStartJob implements Job {

    public void execute(JobExecutionContext context){
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        Condition condition = (Condition)dataMap.get("condition");
        int productId = dataMap.getInt("productId");
        Product product = RepoProduct.getInstance().findProductById(productId);

        if(product == null)
            throw new NotInDatabaseException();

        product.setCondition(condition);
    }
}
