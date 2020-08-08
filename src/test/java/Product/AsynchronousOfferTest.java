package Product;

import Product.Offer.OfferEndJob;
import Product.Offer.OfferStartJob;
import Recommendation.Weather;
import Utilities.CronExpression.CronExpression;
import Utilities.Persistence.GlobalEntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class AsynchronousOfferTest {
    private EntityManager em;
    private Product ibuprofeno;

    private Scheduler scheduler;
    @Before
    public void initialize() throws SchedulerException {
        // added cause Jobs works with product id
        em = GlobalEntityManager.getInstance().getEntityManager();
        em.getTransaction().begin();

        ibuprofeno = new Product("Ibuprofeno 400mg", 145, Manufacturer.BAGO, Weather.NORMAL);

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        ibuprofeno = RepoProduct.getInstance().saveProduct(ibuprofeno);
    }

    @After
    public void finish(){
        em.getTransaction().rollback();
    }

    @Test
    public void startOfferTest() throws InterruptedException, SchedulerException {

        JobDetail job = JobBuilder.newJob(OfferStartJob.class)
                .withIdentity("testStart")
                .build();
        job.getJobDataMap().put("productId", ibuprofeno.getId());
        job.getJobDataMap().put("condition", Condition.TWENTYPERCENT);

        String cronExpression = CronExpression.getCronExpressionLocalDateTime(LocalDateTime.now());
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testStart")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .forJob("testStart")
                .build();

        scheduler.scheduleJob(job, trigger);

        TimeUnit.SECONDS.sleep(1);

        assertEquals(Condition.TWENTYPERCENT, ibuprofeno.getCondition());
    }

    @Test
    public void endOfferTest() throws SchedulerException, InterruptedException {
        ibuprofeno.setCondition(Condition.TWENTYPERCENT);

        JobDetail job = JobBuilder.newJob(OfferEndJob.class)
                .withIdentity("testStart")
                .build();
        job.getJobDataMap().put("productId", ibuprofeno.getId());

        String cronExpression = CronExpression.getCronExpressionLocalDateTime(LocalDateTime.now());
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testStart")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .forJob("testStart")
                .build();

        scheduler.scheduleJob(job, trigger);

        TimeUnit.SECONDS.sleep(1);

        assertEquals(Condition.NORMAL, ibuprofeno.getCondition());
    }

}
