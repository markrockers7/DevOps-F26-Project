package edu.westga.comp4420.course_grade_calculator.test.model.grade;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Grade;

class TestConstructor {

    @Test 
    void testConstructorWithValidParameters() {
        Grade grade = new Grade("Test", 100, 90);
        assertAll(() -> {
            assertTrue(grade.getName().equals("Test"));
            assertTrue(grade.getMaxScore() == 100);
            assertTrue(grade.getScore() == 90);
        });
    }

    //#region Testing Name Validation
    @Test
    void testConstructorWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Grade(null, 100, 90);
        });
    }

    @Test
    void testConstructorWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Grade("", 100, 90);
        });
    }
    //#endregion

    //#region Testing Max Score Validation
    @Test
    void testConstructorWithMaxScoreLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Grade("Test", 0, 0);
        });
    }

    @Test
    void testConstructorWithMaxScoreExactlyOne() {
        assertDoesNotThrow(() -> {
            new Grade("Test", 1, 0);
        });
    }
    //#endregion

    //#region Testing Score Validation
    @Test
    void testConstructorWithScoreGreaterThanMaxScore() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Grade("Test", 100, 101);
        });
    }

    @Test
    void testConstructorWithScoreEqualToMaxScore() {
        assertDoesNotThrow(() -> {
            new Grade("Test", 100, 100);
        });
    }

    @Test 
    void testConstructorWithScoreLessThanMaxScore() {
        assertDoesNotThrow(() -> {
            new Grade("Test", 100, 99);
        });
    }

    @Test
    void testConstructorWithScoreLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Grade("Test", 100, -1);
        });
    }

    @Test 
    void testConstructorWithScoreEqualToZero() {
        assertDoesNotThrow(() -> {
            new Grade("Test", 100, 0);
        });
    }
    //#endregion
}
