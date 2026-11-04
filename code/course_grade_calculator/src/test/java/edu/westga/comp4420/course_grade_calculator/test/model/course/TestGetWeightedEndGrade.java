package edu.westga.comp4420.course_grade_calculator.test.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Course;
import edu.westga.comp4420.course_grade_calculator.model.Grade;
import edu.westga.comp4420.course_grade_calculator.model.Subsection;

public class TestGetWeightedEndGrade {

    @Test
    void testGetWeightedAverageGrade() {
        var midtermExams = new Subsection("Midterm Exams", 30, 2, 0);
        var midtermExam1 = new Grade("Midterm 1", 103, 88);
        midtermExams.addGrade(midtermExam1);

        var finalExam = new Subsection("Final Exams", 15, 1, 0);
        var finalExamGrade = new Grade("Final Exam", 100, 84);
        finalExam.addGrade(finalExamGrade);

        var projects = new Subsection("Projects", 30, 0, 0);
        var project1 = new Grade("Project 1", 100, 95);
        var project2 = new Grade("Project 2", 100, 90);
        projects.addGrade(project1);
        projects.addGrade(project2);

        var labs = new Subsection("Labs", 25, 6, 0);
        var lab1 = new Grade("Lab 1", 10, 10);
        var lab2 = new Grade("Lab 2", 10, 10);
        labs.addGrade(lab1);
        labs.addGrade(lab2);

        var subsections = new ArrayList<Subsection>();
        subsections.add(midtermExams);
        subsections.add(finalExam);
        subsections.add(projects);
        subsections.add(labs);
        var course = new Course("DevOps", "COMP 4420", "Fall 2026", subsections);

        var expectedAverageGrade = midtermExams.getWeightedEndGrade() + finalExam.getWeightedEndGrade() + projects.getWeightedEndGrade() + labs.getWeightedEndGrade();
        assertEquals(expectedAverageGrade, course.getWeightedEndGrade());
    }
}
