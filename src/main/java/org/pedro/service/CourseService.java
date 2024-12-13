package org.pedro.service;


import jakarta.persistence.EntityNotFoundException;
import org.pedro.domain.Course;
import org.pedro.repository.ICourseRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


public class CourseService {

    private static final Logger LOGGER = Logger.getLogger(CourseService.class.getName());

    private ICourseRepository courseRepository;

    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void save (Course course){

        if (course == null){
            throw new IllegalArgumentException("Course is null");
        }

        courseRepository.save(course);
    }

    public List<Course> findAll (){
        List<Course> courses = courseRepository.findAll();
        return courses.isEmpty() ? new ArrayList<>() : courses;
    }

    public Optional<Course> findById (Integer integer){

        if(integer == null) {
            throw  new IllegalArgumentException("Integer Id cannot be null");
        }
        return courseRepository.findById(integer);
    }


    public void update (Course course){
        Optional<Course> courseSearched = findById(course.getCourse_id());
        if (courseSearched.isPresent()){
            courseRepository.update(course);
        }else {
            throw new EntityNotFoundException("Course not found");
        }
    }

    public boolean removeById (Integer integer){
        courseRepository.findById(integer).
                orElseThrow(() -> {
                    LOGGER.warning("Attepted to remove a course with id  " + integer + " , but no such course exits ");
                    return new EntityNotFoundException("Course is null");
                });

        courseRepository.removeById(integer);
        return true;
    }


}
