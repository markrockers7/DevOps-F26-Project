package edu.westga.comp4420.course_grade_calculator.test.model.course;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Course;
import edu.westga.comp4420.course_grade_calculator.model.Subcategory;

public class TestConstructor {

    private static final String VALID_NAME = "DevOps";
    private static final String VALID_COURSE_ID = "COMP 4420";
    private static final String VALID_SEMESTER = "Fall 2026";

    private ArrayList<Subcategory> subcategories;

    @BeforeEach 
    void setUp() {
        Subcategory subcategory = new Subcategory("Exam", 100, 0, 0);
        ArrayList<Subcategory> subcategories = new ArrayList<Subcategory>();
        subcategories.add(subcategory);
        this.subcategories = subcategories;
    }

    @Test
    void testValidConstructor() {
        Course course = new Course(VALID_NAME, VALID_COURSE_ID, VALID_SEMESTER, this.subcategories);
        assertAll(
            () -> assertEquals(VALID_NAME, course.getName()),
            () -> assertEquals(VALID_COURSE_ID, course.getCourseId()),
            () -> assertEquals(VALID_SEMESTER, course.getSemester()),
            () -> assertEquals(1, course.getSubcategories().size())
        );
    }

    //#region Invalid Name Tests
    @Test
    void testConstructorWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Course(null, VALID_COURSE_ID, VALID_SEMESTER, this.subcategories));
    }

    @Test
    void testConstructorWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Course("", VALID_COURSE_ID, VALID_SEMESTER, this.subcategories));
    }
    //#endregion

    //#region Invalid Course ID Tests
    @Test
    void testConstructorWithNullCourseId() {
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, null, VALID_SEMESTER, this.subcategories));
    }

    @Test
    void testConstructorWithEmptyCourseId() {
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, "", VALID_SEMESTER, this.subcategories));
    }
    //#endregion

    //#region Invalid Semester Tests
    @Test
    void testConstructorWithNullSemester() {
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, VALID_COURSE_ID, null, this.subcategories));
    }

    @Test
    void testConstructorWithEmptySemester() {
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, VALID_COURSE_ID, "", this.subcategories));
    }
    //#endregion

    //#region Invalid Subcategory Tests
    @Test
    void testConstructorWithNullSubcategories() {
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, VALID_COURSE_ID, VALID_SEMESTER, null));
    }

    @Test
    void testConstructorWithEmptySubcategories() {
        ArrayList<Subcategory> emptySubcategories = new ArrayList<Subcategory>();
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, VALID_COURSE_ID, VALID_SEMESTER, emptySubcategories));
    }

    @Test
    void testConstructorWithInvalidSubcategoryWeight() {
        Subcategory subcategory1 = new Subcategory("Quiz", 99, 0, 0);
        ArrayList<Subcategory> invalidSubcategories = new ArrayList<Subcategory>();
        invalidSubcategories.add(subcategory1);
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, VALID_COURSE_ID, VALID_SEMESTER, invalidSubcategories));
    }
    //#endregion

}
