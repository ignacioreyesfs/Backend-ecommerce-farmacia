package Product.Offer;

import Product.Product;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import Product.Condition;

public class OfferStartJob implements Job {

    public void execute(JobExecutionContext context){
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        Condition condition = (Condition)dataMap.get("condition");
        // TODO: when persistence is implemented, change it to product id
        Product product = (Product)dataMap.get("product");

        product.setCondition(condition);
    }
}
