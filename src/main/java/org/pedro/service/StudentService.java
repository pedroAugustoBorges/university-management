package org.pedro.service;

import org.pedro.domain.Student;
import org.pedro.exceptions.GenericNotFoundException;
import org.pedro.repository.IStudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class StudentService {

    private final IStudentRepository studentRepository;

    private static final Logger LOGGER = Logger.getLogger(StudentService.class.getName());

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save (Student student){
        if (student == null){
            throw new GenericNotFoundException("Student can't be null");
        }

        studentRepository.save(student);
    }

    public boolean removeById(Integer id){
        studentRepository.findById(id).orElseThrow(() -> {
            LOGGER.severe("Attemped to remove a course with id : "  + id + " ,but no such student exists");
            return new GenericNotFoundException("Student", id);
        });

         return studentRepository.removeById(id);
    }

    public void update (Student student){
        Optional<Student> studentOptional = studentRepository.findById(student.getStudent_id());

        studentOptional.orElseThrow( () -> {
            String errorMessage = "Attepted to update a student " + student.getStudent_id() + ", but no such student exists";
            LOGGER.severe(errorMessage);
            return new GenericNotFoundException("Student",student.getStudent_id());
        });

        studentRepository.update(student);
    }

    public Optional<Student> findById (Integer id){
        if (id == null) {
            throw new IllegalArgumentException("Id cannot is null");
        }

        Optional<Student> studentReturned = studentRepository.findById(id);

        studentReturned.orElseThrow( () -> new GenericNotFoundException("Student", id));

        return studentReturned;
    }

    public List<Student> listAll (){
        List<Student> students = studentRepository.findAll();
        return students.isEmpty()? new ArrayList<>(): students;
    }




}
