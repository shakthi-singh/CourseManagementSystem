package com.example.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class TeachingAssistant extends Person {

    @ManyToOne
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;

    public TeachingAssistant(int id, String firstName, String lastName, String email, Course course) {
        super(id, firstName, lastName, email);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "TeachingAssistant{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", course=" + getCourse() + '\'' +
                '}';
    }
}

