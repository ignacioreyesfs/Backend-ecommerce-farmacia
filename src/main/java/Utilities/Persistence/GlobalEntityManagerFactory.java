package Utilities.Persistence;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class GlobalEntityManagerFactory {

    private EntityManagerFactory emf;
    private static GlobalEntityManagerFactory gem;

    public static GlobalEntityManagerFactory getInstance(){
        if(gem == null){
            gem = new GlobalEntityManagerFactory();
        }
        return gem;
    }

    public EntityManagerFactory getEntityManagerFactory(){
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("pharmacy");
        }
        return emf;
    }
}
