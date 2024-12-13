package org.pedro.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.pedro.domain.Student;
import org.pedro.repository.IStudentRepository;
import org.pedro.repository.imp.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class StudentService {

    private IStudentRepository studentRepository;

    private static final Logger LOGGER = Logger.getLogger(StudentRepository.class.getName());

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save (Student student){
        if (student == null){
            throw new EntityNotFoundException("Student is null, then can't be to save in database");
        }

        studentRepository.save(student);
    }

    public boolean removeByid (Integer id){
        studentRepository.findById(id).orElseThrow(() -> {
            LOGGER.severe("Attemped to remove a course with id : "  + id + " ,but no such student exists");
            return new EntityNotFoundException("Student not found with id: " + id);
        });

         return studentRepository.removeById(id);
    }

    public void update (Student student){
        Optional<Student> studentOptional = studentRepository.findById(student.getStudent_id());

        studentOptional.orElseThrow( () -> {
            String errorMessage = "Attepted to update a student " + student.getStudent_id() + ", but no such student exists";
            LOGGER.severe(errorMessage);
            return new EntityNotFoundException("Student is null with id " + student.getStudent_id());
        });

        studentRepository.update(student);
    }

    public Optional<Student> findById (Integer id){
        if (id == null) {
            throw new IllegalArgumentException("Id cannot is null");
        }

        Optional<Student> studentReturned = studentRepository.findById(id);

        studentReturned.orElseThrow( () -> new EntityNotFoundException("Course searched not exists"));

        return studentReturned;
    }

    public List<Student> listAll (){
        List<Student> students = studentRepository.findAll();
        return students.isEmpty()? new ArrayList<>(): students;
    }




}
