package edu.westga.comp4420.course_grade_calculator.test.model.grade;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Grade;

public class TestSetMaxScore {
    private Grade grade;

    @BeforeEach
    void setUp() {
        this.grade = new Grade("Test", 100, 0);
    }

    @Test 
    void testSetMaxScoreWithSameMaxScore() {
        this.grade.setMaxScore(100);
        assertEquals(100, this.grade.getMaxScore());
    }

    @Test
    void testSetMaxScoreWithHigherMaxScore() {
        this.grade.setMaxScore(200);
        assertEquals(200, this.grade.getMaxScore());
    }

    @Test
    void testSetMaxScoreWithLowerMaxScore() {
        this.grade.setMaxScore(75);
        assertEquals(75, this.grade.getMaxScore());
    }

    @Test 
    void testSetMaxScoreAboveMinimumMaxScore() {
        this.grade.setMaxScore(1);
        assertEquals(1, this.grade.getMaxScore());
    }

    @Test
    void testSetMaxScoreAtMinimumMaxScore() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.grade.setMaxScore(0);
        });
    }

    @Test
    void testSetMaxScoreBelowMinimumMaxScore() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.grade.setMaxScore(-1);
        });
    }

    @Test 
    void testSetMaxScoreWithScoreValue() {
        this.grade.setScore(50.0);
        assertDoesNotThrow(() -> {
            this.grade.setMaxScore(50);
        });
    }

    @Test
    void testSetMaxScoreWithScoreValueAboveMaxScore() {
        this.grade.setScore(50.0);
        assertThrows(IllegalArgumentException.class, () -> {
            this.grade.setMaxScore(49);
        });
    }
}
