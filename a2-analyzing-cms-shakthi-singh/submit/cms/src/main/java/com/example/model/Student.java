package com.example.model;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "studentId"))
    })
@NoArgsConstructor
@Getter
@Setter
public class Student extends Person{

    @OneToMany(mappedBy = "student")
    private List<CourseMark> transcript;
    
}
