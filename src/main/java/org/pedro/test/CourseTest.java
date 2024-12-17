package org.pedro.test;

import org.pedro.connection.ConnectionFactory;
import org.pedro.domain.Course;
import org.pedro.repository.imp.CourseRepository;
import org.pedro.service.CourseService;

import java.util.List;
import java.util.logging.Logger;

public class CourseTest {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

    public static void main(String[] args) {

        Course course = new Course();

        course.setTitle("Design");

        course.setDescription("Tecnico of Design");

        CourseRepository courseRepository = new CourseRepository();

        CourseService courseService = new CourseService(courseRepository);

        courseService.save(course);

//        List<Course> courses = courseService.findAll();
//
//        System.out.println(courses);

//        boolean removed = courseService.removeById(3);


//
    }
}
