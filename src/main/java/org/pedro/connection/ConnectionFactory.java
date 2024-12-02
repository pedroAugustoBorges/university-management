package org.pedro.connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionFactory {

    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("myPU");

    public static EntityManager getEntityManagerFactory (){
        return EMF.createEntityManager();
    }


}
