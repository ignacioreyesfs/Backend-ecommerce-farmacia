package Product.Offer;

import Product.ProductService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import Product.Condition;

public class OfferStartJob implements Job {


    public void execute(JobExecutionContext context){
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        Condition condition = (Condition)dataMap.get("condition");
        int productId = dataMap.getInt("productId");

        ProductService productService = new ProductService();
        productService.startProductOffer(productId, condition);

    }
}
