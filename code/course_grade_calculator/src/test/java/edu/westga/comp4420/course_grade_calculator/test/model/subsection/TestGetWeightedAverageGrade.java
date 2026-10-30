package edu.westga.comp4420.course_grade_calculator.test.model.subsection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Grade;
import edu.westga.comp4420.course_grade_calculator.model.Subsection;

public class TestGetWeightedAverageGrade {

    @Test
    void testGetWeightedAverageGradeWithNoGrades() {
        Subsection subsection = new Subsection("Homework", 20, 0, 0);
        assertEquals(0.0, subsection.getWeightedAverageGrade(), 0.001);
    }

    @Test
    void testGetWeightedAverageGradeWithGrades() {
        Subsection subsection = new Subsection("Quiz", 20, 0, 0);
        subsection.addGrade(new Grade("Quiz 1", 100, 90));
        subsection.addGrade(new Grade("Quiz 2", 100, 80));
        subsection.addGrade(new Grade("Quiz 3", 100, 70));
        assertEquals(16.0, subsection.getWeightedAverageGrade(), 0.001);
    }

}
