package org.pedro.repository.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.pedro.connection.ConnectionFactory;
import org.pedro.domain.Student;
import org.pedro.repository.IStudentRepository;
import org.pedro.repository.TransactionFunction;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentRepository implements IStudentRepository {

    private static final Logger LOGGER = Logger.getLogger(StudentRepository.class.getName());

    @Override
    public void save(Student student) {
        executeTransaction(em -> {
            em.persist(student);
            return null;
        } );
    }

    @Override
    public void update(Student student) {
        executeTransaction(em -> {
            em.merge(student);
            return null;
        });
    }

    @Override
    public boolean removeById(Integer id) {
        return Boolean.TRUE.equals(executeTransaction(em -> {
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
                return true;
            } else {
                return false;
            }
        }));

    }

    @Override
    public Optional<Student> findById(Integer integer) {

        return executeTransaction(em -> Optional.ofNullable(em.find(Student.class, integer)));
    }

    @Override
    public List<Student> findAll() {
       return executeTransaction(em -> em.createQuery("SELECT s FROM Student s", Student.class).getResultList());
    }

    private <T> T executeTransaction (TransactionFunction<T> function){

        EntityManager em = ConnectionFactory.getEntityManager();


        try {

            em.getTransaction().begin();

            T result = function.apply(em);

            em.getTransaction().commit();

            return result;


        }catch (PersistenceException e){

            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            LOGGER.log(Level.WARNING, "Error executing transaction", e);


        }finally {
            ConnectionFactory.closeEntityManager(em);
        }

        return null;
    }
}
