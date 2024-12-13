package org.pedro.service;

import jakarta.persistence.EntityNotFoundException;
import org.pedro.domain.Enrollment;
import org.pedro.repository.IEnrollmentRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class EnrollmentService {

    private static final Logger LOGGER = Logger.getLogger(EnrollmentService.class.getName());

    private IEnrollmentRepository enrollmentRepository;

    public EnrollmentService(IEnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public void save (Integer studentId, Integer courseId){
        if(studentId == null || courseId == null){
            throw new IllegalArgumentException("ID is null, then can't be save in database");
        }
        enrollmentRepository.save(studentId, courseId);
    }

    public void save (Enrollment enrollment){
        if (enrollment == null){
            throw new EntityNotFoundException("Enrollment can't be null");
        }

        enrollmentRepository.save(enrollment);
    }

    public Optional<Enrollment> findById (Integer enrollmentId){

        if (enrollmentId == null){
            throw new IllegalArgumentException("Id cannot null");
        }

        Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollmentId);

        enrollment.orElseThrow(() -> new EntityNotFoundException("Enrollment with id : " + enrollmentId + " not found"));

        return enrollment;
    }

    public void update (Enrollment enrollment){

        if (enrollment == null){
            throw new IllegalArgumentException("Enrollment can't be null");
        }
        Optional<Enrollment> enrollmentSearched = enrollmentRepository.findById(enrollment.getId());

        enrollmentSearched.orElseThrow(() -> new EntityNotFoundException("Enrollment with id : " + enrollment.getId() + " not found"));

        enrollmentRepository.update(enrollment);
    }

    public List<Enrollment> findAll (){

        List<Enrollment> enrollments = enrollmentRepository.findAll();
        return enrollments.isEmpty() ? new ArrayList<>() : enrollments;
    }

    public List<Enrollment> findByIdStudent (Integer studentId){

        List<Enrollment> enrollments = enrollmentRepository.findByIdStudent(studentId);
        return enrollments.isEmpty() ? new ArrayList<>() : enrollments;
    }

    public List<Enrollment> findByIdCourse (Integer courseId){

        List<Enrollment> enrollments = enrollmentRepository.findByIdCourse(courseId);
        return enrollments.isEmpty() ? new ArrayList<>() : enrollments;
    }




}