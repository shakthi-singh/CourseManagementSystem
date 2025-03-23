package com.example.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//
import java.util.Set;
@Entity
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "profId"))
    })
@NoArgsConstructor
@Getter
@Setter
public class Professor extends Person {
    private String office;
    //MY CODE
   @ManyToMany
   @JoinTable(
           name = "teaches",
           joinColumns = @JoinColumn(name = "profId"),
           inverseJoinColumns = @JoinColumn(name = "courseId")
   )
   private Set<Course> courses;
   //
}