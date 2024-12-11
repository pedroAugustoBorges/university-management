package org.pedro.repository.imp;

import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceException;
import org.pedro.connection.ConnectionFactory;
import org.pedro.domain.Course;
import org.pedro.repository.ICourseRepository;
import org.pedro.repository.TransactionFunction;

import java.util.List;
import java.util.Optional;

import java.util.logging.Logger;

public class CourseRepository implements ICourseRepository {

    private static final Logger LOGGER = Logger.getLogger(CourseRepository.class.getName());


    @Override
    public void save(Course course) {

        executeTransaction(em -> {
            em.persist(course);
            return null;
        });

    }

    @Override
    public void update(Course course) {
        executeTransaction(em -> {
            em.merge(course);
            return null;
        });
    }

    @Override
    public boolean removeById(Integer id) {

       return Boolean.TRUE.equals(executeTransaction(em -> {
           Course course = em.find(Course.class, id);
           if (course != null){
              em.remove(course);
              return true;
          }else {
              return false;
          }
       }));

    }


    @Override
    public Optional<Course> findById(Integer integer) {

        return executeTransaction(em -> Optional.ofNullable(em.find(Course.class, integer)));

    }


    @Override
    public List<Course> findAll() {
       return executeTransaction(em -> em.createQuery("SELECT c FROM Course c", Course.class).getResultList());
    }

    private <T> T executeTransaction(TransactionFunction<T> function) {
        EntityManager em = ConnectionFactory.getEntityManager();



        try {
            em.getTransaction().begin();

           T result = function.apply(em);

            em.getTransaction().commit();

            return result;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            LOGGER.severe("Erro executing transaction " + e.getMessage());
            throw e;

        } finally {
            ConnectionFactory.closeEntityManagerFactory(em);
        }
    }
}
