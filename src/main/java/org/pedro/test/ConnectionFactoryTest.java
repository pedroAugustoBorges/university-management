package org.pedro.test;

import org.pedro.connection.ConnectionFactory;
import org.pedro.domain.Course;
import org.pedro.repository.imp.CourseRepository;
import org.pedro.service.CourseService;

import java.util.List;
import java.util.logging.Logger;

public class ConnectionFactoryTest {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

    public static void main(String[] args) {

        Course course = new Course();

        course.setTitle("Portuguese");

        course.setDescription("Course of Portuguese beginner");

        CourseRepository courseRepository = new CourseRepository();

        CourseService courseService = new CourseService(courseRepository);

//        courseService.save(course);

//        List<Course> courses = courseService.findAll();
//
//        System.out.println(courses);

        boolean removed = courseService.removeById(2);

        System.out.println(removed);
//
    }
}
