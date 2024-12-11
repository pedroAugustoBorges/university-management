package org.pedro.test;

import org.pedro.domain.Student;
import org.pedro.repository.imp.StudentRepository;
import org.pedro.service.StudentService;

import java.util.List;
import java.util.Optional;

public class StudentTest {

    public static void main(String[] args) {
        Student student = new Student();

        student.setName("Cleitin Junior");

        StudentRepository studentRepository = new StudentRepository();

        StudentService studentService = new StudentService(studentRepository);

        Student studentUpdate = new Student();

        studentUpdate.setStudent_id(1);
        studentUpdate.setName("Vanessa Marcone");

//        studentService.removeByid(3);

//
//        studentService.save(student);

        List<Student> students = studentService.listAll();

        System.out.println(students);

//        studentService.update(studentUpdate);
//
//        Optional<Student> studentFind = studentService.findById(1);
//
//        System.out.println(studentFind.get());
    }
}
