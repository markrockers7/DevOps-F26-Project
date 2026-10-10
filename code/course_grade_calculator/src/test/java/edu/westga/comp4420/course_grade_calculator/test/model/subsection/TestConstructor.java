package edu.westga.comp4420.course_grade_calculator.test.model.subsection;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Subsection;

public class TestConstructor {
    private static final String SUBSECTION_NAME = "Test";

    @Test
    void testValidConstructor() {
        Subsection subsection = new Subsection(SUBSECTION_NAME, 20, 3, 1);

        assertAll(() -> assertEquals("Test", subsection.getName()),
                () -> assertEquals(20, subsection.getWeight()),
                () -> assertTrue(subsection.getGrades().isEmpty()),
                () -> assertEquals(3, subsection.getMaxGradeCount()),
                () -> assertEquals(1, subsection.getDroppedGradeCount()));
    }

    //region Name Tests
    @Test
    void testWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Subsection(null, 20, 3, 1));
    }

    @Test
    void testWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Subsection("", 20, 3, 1));
    }
    //#endregion

    //region Weight Tests
    @Test
    void testWithNegativeWeight() {
        assertThrows(IllegalArgumentException.class, () -> new Subsection(SUBSECTION_NAME, -1, 3, 1));
    }

    @Test
    void testWithWeightOfZero() {
        Subsection subsection = new Subsection(SUBSECTION_NAME, 0, 3, 1);
        assertAll(() -> assertEquals("Test", subsection.getName()),
                () -> assertEquals(0, subsection.getWeight()),
                () -> assertTrue(subsection.getGrades().isEmpty()),
                () -> assertEquals(3, subsection.getMaxGradeCount()),
                () -> assertEquals(1, subsection.getDroppedGradeCount()));
    }

    @Test
    void testWithWeightOnfOne() {
        Subsection subsection = new Subsection(SUBSECTION_NAME, 1, 3, 1);
        assertAll(() -> assertEquals("Test", subsection.getName()),
                () -> assertEquals(1, subsection.getWeight()),
                () -> assertTrue(subsection.getGrades().isEmpty()),
                () -> assertEquals(3, subsection.getMaxGradeCount()),
                () -> assertEquals(1, subsection.getDroppedGradeCount()));
    }

    @Test
    void testWithWeightOfNinetyNine() {
        Subsection subsection = new Subsection(SUBSECTION_NAME, 99, 3, 1);
        assertAll(() -> assertEquals("Test", subsection.getName()),
                () -> assertEquals(99, subsection.getWeight()),
                () -> assertTrue(subsection.getGrades().isEmpty()),
                () -> assertEquals(3, subsection.getMaxGradeCount()),
                () -> assertEquals(1, subsection.getDroppedGradeCount()));
    }

    @Test
    void testWithWeightOfOneHundred() {
        Subsection subsection = new Subsection(SUBSECTION_NAME, 100, 3, 1);
        assertAll(() -> assertEquals("Test", subsection.getName()),
                () -> assertEquals(100, subsection.getWeight()),
                () -> assertTrue(subsection.getGrades().isEmpty()),
                () -> assertEquals(3, subsection.getMaxGradeCount()),
                () -> assertEquals(1, subsection.getDroppedGradeCount()));
    }

    @Test
    void testWithWeightOfOneHundredAndOne() {
        assertThrows(IllegalArgumentException.class, () -> new Subsection(SUBSECTION_NAME, 101, 3, 1));
    }
    //#endregion

    //region Total Grade Count Tests
    @Test
    void testWithNegativeTotalGradeCount() {
        assertThrows(IllegalArgumentException.class, () -> new Subsection(SUBSECTION_NAME, 20, -1, 1));
    }

    @Test
    void testWithZeroTotalGradeCount() {
        Subsection subsection = new Subsection(SUBSECTION_NAME, 20, 0, 1);
        assertAll(() -> assertEquals("Test", subsection.getName()),
                () -> assertEquals(20, subsection.getWeight()),
                () -> assertTrue(subsection.getGrades().isEmpty()),
                () -> assertEquals(0, subsection.getMaxGradeCount()),
                () -> assertEquals(1, subsection.getDroppedGradeCount()));
    }

    @Test
    void testWithOneTotalGradeCount() {
        Subsection subsection = new Subsection(SUBSECTION_NAME, 20, 1, 0);
        assertAll(() -> assertEquals("Test", subsection.getName()),
                () -> assertEquals(20, subsection.getWeight()),
                () -> assertTrue(subsection.getGrades().isEmpty()),
                () -> assertEquals(1, subsection.getMaxGradeCount()),
                () -> assertEquals(0, subsection.getDroppedGradeCount()));
    }
    //endregion

    //region Dropped Grade Count Tests
    @Test
    void testWithNegativeDroppedGradeCount() {
        assertThrows(IllegalArgumentException.class, () -> new Subsection(SUBSECTION_NAME, 20, 3, -1));
    }

    @Test
    void testWithZeroDroppedGradeCount() {
        Subsection subsection = new Subsection(SUBSECTION_NAME, 20, 3, 0);
        assertAll(() -> assertEquals("Test", subsection.getName()),
                () -> assertEquals(20, subsection.getWeight()),
                () -> assertTrue(subsection.getGrades().isEmpty()),
                () -> assertEquals(3, subsection.getMaxGradeCount()),
                () -> assertEquals(0, subsection.getDroppedGradeCount()));
    }

    @Test
    void testWithOneDroppedGradeCount() {
        Subsection subsection = new Subsection(SUBSECTION_NAME, 20, 3, 1);
        assertAll(() -> assertEquals("Test", subsection.getName()),
                () -> assertEquals(20, subsection.getWeight()),
                () -> assertTrue(subsection.getGrades().isEmpty()),
                () -> assertEquals(3, subsection.getMaxGradeCount()),
                () -> assertEquals(1, subsection.getDroppedGradeCount()));
    }

    @Test
    void testWithDroppedGradeCountGreaterThanTotalGradeCountAtZero() {
        Subsection subsection = new Subsection(SUBSECTION_NAME, 20, 0, 1);
        assertAll(() -> assertEquals("Test", subsection.getName()),
                () -> assertEquals(20, subsection.getWeight()),
                () -> assertTrue(subsection.getGrades().isEmpty()),
                () -> assertEquals(0, subsection.getMaxGradeCount()),
                () -> assertEquals(1, subsection.getDroppedGradeCount()));
    }

    @Test
    void testWithDroppedGradeCountAndTotalGradeCountEqualZero() {
        Subsection subsection = new Subsection(SUBSECTION_NAME, 20, 0, 0);
        assertAll(() -> assertEquals("Test", subsection.getName()),
                () -> assertEquals(20, subsection.getWeight()),
                () -> assertTrue(subsection.getGrades().isEmpty()),
                () -> assertEquals(0, subsection.getMaxGradeCount()),
                () -> assertEquals(0, subsection.getDroppedGradeCount()));
    }

    @Test
    void testWithDroppedGradeCountGreaterThanTotalGradeCount() {
        assertThrows(IllegalArgumentException.class, () -> new Subsection(SUBSECTION_NAME, 20, 3, 4));
    }

    @Test
    void testWithDroppedGradeCountEqualToTotalGradeCount() {
        assertThrows(IllegalArgumentException.class, () -> new Subsection(SUBSECTION_NAME, 20, 3, 3));
    }
    //endregion
}
