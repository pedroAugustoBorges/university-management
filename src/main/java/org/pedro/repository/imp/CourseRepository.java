package org.pedro.repository.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.pedro.connection.ConnectionFactory;
import org.pedro.domain.Course;
import org.pedro.repository.ICourseRepository;
import org.pedro.repository.TransactionFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
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
    public boolean removeById(Integer integer) {

        AtomicBoolean isBoolean = new AtomicBoolean(false);

        executeTransaction((EntityManager em) -> {
            Course course = em.find(Course.class, integer);

            if (course != null) {
                em.remove(course);
                isBoolean.set(true);
            } else {
                throw new EntityNotFoundException("Course not found with id : " + integer);
            }
            return null;
        });
        return isBoolean.get();
    }


    @Override
    public Optional<Course> findById(Integer integer) {

        EntityManager em = ConnectionFactory.getEntityManagerFactory();

        Optional<Course> courseOptional = Optional.empty();

        try{

            Course courseSearched = em.find(Course.class, integer);

            courseOptional = Optional.ofNullable(courseSearched);

        }catch (PersistenceException e){
            e.printStackTrace();

        }finally {
            em.close();
        }

        return courseOptional;


    }


    @Override
    public List<Course> findAll() {

            EntityManager em = ConnectionFactory.getEntityManagerFactory();

            List<Course> courses = new ArrayList<>();

        try{
            String listALlQuery = "SELECT c FROM Course c";

           courses = em.createQuery(listALlQuery, Course.class).getResultList();

        }catch (PersistenceException e){

            e.printStackTrace();

        }finally {
            em.close();
        }
        return courses;
    }

    private <T> T executeTransaction(TransactionFunction<T> function) {
        EntityManager em = ConnectionFactory.getEntityManagerFactory();



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
            em.close();
        }
    }
}
