package edu.westga.comp4420.course_grade_calculator.test.model.subsection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Grade;
import edu.westga.comp4420.course_grade_calculator.model.Subsection;

public class TestGetAverageGrade {

    @Test
    void testGetAverageGradeWithNoGrades() {
        Subsection subsection = new Subsection("Homework", 20, 0, 0);
        assertEquals(0.0, subsection.getAverageGrade(), 0.001);
    }

    @Test
    void testGetAverageGradeWithGrades() {
        Subsection subsection = new Subsection("Quiz", 20, 0, 0);
        subsection.addGrade(new Grade("Quiz 1", 100, 90));
        subsection.addGrade(new Grade("Quiz 2", 100, 80));
        subsection.addGrade(new Grade("Quiz 3", 100, 70));
        assertEquals(80.0, subsection.getAverageGrade(), 0.001);
    }

    @Test
    void testGetAverageGradeWithDroppedGrades() {
        Subsection subsection = new Subsection("Exam", 20, 0, 1);
        subsection.addGrade(new Grade("Exam 1", 100, 90));
        subsection.addGrade(new Grade("Exam 2", 100, 80));
        subsection.addGrade(new Grade("Exam 3", 100, 70));
        assertEquals(85.0, subsection.getAverageGrade(), 0.001);
    }

    @Test
    void testGetAverageGradeWithDroppedGradesAndNotEnoughGrades() {
        Subsection subsection = new Subsection("Project", 20, 0, 2);
        subsection.addGrade(new Grade("Project 1", 100, 90));
        subsection.addGrade(new Grade("Project 2", 100, 80));
        assertEquals(0.0, subsection.getAverageGrade(), 0.001);
    }

}
