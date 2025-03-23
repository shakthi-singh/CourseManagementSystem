package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class CourseMarkKey implements Serializable {

    @Column(name = "studentId")
    Integer studentId;

    @Column(name = "courseId")
    String courseId;


    @Override
    public int hashCode() {
        String concatString = String.valueOf(studentId.hashCode()) + String.valueOf(courseId.hashCode());
        return concatString.hashCode();
    }
    public CourseMarkKey(){}

    public CourseMarkKey(int studentId, String courseId){
        this.setStudentId(studentId);
        this.setCourseId(courseId);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null){
            return false;
        }
        if (o == this)
            return true;
        if (!(o instanceof CourseMarkKey))
            return false;
        CourseMarkKey other = (CourseMarkKey) o;
        return studentId.equals(other.studentId) && courseId.equals(other.courseId);
    }

}
