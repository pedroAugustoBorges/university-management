package org.pedro.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public interface TransactionFunction <T>{

    T apply(EntityManager entityManager) throws PersistenceException;
}
