package edu.westga.comp4420.course_grade_calculator.test.model.grade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Grade;

public class TestSetScore {
    private Grade grade;

    @BeforeEach
    void setUp() {
        this.grade = new Grade("Test", 100, 50.0);
    }

    @Test 
    void testSetScoreWithSameScore() {
        this.grade.setScore(50.0);
        assertEquals(50.0, this.grade.getScore());
    }

    @Test
    void testSetScoreWithHigherScore() {
        this.grade.setScore(75.0);
        assertEquals(75.0, this.grade.getScore());
    }

    @Test
    void testSetScoreWithLowerScore() {
        this.grade.setScore(25.0);
        assertEquals(25.0, this.grade.getScore());
    }

    @Test
    void testSetScoreAboveMaxScore() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.grade.setScore(101.0);
        });
    }
    
    @Test
    void testSetScoreAtMaxScore() {
        this.grade.setScore(100.0);
        assertEquals(100.0, this.grade.getScore());
    }

    @Test
    void testSetScoreBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.grade.setScore(-1.0);
        });
    }
}
