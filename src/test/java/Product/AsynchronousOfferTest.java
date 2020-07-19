package Product;

import Product.Offer.OfferEndJob;
import Product.Offer.OfferStartJob;
import Recommendation.Weather;
import Utilities.CronExpression.CronExpression;
import org.junit.Before;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class AsynchronousOfferTest {

    private Product ibuprofeno = new Product("Ibuprofeno 400mg", 145, Manufacturer.BAGO, Weather.NORMAL);
    private Scheduler scheduler;
    @Before
    public void initialize() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
        scheduler.start();
    }



    @Test
    public void startOfferTest() throws InterruptedException, SchedulerException {

        JobDetail job = JobBuilder.newJob(OfferStartJob.class)
                .withIdentity("testStart")
                .build();
        job.getJobDataMap().put("product", ibuprofeno);
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
        job.getJobDataMap().put("product", ibuprofeno);

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
