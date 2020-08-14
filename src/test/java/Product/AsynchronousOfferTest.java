package Product;

import Product.Offer.OfferEndJob;
import Product.Offer.OfferStartJob;
import Recommendation.RecommendedWeather;
import Utilities.CronExpression.CronExpression;
import Utilities.Persistence.GlobalEntityManagerFactory;
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
    private RepoProduct repoProduct;
    private Product ibuprofen;

    private Scheduler scheduler;
    @Before
    public void initialize() throws SchedulerException {
        // added cause Jobs works with product id
        em = GlobalEntityManagerFactory.getInstance().getEntityManagerFactory().createEntityManager();
        repoProduct = new RepoProduct(em);
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
        ibuprofen = new Product("Ibuprofen", Unit.MG, 400, 145, Manufacturer.BAGO, RecommendedWeather.NORMAL);

        em.getTransaction().begin();
        ibuprofen = repoProduct.saveProduct(ibuprofen);
        em.getTransaction().commit();

        scheduler.start();
    }

    @After
    public void finishTest(){
        em.close();
    }

    @Test
    public void startOfferTest() throws InterruptedException, SchedulerException {

        JobDetail job = JobBuilder.newJob(OfferStartJob.class)
                .withIdentity("testStart")
                .build();
        int ibuprofenId = ibuprofen.getId();
        job.getJobDataMap().put("productId", ibuprofenId);
        job.getJobDataMap().put("condition", Condition.TWENTYPERCENT);

        String cronExpression = CronExpression.getCronExpressionLocalDateTime(LocalDateTime.now());
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testStart")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .forJob("testStart")
                .build();

        scheduler.scheduleJob(job, trigger);
        TimeUnit.SECONDS.sleep(1);

        em.clear();
        ibuprofen = repoProduct.findProductById(ibuprofenId);

        assertEquals(Condition.TWENTYPERCENT, ibuprofen.getCondition());
    }

    @Test
    public void endOfferTest() throws SchedulerException, InterruptedException {
        em.getTransaction().begin();

        ibuprofen.setCondition(Condition.TWENTYPERCENT);
        ibuprofen = repoProduct.saveProduct(ibuprofen);

        em.getTransaction().commit();

        JobDetail job = JobBuilder.newJob(OfferEndJob.class)
                .withIdentity("testStart")
                .build();
        int ibuprofenId = ibuprofen.getId();
        job.getJobDataMap().put("productId", ibuprofenId);

        String cronExpression = CronExpression.getCronExpressionLocalDateTime(LocalDateTime.now());
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testStart")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .forJob("testStart")
                .build();

        scheduler.scheduleJob(job, trigger);

        TimeUnit.SECONDS.sleep(1);

        em.clear();
        ibuprofen = repoProduct.findProductById(ibuprofenId);

        assertEquals(Condition.NORMAL, ibuprofen.getCondition());
    }

}
