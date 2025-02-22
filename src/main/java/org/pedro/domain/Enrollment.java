package org.pedro.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor

public class  Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    private LocalDateTime localDateTime;

    @Override
    public String toString() {
        return "\nEnrollment:\n" +
                "Id: " + id +
                ", Student_Id: " + (student != null ? student.getStudent_id() : "null")+
                ", Course_Id: " + (course != null ? course.getCourse_id() : "null") +
                ", localDateTime: " + localDateTime +
                "}";
    }
}