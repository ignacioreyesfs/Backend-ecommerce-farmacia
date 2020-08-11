package Utilities.Persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class GlobalEntityManagerFactory {

    private EntityManagerFactory emf;
    private static GlobalEntityManagerFactory gemf;

    public static GlobalEntityManagerFactory getInstance(){
        if(gemf == null){
            gemf = new GlobalEntityManagerFactory();
        }
        return gemf;
    }

    public EntityManagerFactory getEntityManagerFactory(){
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("pharmacy");
        }
        return emf;
    }
}
