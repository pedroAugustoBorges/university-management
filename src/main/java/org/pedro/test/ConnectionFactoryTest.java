package org.pedro.test;

import org.pedro.domain.Course;
import org.pedro.repository.imp.CourseRepository;
import org.pedro.service.CourseService;

import java.util.List;

public class ConnectionFactoryTest {

    public static void main(String[] args) {

        Course course = new Course();

        course.setTitle("English");

        course.setDescription("Course of English between A1 and C2");

        CourseRepository courseRepository = new CourseRepository();

        CourseService courseService = new CourseService(courseRepository);

//        courseService.save(course);

        List<Course> courses = courseService.findAll();

        System.out.println(courses);

    }
}
