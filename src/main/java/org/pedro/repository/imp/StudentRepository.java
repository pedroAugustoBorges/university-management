package org.pedro.repository.imp;

import org.pedro.domain.Student;
import org.pedro.repository.IStudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentRepository implements IStudentRepository {

    @Override
    public void save(Student entity) {

    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public boolean removeById(Integer integer) {
        return false;
    }

    @Override
    public Optional<Student> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }
}
