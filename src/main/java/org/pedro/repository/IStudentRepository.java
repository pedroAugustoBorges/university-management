package org.pedro.repository;

import org.pedro.domain.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository extends GenericRepository <Student, Integer>{

    @Override
    void save(Student entity);

    @Override
    void update(Student entity);

    @Override
    boolean removeById(Integer integer);

    @Override
    Optional<Student> findById(Integer integer);

    @Override
    List<Student> findAll();
}
