package edu.westga.comp4420.course_grade_calculator.test.model.course;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.comp4420.course_grade_calculator.model.Course;
import edu.westga.comp4420.course_grade_calculator.model.Subsection;

public class TestConstructor {

    private static final String VALID_NAME = "DevOps";
    private static final String VALID_COURSE_ID = "COMP 4420";
    private static final String VALID_SEMESTER = "Fall 2026";

    private ArrayList<Subsection> subsections;

    @BeforeEach 
    void setUp() {
        Subsection subsection = new Subsection("Exam", 100, 0, 0);
        ArrayList<Subsection> subsections = new ArrayList<Subsection>();
        subsections.add(subsection);
        this.subsections = subsections;
    }

    @Test
    void testValidConstructor() {
        Course course = new Course(VALID_NAME, VALID_COURSE_ID, VALID_SEMESTER, this.subsections);
        assertAll(
            () -> assertEquals(VALID_NAME, course.getName()),
            () -> assertEquals(VALID_COURSE_ID, course.getCourseId()),
            () -> assertEquals(VALID_SEMESTER, course.getSemester()),
            () -> assertEquals(1, course.getSubsections().size())
        );
    }

    //#region Invalid Name Tests
    @Test
    void testConstructorWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Course(null, VALID_COURSE_ID, VALID_SEMESTER, this.subsections));
    }

    @Test
    void testConstructorWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Course("", VALID_COURSE_ID, VALID_SEMESTER, this.subsections));
    }
    //#endregion

    //#region Invalid Course ID Tests
    @Test
    void testConstructorWithNullCourseId() {
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, null, VALID_SEMESTER, this.subsections));
    }

    @Test
    void testConstructorWithEmptyCourseId() {
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, "", VALID_SEMESTER, this.subsections));
    }
    //#endregion

    //#region Invalid Semester Tests
    @Test
    void testConstructorWithNullSemester() {
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, VALID_COURSE_ID, null, this.subsections));
    }

    @Test
    void testConstructorWithEmptySemester() {
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, VALID_COURSE_ID, "", this.subsections));
    }
    //#endregion

    //#region Invalid Subsection Tests
    @Test
    void testConstructorWithNullSubsections() {
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, VALID_COURSE_ID, VALID_SEMESTER, null));
    }

    @Test
    void testConstructorWithEmptySubsections() {
        ArrayList<Subsection> emptySubsections = new ArrayList<Subsection>();
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, VALID_COURSE_ID, VALID_SEMESTER, emptySubsections));
    }

    @Test
    void testConstructorWithInvalidSubsectionWeight() {
        Subsection subsection = new Subsection("Quiz", 99, 0, 0);
        ArrayList<Subsection> invalidSubsections = new ArrayList<Subsection>();
        invalidSubsections.add(subsection);
        assertThrows(IllegalArgumentException.class, () -> new Course(VALID_NAME, VALID_COURSE_ID, VALID_SEMESTER, invalidSubsections));
    }
    //#endregion

}
