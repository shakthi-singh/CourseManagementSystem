package com.example.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @Column(name = "courseId")
    private String id;

    @NonNull
    @Column(name = "courseName")
    private String name;

    @OneToMany(mappedBy = "course")
    private List<CourseMark> marks = new ArrayList<>();

    public Course(String id, String name){
        this.id = id;
        this.name = name;
    }

}
