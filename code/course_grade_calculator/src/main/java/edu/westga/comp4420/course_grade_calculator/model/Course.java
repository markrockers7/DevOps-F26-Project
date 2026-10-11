package edu.westga.comp4420.course_grade_calculator.model;

import java.util.ArrayList;

public class Course {

    private static final Integer MANDATORY_WEIGHT = 100;

    private static final String INVALID_NAME_ERROR = "Name cannot be null or empty";
    private static final String INVALID_COURSE_ID_ERROR = "Course ID cannot be null or empty";
    private static final String INVALID_SEMESTER_ERROR = "Semester cannot be null or empty";
    private static final String INVALID_SUBSECTION_NULL_ERROR = "Subsections cannot be null";
    private static final String INVALID_SUBSECTION_WEIGHT_ERROR = "Total weight of subsections must equal " + MANDATORY_WEIGHT;

    private String name;
    private String courseId;
    private String semester;
    private ArrayList<Subsection> subsections;

    /**
     * Creates a Course with the given name, course ID, and semester.
     * 
     * @param name     The name of the course
     * @param courseId The course ID of the course
     * @param semester The semester of the course
     * @param subsections The subsections of the course
     */
    public Course(String name, String courseId, String semester, ArrayList<Subsection> subsections) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME_ERROR);
        }
        if (courseId == null || courseId.isEmpty()) {
            throw new IllegalArgumentException(INVALID_COURSE_ID_ERROR);
        }
        if (semester == null || semester.isEmpty()) {
            throw new IllegalArgumentException(INVALID_SEMESTER_ERROR);
        }
        if (subsections == null) {
            throw new IllegalArgumentException(INVALID_SUBSECTION_NULL_ERROR);
        }
        if (!this.isTotalWeightValid(subsections)) {
            throw new IllegalArgumentException(INVALID_SUBSECTION_WEIGHT_ERROR);
        }
        this.name = name;
        this.courseId = courseId;
        this.semester = semester;
        this.subsections = subsections;
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
     * Returns the Subsections of the course.
     * 
     * @precondition none
     * @postcondition none
     *
     * @return The Subsections of the course
     */
    public ArrayList<Subsection> getSubsections() {
        return this.subsections;
    }

    /**
     * Returns the weighted average grade of the course.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return The weighted average grade of the course
     */
    public double getWeightedAverageGrade() {
        double totalWeightedScore = 0;
        for (Subsection subsection : this.subsections) {
            totalWeightedScore += subsection.getWeightedAverageGrade();
        }
        return totalWeightedScore;
    }

    /**
     * Returns the weighted end grade of the course.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return The weighted end grade of the course
     */
    public double getWeightedEndGrade() {
        double totalWeightedScore = 0;
        for (Subsection subsection : this.subsections) {
            totalWeightedScore += subsection.getWeightedEndGrade();
        }
        return totalWeightedScore;
    }

    @Override 
    public String toString() {
        return this.name + " (" + this.courseId + ") - " + this.semester;
    }
    //#endregion

    //#region Private Methods

    private int getTotalWeight(ArrayList<Subsection> subsections) {
        int totalWeight = 0;
        for (Subsection subsection : subsections) {
            totalWeight += subsection.getWeight();
        }
        return totalWeight;
    }
    private boolean isTotalWeightValid(ArrayList<Subsection> subsections) {
        return this.getTotalWeight(subsections) == MANDATORY_WEIGHT;
    }
    //#endregion
}
