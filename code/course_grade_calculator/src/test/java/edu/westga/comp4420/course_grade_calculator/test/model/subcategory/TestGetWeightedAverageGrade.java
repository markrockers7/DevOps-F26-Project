package edu.westga.comp4420.course_grade_calculator.test.model.subcategory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Grade;
import edu.westga.comp4420.course_grade_calculator.model.Subcategory;

public class TestGetWeightedAverageGrade {

    @Test
    void testGetWeightedAverageGradeWithNoGrades() {
        Subcategory subcategory = new Subcategory("Homework", 20, 0, 0);
        assertEquals(0.0, subcategory.getWeightedAverageGrade(), 0.001);
    }

    @Test
    void testGetWeightedAverageGradeWithGrades() {
        Subcategory subcategory = new Subcategory("Quiz", 20, 0, 0);
        subcategory.addGrade(new Grade("Quiz 1", 100, 90));
        subcategory.addGrade(new Grade("Quiz 2", 100, 80));
        subcategory.addGrade(new Grade("Quiz 3", 100, 70));
        assertEquals(16.0, subcategory.getWeightedAverageGrade(), 0.001);
    }

}
