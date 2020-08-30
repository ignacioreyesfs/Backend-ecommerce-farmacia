package Persistence;

import Order.*;
import Product.*;
import Recommendation.RecommendedWeather;
import Utilities.Persistence.GlobalEntityManagerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ORMTest {
    private EntityManager em;
    private RepoProduct repoProduct;
    private RepoOrder repoOrder;

    @Before
    public void initialize(){
        em = GlobalEntityManagerFactory.getInstance().getEntityManagerFactory().createEntityManager();
        repoProduct = new RepoProduct(em);
        repoOrder = new RepoOrder(em);
        em.getTransaction().begin();
    }

    @After
    public void finish(){
        em.getTransaction().rollback();
        em.close();
    }

    @Test
    public void OrderPersistTest(){
        Product ibuprofen = new Product("Ibuprofen", Unit.MG, 600, 125, Manufacturer.BAGO, RecommendedWeather.NORMAL);
        ItemProduct itemIbuprofen = new ItemProduct(ibuprofen, 3);
        Order order = new Order("Liniers", 773);
        order.addItemProduct(itemIbuprofen);

        em.persist(order);

        Product ibuprofenPersistent = repoProduct.findProductById(ibuprofen.getId());
        assertEquals(ibuprofen.getId(), ibuprofenPersistent.getId());
        assertEquals(Condition.NORMAL, ibuprofenPersistent.getCondition());

        ibuprofen.setCondition(Condition.TWENTYPERCENT);
        ibuprofen = repoProduct.saveProduct(ibuprofen);
        ibuprofenPersistent = repoProduct.findProductById((ibuprofen.getId()));

        assertEquals(Condition.TWENTYPERCENT, ibuprofenPersistent.getCondition());
    }

}
