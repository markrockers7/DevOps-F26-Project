package edu.westga.comp4420.course_grade_calculator.test.model.subsection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Grade;
import edu.westga.comp4420.course_grade_calculator.model.Subsection;

public class TestGetEndGrade {

    private static final String TEST_SUBSECTION_NAME = "Exams";

    @Test
    void testGetEndGradeWithNoGrades() {
        var subsection = new Subsection(TEST_SUBSECTION_NAME, 100, 0, 0);
        assertEquals(100.0, subsection.getEndGrade(), 0.01);
    }

    @Test
    void testGetEndGradeWithMaxGrades() {
        var subsection = new Subsection(TEST_SUBSECTION_NAME, 100, 3, 0);
        subsection.addGrade(new Grade("Exam 1", 100, 80));
        subsection.addGrade(new Grade("Exam 2", 100, 90));
        subsection.addGrade(new Grade("Exam 3", 100, 70));
        assertEquals(80.0, subsection.getEndGrade(), 0.01);
    }

    @Test
    void testGetEndGradeWithSomeGrades() {
        var subsection = new Subsection(TEST_SUBSECTION_NAME, 100, 3, 0);
        subsection.addGrade(new Grade("Exam 1", 100, 80));
        subsection.addGrade(new Grade("Exam 2", 100, 70));
        assertEquals(83.33, subsection.getEndGrade(), 0.01);
    }

    @Test
    void testGetEndGradeWithOneGrade() {
        var subsection = new Subsection(TEST_SUBSECTION_NAME, 100, 3, 0);
        subsection.addGrade(new Grade("Exam 1", 100, 60));
        assertEquals(86.66, subsection.getEndGrade(), 0.01);
    }

    @Test
    void testGetEndGradeWithDroppedGradesAndNoGrades() {
        var subsection = new Subsection(TEST_SUBSECTION_NAME, 100, 3, 1);
        assertEquals(100.0, subsection.getEndGrade(), 0.01);
    }

    @Test
    void testGetEndGradeWithDroppedGradesAndMaxGrades() {
        var subsection = new Subsection(TEST_SUBSECTION_NAME, 100, 3, 1);
        subsection.addGrade(new Grade("Exam 1", 100, 80));
        subsection.addGrade(new Grade("Exam 2", 100, 90));
        subsection.addGrade(new Grade("Exam 3", 100, 70));
        var expectedEndGrade = (90.0 + 80.0) / 2.0;
        assertEquals(expectedEndGrade, subsection.getEndGrade(), 0.01);
    }

    @Test
    void testGetEndGradeWithDroppedGradesAndSomeGrades() {
        var subsection = new Subsection(TEST_SUBSECTION_NAME, 100, 3, 1);
        subsection.addGrade(new Grade("Exam 1", 100, 80));
        subsection.addGrade(new Grade("Exam 2", 100, 70));
        var expectedEndGrade = (80.0 + 100.0) / 2.0;
        assertEquals(expectedEndGrade, subsection.getEndGrade(), 0.01);
    }

    @Test
    void testGetEndGradeWithDroppedGradesAndOneGrade() {
        var subsection = new Subsection(TEST_SUBSECTION_NAME, 100, 3, 1);
        subsection.addGrade(new Grade("Exam 1", 100, 60));
        assertEquals(100.0, subsection.getEndGrade(), 0.01);
    }

    @Test
    void testGetEndGradeWithNoMaxGradesAndOneGrade() {
        var subsection = new Subsection(TEST_SUBSECTION_NAME, 100, 0, 0);
        subsection.addGrade(new Grade("Exam 1", 100, 60));
        assertEquals(60.0, subsection.getEndGrade(), 0.01);
    }

    @Test
    void testGetEndGradeWithNoMaxGradesAndMultipleGrades() {
        var subsection = new Subsection(TEST_SUBSECTION_NAME, 100, 0, 0);
        subsection.addGrade(new Grade("Exam 1", 100, 60));
        subsection.addGrade(new Grade("Exam 2", 100, 80));
        assertEquals(70.0, subsection.getEndGrade(), 0.01);
    }

    @Test
    void testGetEndGradeWithNoMaxGradesOneGradeOneDropped() {
        var subsection = new Subsection(TEST_SUBSECTION_NAME, 100, 0, 1);
        subsection.addGrade(new Grade("Exam 1", 100, 60));
        assertEquals(100.0, subsection.getEndGrade(), 0.01);
    }

    @Test
    void testGetEndGradeWithGradeMaxGreaterThan100() {
        var subsection = new Subsection(TEST_SUBSECTION_NAME, 100, 3, 0);
        subsection.addGrade(new Grade("Exam 1", 150, 80));
        subsection.addGrade(new Grade("Exam 2", 150, 70));
        var expectedEndGrade = (((80.0 / 150) * 100.0) + ((70.0 / 150) * 100.0) + 100.0) / 3.0;
        assertEquals(expectedEndGrade, subsection.getEndGrade(), 0.01);
    }

    @Test
    void testGradesWithGradesThatHaveUnevenPercentages() {
        var midtermExams = new Subsection("Midterm Exams", 30, 2, 0);
        var midtermExam1 = new Grade("Midterm 1", 103, 88);
        var midtermExam2 = new Grade("Midterm 2", 100, 92);
        midtermExams.addGrade(midtermExam1);
        midtermExams.addGrade(midtermExam2);

        var expectedEndGrade = ((88.0 / 103) * 100.0 + (92.0 / 100) * 100.0) / 2.0;
        assertEquals(expectedEndGrade, midtermExams.getEndGrade(), 0.01);
    }
}
