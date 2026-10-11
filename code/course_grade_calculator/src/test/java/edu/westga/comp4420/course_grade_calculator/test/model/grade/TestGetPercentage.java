package edu.westga.comp4420.course_grade_calculator.test.model.grade;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Grade;

public class TestGetPercentage {

    private Grade grade;

    @BeforeEach 
    void setUp() {
        this.grade = new Grade("Test", 1, 0);
    }

    @Test
    void testGetPercentageWithMaxScoreAndScoreEqual() {
        this.grade.setMaxScore(100);
        this.grade.setScore(100.0);
        assertEquals(100.0, this.grade.getPercentage());
    }

    @Test 
    void testGetPercentageWithScoreHalfOfMaxScore() {
        this.grade.setMaxScore(100);
        this.grade.setScore(50.0);
        assertEquals(50.0, this.grade.getPercentage());
    }

    @Test 
    void testGetPercentageWithScoreZero() {
        this.grade.setMaxScore(100);
        this.grade.setScore(0.0);
        assertEquals(0.0, this.grade.getPercentage());
    }

    @Test 
    void testGetPercentageWithMaxScoreMinimum() {
        this.grade.setMaxScore(1);
        this.grade.setScore(0.0);
        assertEquals(0.0, this.grade.getPercentage());
    }

    @Test
    void testGetPercentageWithUnevenMaxScoreAndScore() {
        this.grade.setMaxScore(75);
        this.grade.setScore(50.0);
        assertEquals(66.66, this.grade.getPercentage(), 0.01);
    }

}
