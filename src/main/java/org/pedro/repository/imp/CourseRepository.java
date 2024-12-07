package org.pedro.repository.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import org.pedro.connection.ConnectionFactory;
import org.pedro.domain.Course;
import org.pedro.repository.ICourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CourseRepository implements ICourseRepository {


    @Override
    public void save(Course course) {

        EntityManager em = ConnectionFactory.getEntityManagerFactory();

        try {
            em.getTransaction().begin();

            em.persist(course);

            em.getTransaction().commit();

        } catch (PersistenceException e) {
            e.printStackTrace();

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

        } finally {
                em.close();

        }
    }

    @Override
    public void update(Course course) {

        EntityManager em = ConnectionFactory.getEntityManagerFactory();
        try {
            em.getTransaction().begin();
            em.merge(course);

            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();

            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        } finally {
                em.close();
        }
    }

    @Override
    public boolean removeById(Integer integer) {

        EntityManager em = ConnectionFactory.getEntityManagerFactory();

        boolean isRemoved = false;

        try{

            em.getTransaction().begin();

            Course course = em.find(Course.class, integer);

            if(course!= null){
                em.remove(course);
                isRemoved = true;

            }else{
                throw new NullPointerException("Course not foundwith id: " + integer);
            }

            em.getTransaction().commit();


        } catch (PersistenceException e) {
            e.printStackTrace();

            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }


        } finally {
                em.close();
        }

        return isRemoved;
    }

    @Override
    public Optional<Course> findById(Integer integer) {

        EntityManager em = ConnectionFactory.getEntityManagerFactory();
        Optional<Course> course = null;
        try {

            Course courseSearched = em.find(Course.class, integer);

           course = Optional.ofNullable(courseSearched);

            return course;


        } catch (PersistenceException e) {
            e.printStackTrace();

        } finally {
                em.close();
        }

        return course;
    }


    @Override
    public List<Course> findAll() {

            EntityManager em = ConnectionFactory.getEntityManagerFactory();

            List<Course> courses = new ArrayList<>();

        try{

            String listALlQuery = "SELECT c FROM Course c";

            em.getTransaction().begin();


           courses = em.createQuery(listALlQuery, Course.class).getResultList();

        }catch (PersistenceException e){

            e.printStackTrace();

        }finally {
            em.close();
        }

        return courses;
    }
}
