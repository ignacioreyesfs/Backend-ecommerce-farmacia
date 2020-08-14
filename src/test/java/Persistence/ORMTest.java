package Persistence;

import Product.*;
import Recommendation.RecommendedWeather;
import StockNotification.AwaitedProduct;
import StockNotification.RepoAwaitedProduct;
import Utilities.Persistence.GlobalEntityManagerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

public class ORMTest {
    private EntityManager em;
    private RepoProduct repoProduct;
    private RepoAwaitedProduct repoAwaitedProduct;

    @Before
    public void initialize(){
        em = GlobalEntityManagerFactory.getInstance().getEntityManagerFactory().createEntityManager();
        repoAwaitedProduct = new RepoAwaitedProduct(em);
        repoProduct = new RepoProduct(em);
        em.getTransaction().begin();
    }

    @After
    public void finish(){
        em.getTransaction().rollback();
        em.close();
    }

    @Test
    public void awaitedAndProductPersistTest(){
        Product ibuprofen = new Product("Ibuprofen", Unit.MG, 600, 125, Manufacturer.BAGO, RecommendedWeather.NORMAL);
        AwaitedProduct awaitedIbuprofen = new AwaitedProduct(ibuprofen);

        awaitedIbuprofen = repoAwaitedProduct.saveAwaitedProduct(awaitedIbuprofen);

        Product ibuprofenPersistent = repoProduct.findProductById(ibuprofen.getId());
        assertEquals(ibuprofen.getId(), ibuprofenPersistent.getId());
        assertEquals(Condition.NORMAL, ibuprofenPersistent.getCondition());

        ibuprofen.setCondition(Condition.TWENTYPERCENT);
        ibuprofen = repoProduct.saveProduct(ibuprofen);
        ibuprofenPersistent = repoProduct.findProductById((ibuprofen.getId()));

        assertEquals(Condition.TWENTYPERCENT, ibuprofenPersistent.getCondition());
    }

}
