package edu.westga.comp4420.course_grade_calculator.test.model.subcategory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Grade;
import edu.westga.comp4420.course_grade_calculator.model.Subcategory;

public class TestGetAverageGrade {

    @Test
    void testGetAverageGradeWithNoGrades() {
        Subcategory subcategory = new Subcategory("Homework", 20, 0, 0);
        assertEquals(0.0, subcategory.getAverageGrade(), 0.001);
    }

    @Test
    void testGetAverageGradeWithGrades() {
        Subcategory subcategory = new Subcategory("Quiz", 20, 0, 0);
        subcategory.addGrade(new Grade("Quiz 1", 100, 90));
        subcategory.addGrade(new Grade("Quiz 2", 100, 80));
        subcategory.addGrade(new Grade("Quiz 3", 100, 70));
        assertEquals(80.0, subcategory.getAverageGrade(), 0.001);
    }

    @Test
    void testGetAverageGradeWithDroppedGrades() {
        Subcategory subcategory = new Subcategory("Exam", 20, 0, 1);
        subcategory.addGrade(new Grade("Exam 1", 100, 90));
        subcategory.addGrade(new Grade("Exam 2", 100, 80));
        subcategory.addGrade(new Grade("Exam 3", 100, 70));
        assertEquals(85.0, subcategory.getAverageGrade(), 0.001);
    }

    @Test
    void testGetAverageGradeWithDroppedGradesAndNotEnoughGrades() {
        Subcategory subcategory = new Subcategory("Project", 20, 0, 2);
        subcategory.addGrade(new Grade("Project 1", 100, 90));
        subcategory.addGrade(new Grade("Project 2", 100, 80));
        assertEquals(0.0, subcategory.getAverageGrade(), 0.001);
    }

}
