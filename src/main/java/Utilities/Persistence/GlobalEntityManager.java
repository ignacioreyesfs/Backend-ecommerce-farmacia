package Utilities.Persistence;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class GlobalEntityManager {

    private EntityManagerFactory emf;
    private EntityManager em;
    private static GlobalEntityManager gem;

    public static GlobalEntityManager getInstance(){
        if(gem == null){
            gem = new GlobalEntityManager();
        }
        return gem;
    }

    public EntityManager getEntityManager(){
        if(em == null){
            emf = Persistence.createEntityManagerFactory("pharmacy");
            em = emf.createEntityManager();
        }
        return em;
    }
}
