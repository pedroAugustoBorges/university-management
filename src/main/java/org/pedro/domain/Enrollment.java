package org.pedro.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    private LocalDateTime localDateTime;


}