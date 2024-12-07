package org.pedro.service;

import jakarta.persistence.EntityManager;
import org.pedro.connection.ConnectionFactory;
import org.pedro.domain.Course;
import org.pedro.repository.imp.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void save (Course course){
        courseRepository.save(course);
    }

    public List<Course> findAll (){
        List<Course> courses = courseRepository.findAll();
        return courses != null ? courses : new ArrayList<>();
    }

    public Optional<Course> findById (Integer integer){

        if(integer == null) {
            throw  new IllegalArgumentException("Integer Id cannot bee null");
        }
        return courseRepository.findById(integer);
    }
}
