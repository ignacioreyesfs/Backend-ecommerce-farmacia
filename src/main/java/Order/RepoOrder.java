package Order;


import javax.persistence.EntityManager;

public class RepoOrder {
    private EntityManager em;

    public RepoOrder(EntityManager em){
        this.em = em;
    }

    public Order findProductById(int id){
        return em.find(Order.class, id);
    }

    public Order saveOrder(Order order){
        if(order.getId() == null){
            em.persist(order);
        }else{
            order = em.merge(order);
        }
        return order;
    }
}
