package org.pedro.connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static final String PERSISTENCE_UNIT_NAME = "myPU";

    private static EntityManagerFactory entityManagerFactory;

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

    private ConnectionFactory() {

    }

    public static EntityManager getEntityManager() {

        if (entityManagerFactory == null) {

            try {
                LOGGER.info("Criando EntityManager");
                entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            } catch (PersistenceException | NullPointerException e) {
                LOGGER.log(Level.SEVERE, "Erro ao criar EntityMangerFactory");
                throw e;
            }

        }

        if (!entityManagerFactory.isOpen()) {
            LOGGER.severe("Tentativa de usar um entityManager fechado");
            throw new IllegalStateException("Entity manager is closed");
        }

        LOGGER.info("Criando entityManager");

        return entityManagerFactory.createEntityManager();

    }

    public static void closeEntityManagerFactory (){
        if (entityManagerFactory != null && entityManagerFactory.isOpen()){
            entityManagerFactory.close();
        }
    }

    public static void closeEntityManager (EntityManager em){
        if (em != null && em.isOpen()){
            em.close();
        }
    }

}


