package org.pedro.connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class ConnectionFactory {

    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("myPU");

    public static EntityManager getEntityManagerFactory (){

        EntityManager entityManager = null;

        try {
            entityManager = EMF.createEntityManager();
        }catch (PersistenceException e){
            throw new RuntimeException("Error creating EntityManager", e);
        }
        return entityManager;
    }


}
