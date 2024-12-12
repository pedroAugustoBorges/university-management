package org.pedro.repository.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.pedro.connection.ConnectionFactory;
import org.pedro.domain.Course;
import org.pedro.domain.Enrollment;
import org.pedro.domain.Student;
import org.pedro.repository.IEnrollmentRepository;
import org.pedro.repository.TransactionFunction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnrollmentRepository implements IEnrollmentRepository {

    @Override
    public void save(Integer studentId, Integer courseId) {
        executeTransaction(em -> {
            Course course = em.find(Course.class, courseId);

            Student student = em.find(Student.class, studentId);

            if (student == null){
                throw new EntityNotFoundException("Student not found with id : " + studentId);
            }
            if (course == null){
                throw new EntityNotFoundException("Course not found with id : " + courseId);
            }

            Enrollment enrollment =  new Enrollment();

            enrollment.setCourse(course);
            enrollment.setStudent(student);
            enrollment.setLocalDateTime(LocalDateTime.now());

            em.persist(enrollment);

            return null;
        });
    }

    @Override
    public void save(Enrollment enrollment) {
     executeTransaction(em -> {
         em.persist(enrollment);
         return null;
     });
    }

    @Override
    public void update(Enrollment enrollment) {
        executeTransaction(em -> {
            em.merge(enrollment);
            return null;
        });
    }

    @Override
    public boolean removeById(Integer integer) {
        return Boolean.TRUE.equals(executeTransaction(em -> {

            Enrollment enrollment = em.find(Enrollment.class, integer);

            if (enrollment != null) {
                em.remove(enrollment);
                return true;
            } else {
                return false;
            }

        }));

    }

    @Override
    public Optional<Enrollment> findById(Integer integer) {
        return executeTransaction(em -> Optional.ofNullable(em.find(Enrollment.class, integer)));
    }

    @Override
    public List<Enrollment> findAll() {
        String jpql = "SELECT e FROM Enrollment e " +
        "JOIN FETCH e.student s " +
                "JOIN FETCH e.course c";

        return executeTransaction(em -> em.createQuery(jpql, Enrollment.class).getResultList());
    }

    @Override
    public List<Enrollment> findByIdStudent(Integer studentId){

      String jpql = "SELECT e FROM Enrollment e " +
              "JOIN FETCH e.student s " +
              "JOIN FETCH e.course c " +
              "WHERE s.id = :studentId";

        return executeTransaction(em -> em.createQuery(jpql, Enrollment.class).setParameter("studentId", studentId).getResultList());
    }

    @Override
    public List<Enrollment> findByIdCourse(Integer courseId) {
       String jpql = "SELECT e FROM Enrollment e " +
               "JOIN FETCH e.student s " +
               "JOIN FETCH e.course c " +
               "WHERE c.id = :courseId";

        return executeTransaction(em -> em.createQuery(jpql, Enrollment.class).setParameter("courseId", courseId).getResultList());
    }

    private <T> T executeTransaction (TransactionFunction<T> function){
        EntityManager em = ConnectionFactory.getEntityManager();

        try{

            em.getTransaction().begin();

            T result = function.apply(em);

            em.getTransaction().commit();

            return result;

        }catch (PersistenceException e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }finally {
            ConnectionFactory.closeEntityManager(em);
        }


       return null;
    }
}
