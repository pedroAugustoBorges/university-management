package org.pedro.test;

import org.pedro.domain.Course;
import org.pedro.domain.Enrollment;
import org.pedro.domain.Student;
import org.pedro.repository.imp.EnrollmentRepository;
import org.pedro.service.EnrollmentService;

import java.time.LocalDateTime;
import java.util.Optional;

public class EnrollmentTest {

    public static void main(String[] args) {

        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        EnrollmentService enrollmentService = new EnrollmentService(enrollmentRepository);

//        enrollmentService.save(3, 1);
//        enrollmentService.save(2, 5);
//        enrollmentService.save(1, 5);
//        enrollmentService.save(1, 4);
//        enrollmentService.save(2, 6 );
//        enrollmentService.save(3, 6);


//        Course course = enrollmentService.fetchCourseForUpdate(1).get();
//
//        Student student = enrollmentService.fetchStudentForUpdate(2).get();
//
//
//        Enrollment enrollmentUpdate = new Enrollment();
//
//        enrollmentUpdate.setId(10);
//
//        enrollmentUpdate.setLocalDateTime(LocalDateTime.now());
//
//        enrollmentUpdate.setCourse(course);
//
//        enrollmentUpdate.setStudent(student);
//
//        enrollmentService.update(enrollmentUpdate);



//        System.out.println(enrollmentService.findByIdCourse(1));

        enrollmentService.removeById(6);

        System.out.println(enrollmentService.findAll());



    }
}
