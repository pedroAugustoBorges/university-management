package org.pedro.repository;

import org.pedro.domain.Enrollment;

import java.util.List;
import java.util.Optional;

public interface IEnrollmentRepository extends GenericRepository <Enrollment, Integer> {

    @Override
    void save(Enrollment entity);

    @Override
    void update(Enrollment entity);

    @Override
    boolean removeById(Integer integer);

    @Override
    Optional<Enrollment> findById(Integer integer);

    @Override
    List<Enrollment> findAll();
}
