package edu.westga.comp4420.course_grade_calculator.test.model.subcategory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Grade;
import edu.westga.comp4420.course_grade_calculator.model.Subcategory;

public class TestAddGrade {

    @Test
    void testAddValidGrade() {
        Grade grade = new Grade("Test 1", 100, 90);
        Subcategory subcategory = new Subcategory("Tests", 100, 0, 0);
        subcategory.addGrade(grade);

        assertAll(
            () -> assertEquals(1, subcategory.getGrades().size()),
            () -> assertEquals(grade, subcategory.getGrades().get(0))
        );
    }

    @Test
    void testAddMultipleValidGrades() {
        Grade grade1 = new Grade("Project 1", 100, 90);
        Grade grade2 = new Grade("Project 2", 100, 80);
        Subcategory subcategory = new Subcategory("Projects", 100, 0, 0);
        subcategory.addGrade(grade1);
        subcategory.addGrade(grade2);

        assertAll(
            () -> assertEquals(2, subcategory.getGrades().size()),
            () -> assertEquals(grade1, subcategory.getGrades().get(0)),
            () -> assertEquals(grade2, subcategory.getGrades().get(1))
        );
    }

    @Test
    void testAddGradeExceedingTotalGradeCount() {
        Grade grade1 = new Grade("Lab 1", 100, 90);
        Grade grade2 = new Grade("Lab 2", 100, 80);
        Subcategory subcategory = new Subcategory("Labs", 100, 1, 0);
        subcategory.addGrade(grade1);

        assertThrows(IllegalArgumentException.class, () -> {
            subcategory.addGrade(grade2);
        });
    }

    @Test
    void testAddNullGrade() {
        Subcategory subcategory = new Subcategory("Quizzes", 100, 0, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            subcategory.addGrade(null);
        });
    }

}
