package org.pedro.repository.imp;

import org.pedro.domain.Course;
import org.pedro.repository.ICourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseRepository implements ICourseRepository {

    @Override
    public void save(Course entity) {

    }

    @Override
    public void update(Course entity) {

    }

    @Override
    public boolean removeById(Integer integer) {
        return false;
    }

    @Override
    public Optional<Course> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Course> findAll() {
        return List.of();
    }
}
