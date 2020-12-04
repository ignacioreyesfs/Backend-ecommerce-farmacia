package Product.Offer;

import Product.ProductService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class OfferEndJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException{
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        int productId = dataMap.getInt("productId");
        ProductService productService = new ProductService();
        productService.finishOfferByProductId(productId);
    }
}
