package edu.westga.comp4420.course_grade_calculator.model;

import java.util.ArrayList;

public class Course {

    private static final Integer MANDATORY_WEIGHT = 100;

    private static final String INVALID_NAME_ERROR = "Name cannot be null or empty";
    private static final String INVALID_COURSE_ID_ERROR = "Course ID cannot be null or empty";
    private static final String INVALID_SEMESTER_ERROR = "Semester cannot be null or empty";
    private static final String INVALID_SUBCATEGORY_NULL_ERROR = "Subcategories cannot be null";
    private static final String INVALID_SUBCATEGORY_WEIGHT_ERROR = "Total weight of subcategories must equal " + MANDATORY_WEIGHT;

    private String name;
    private String courseId;
    private String semester;
    private ArrayList<Subcategory> subcategories;

    /**
     * Creates a Course with the given name, course ID, and semester.
     * 
     * @param name     The name of the course
     * @param courseId The course ID of the course
     * @param semester The semester of the course
     * @param subcategories The subcategories of the course
     */
    public Course(String name, String courseId, String semester, ArrayList<Subcategory> subcategories) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME_ERROR);
        }
        if (courseId == null || courseId.isEmpty()) {
            throw new IllegalArgumentException(INVALID_COURSE_ID_ERROR);
        }
        if (semester == null || semester.isEmpty()) {
            throw new IllegalArgumentException(INVALID_SEMESTER_ERROR);
        }
        if (subcategories == null) {
            throw new IllegalArgumentException(INVALID_SUBCATEGORY_NULL_ERROR);
        }
        if (!this.isTotalWeightValid(subcategories)) {
            throw new IllegalArgumentException(INVALID_SUBCATEGORY_WEIGHT_ERROR);
        }
        this.name = name;
        this.courseId = courseId;
        this.semester = semester;
        this.subcategories = subcategories;
    }

    //#region Getters
    /**
     * Returns the name of the course.
     * 
     * @precondition none
     * @postcondition none
     *
     * @return The name of the course
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the course ID of the course.
     * 
     * @precondition none
     * @postcondition none
     *
     * @return The course ID of the course
     */
    public String getCourseId() {
        return this.courseId;
    }

    /**
     * Returns the semester of the course.
     * 
     * @precondition none
     * @postcondition none
     *
     * @return The semester of the course
     */
    public String getSemester() {
        return this.semester;
    }

    /**
     * Returns the subcategories of the course.
     * 
     * @precondition none
     * @postcondition none
     *
     * @return The subcategories of the course
     */
    public ArrayList<Subcategory> getSubcategories() {
        return this.subcategories;
    }
    //#endregion

    //#region Private Methods

    private int getTotalWeight(ArrayList<Subcategory> subcategories) {
        int totalWeight = 0;
        for (Subcategory subcategory : subcategories) {
            totalWeight += subcategory.getWeight();
        }
        return totalWeight;
    }
    private boolean isTotalWeightValid(ArrayList<Subcategory> subcategories) {
        return this.getTotalWeight(subcategories) == MANDATORY_WEIGHT;
    }
    //#endregion
}
