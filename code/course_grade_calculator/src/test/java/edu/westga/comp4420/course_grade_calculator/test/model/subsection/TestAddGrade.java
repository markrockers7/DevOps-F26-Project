package edu.westga.comp4420.course_grade_calculator.test.model.subsection;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Grade;
import edu.westga.comp4420.course_grade_calculator.model.Subsection;

public class TestAddGrade {

    @Test
    void testAddValidGrade() {
        Grade grade = new Grade("Test 1", 100, 90);
        Subsection subsection = new Subsection("Tests", 100, 0, 0);
        subsection.addGrade(grade);

        assertAll(
                () -> assertEquals(1, subsection.getGrades().size()),
                () -> assertEquals(grade, subsection.getGrades().get(0)));
    }

    @Test
    void testAddMultipleValidGrades() {
        Grade grade1 = new Grade("Project 1", 100, 90);
        Grade grade2 = new Grade("Project 2", 100, 80);
        Subsection subsection = new Subsection("Projects", 100, 0, 0);
        subsection.addGrade(grade1);
        subsection.addGrade(grade2);

        assertAll(
                () -> assertEquals(2, subsection.getGrades().size()),
                () -> assertEquals(grade1, subsection.getGrades().get(0)),
                () -> assertEquals(grade2, subsection.getGrades().get(1)));
    }

    @Test
    void testAddGradeExceedingTotalGradeCount() {
        Grade grade1 = new Grade("Lab 1", 100, 90);
        Grade grade2 = new Grade("Lab 2", 100, 80);
        Subsection subsection = new Subsection("Labs", 100, 1, 0);
        subsection.addGrade(grade1);

        assertThrows(IllegalArgumentException.class, () -> {
            subsection.addGrade(grade2);
        });
    }

    @Test
    void testAddNullGrade() {
        Subsection subsection = new Subsection("Quizzes", 100, 0, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            subsection.addGrade(null);
        });
    }

}
