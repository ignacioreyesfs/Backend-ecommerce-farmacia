package Product.Offer;

import Product.Product;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import Product.Condition;

public class OfferEndJob implements Job {
    private Product product;

    public OfferEndJob(Product product){
        this.product = product;
    }

    public void execute(JobExecutionContext arg0) throws JobExecutionException{
        product.setCondition(Condition.NORMAL);
    }
}
