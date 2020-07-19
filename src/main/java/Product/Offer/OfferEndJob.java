package Product.Offer;

import Product.Product;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import Product.Condition;

public class OfferEndJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException{
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        //TODO: when persistence is implemented, change to product id
        Product product = (Product)dataMap.get("product");

        product.setCondition(Condition.NORMAL);
    }
}
