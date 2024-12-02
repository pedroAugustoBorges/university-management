package org.pedro.repository.imp;

import org.pedro.domain.Enrollment;
import org.pedro.repository.IEnrollmentRepository;

import java.util.List;
import java.util.Optional;

public class EnrollmentRepository implements IEnrollmentRepository {

    @Override
    public void save(Enrollment entity) {

    }

    @Override
    public void update(Enrollment entity) {

    }

    @Override
    public boolean removeById(Integer integer) {
        return false;
    }

    @Override
    public Optional<Enrollment> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Enrollment> findAll() {
        return List.of();
    }
}
