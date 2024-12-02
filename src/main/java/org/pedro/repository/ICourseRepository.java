package org.pedro.repository;

import org.pedro.domain.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository extends GenericRepository<Course, Integer> {

    @Override
    void save(Course entity);

    @Override
    void update(Course entity);

    @Override
    boolean removeById(Integer integer);

    @Override
    Optional<Course> findById(Integer integer);

    @Override
    List<Course> findAll();
}
