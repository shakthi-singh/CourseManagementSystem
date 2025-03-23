package com.example;

import com.example.model.Professor;
import com.example.model.Student;
import com.example.model.Course;
import com.example.model.CourseMark;
import com.example.model.CourseMarkKey;
import com.example.model.TeachingAssistant;


import java.util.HashMap;

// This is the main java program

public class App {

    public static void main(String[] args) {
        DataUtil dataUtil = new DataUtil();
        try {
            // Establish connection with the database (remove one if it already exists)
            dataUtil.tearDown();
            dataUtil.setUp();
            // Load the data from the database
            HashMap<Integer, Student> students = dataUtil.getStudents();
            HashMap<String, Course> courses = dataUtil.getCourses();
            HashMap<Integer, Professor> professors = dataUtil.getProfessors();
            HashMap<CourseMarkKey, CourseMark> transcript = dataUtil.getTranscript();
            
            // YOUR CODE GOES HERE
            dataUtil.populateTeachingAssistantTable();
            dataUtil.query1();
            dataUtil.query2();
            dataUtil.query3();
            dataUtil.query4();
            dataUtil.query5();
            dataUtil.query6();
            dataUtil.query7();

        } catch (Exception e) {
            e.printStackTrace();
    }

    }

}

