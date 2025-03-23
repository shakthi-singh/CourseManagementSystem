package com.example.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Transcript")
public class CourseMark {

    @EmbeddedId
    CourseMarkKey markId;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "courseId")
    private Course course;

    @NonNull
    @Column(name = "grade")
    private int mark;


}
