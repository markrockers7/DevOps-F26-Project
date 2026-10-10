package edu.westga.comp4420.course_grade_calculator.test.model.subcategory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Subcategory;

public class TestConstructor {
    private static final String SUBCATEGORY_NAME = "Test";

    @Test
    void testValidConstructor() {
        Subcategory subcategory = new Subcategory(SUBCATEGORY_NAME, 20, 3, 1);

        assertAll(() -> assertEquals("Test", subcategory.getName()),
                () -> assertEquals(20, subcategory.getWeight()),
                () -> assertTrue(subcategory.getGrades().isEmpty()),
                () -> assertEquals(3, subcategory.getMaxGradeCount()),
                () -> assertEquals(1, subcategory.getDroppedGradeCount()));
    }

    //region Name Tests
    @Test
    void testWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(null, 20, 3, 1));
    }

    @Test
    void testWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("", 20, 3, 1));
    }
    //#endregion

    //region Weight Tests
    @Test
    void testWithNegativeWeight() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(SUBCATEGORY_NAME, -1, 3, 1));
    }

    @Test
    void testWithWeightOfZero() {
        Subcategory subcategory = new Subcategory(SUBCATEGORY_NAME, 0, 3, 1);
        assertAll(() -> assertEquals("Test", subcategory.getName()),
                () -> assertEquals(0, subcategory.getWeight()),
                () -> assertTrue(subcategory.getGrades().isEmpty()),
                () -> assertEquals(3, subcategory.getMaxGradeCount()),
                () -> assertEquals(1, subcategory.getDroppedGradeCount()));
    }

    @Test
    void testWithWeightOnfOne() {
        Subcategory subcategory = new Subcategory(SUBCATEGORY_NAME, 1, 3, 1);
        assertAll(() -> assertEquals("Test", subcategory.getName()),
                () -> assertEquals(1, subcategory.getWeight()),
                () -> assertTrue(subcategory.getGrades().isEmpty()),
                () -> assertEquals(3, subcategory.getMaxGradeCount()),
                () -> assertEquals(1, subcategory.getDroppedGradeCount()));
    }

    @Test
    void testWithWeightOfNinetyNine() {
        Subcategory subcategory = new Subcategory(SUBCATEGORY_NAME, 99, 3, 1);
        assertAll(() -> assertEquals("Test", subcategory.getName()),
                () -> assertEquals(99, subcategory.getWeight()),
                () -> assertTrue(subcategory.getGrades().isEmpty()),
                () -> assertEquals(3, subcategory.getMaxGradeCount()),
                () -> assertEquals(1, subcategory.getDroppedGradeCount()));
    }

    @Test
    void testWithWeightOfOneHundred() {
        Subcategory subcategory = new Subcategory(SUBCATEGORY_NAME, 100, 3, 1);
        assertAll(() -> assertEquals("Test", subcategory.getName()),
                () -> assertEquals(100, subcategory.getWeight()),
                () -> assertTrue(subcategory.getGrades().isEmpty()),
                () -> assertEquals(3, subcategory.getMaxGradeCount()),
                () -> assertEquals(1, subcategory.getDroppedGradeCount()));
    }

    @Test
    void testWithWeightOfOneHundredAndOne() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(SUBCATEGORY_NAME, 101, 3, 1));
    }
    //#endregion

    //region Total Grade Count Tests
    @Test
    void testWithNegativeTotalGradeCount() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(SUBCATEGORY_NAME, 20, -1, 1));
    }

    @Test
    void testWithZeroTotalGradeCount() {
        Subcategory subcategory = new Subcategory(SUBCATEGORY_NAME, 20, 0, 1);
        assertAll(() -> assertEquals("Test", subcategory.getName()),
                () -> assertEquals(20, subcategory.getWeight()),
                () -> assertTrue(subcategory.getGrades().isEmpty()),
                () -> assertEquals(0, subcategory.getMaxGradeCount()),
                () -> assertEquals(1, subcategory.getDroppedGradeCount()));
    }

    @Test
    void testWithOneTotalGradeCount() {
        Subcategory subcategory = new Subcategory(SUBCATEGORY_NAME, 20, 1, 0);
        assertAll(() -> assertEquals("Test", subcategory.getName()),
                () -> assertEquals(20, subcategory.getWeight()),
                () -> assertTrue(subcategory.getGrades().isEmpty()),
                () -> assertEquals(1, subcategory.getMaxGradeCount()),
                () -> assertEquals(0, subcategory.getDroppedGradeCount()));
    }
    //endregion

    //region Dropped Grade Count Tests
    @Test
    void testWithNegativeDroppedGradeCount() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(SUBCATEGORY_NAME, 20, 3, -1));
    }

    @Test
    void testWithZeroDroppedGradeCount() {
        Subcategory subcategory = new Subcategory(SUBCATEGORY_NAME, 20, 3, 0);
        assertAll(() -> assertEquals("Test", subcategory.getName()),
                () -> assertEquals(20, subcategory.getWeight()),
                () -> assertTrue(subcategory.getGrades().isEmpty()),
                () -> assertEquals(3, subcategory.getMaxGradeCount()),
                () -> assertEquals(0, subcategory.getDroppedGradeCount()));
    }

    @Test
    void testWithOneDroppedGradeCount() {
        Subcategory subcategory = new Subcategory(SUBCATEGORY_NAME, 20, 3, 1);
        assertAll(() -> assertEquals("Test", subcategory.getName()),
                () -> assertEquals(20, subcategory.getWeight()),
                () -> assertTrue(subcategory.getGrades().isEmpty()),
                () -> assertEquals(3, subcategory.getMaxGradeCount()),
                () -> assertEquals(1, subcategory.getDroppedGradeCount()));
    }

    @Test
    void testWithDroppedGradeCountGreaterThanTotalGradeCountAtZero() {
        Subcategory subcategory = new Subcategory(SUBCATEGORY_NAME, 20, 0, 1);
        assertAll(() -> assertEquals("Test", subcategory.getName()),
                () -> assertEquals(20, subcategory.getWeight()),
                () -> assertTrue(subcategory.getGrades().isEmpty()),
                () -> assertEquals(0, subcategory.getMaxGradeCount()),
                () -> assertEquals(1, subcategory.getDroppedGradeCount()));
    }

    @Test
    void testWithDroppedGradeCountAndTotalGradeCountEqualZero() {
        Subcategory subcategory = new Subcategory(SUBCATEGORY_NAME, 20, 0, 0);
        assertAll(() -> assertEquals("Test", subcategory.getName()),
                () -> assertEquals(20, subcategory.getWeight()),
                () -> assertTrue(subcategory.getGrades().isEmpty()),
                () -> assertEquals(0, subcategory.getMaxGradeCount()),
                () -> assertEquals(0, subcategory.getDroppedGradeCount()));
    }

    @Test
    void testWithDroppedGradeCountGreaterThanTotalGradeCount() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(SUBCATEGORY_NAME, 20, 3, 4));
    }

    @Test
    void testWithDroppedGradeCountEqualToTotalGradeCount() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(SUBCATEGORY_NAME, 20, 3, 3));
    }
    //endregion
}
