package edu.westga.comp4420.course_grade_calculator.model;

import java.util.ArrayList;

public class Subcategory {

    private static final String INVALID_NAME_ERROR = "Name cannot be null or empty";
    private static final String INVALID_WEIGHT_ERROR = "Weight must be between 0 and 100";
    private static final String NEGATIVE_TOTAL_GRADES_ERROR = "Total grades cannot be negative";
    private static final String NEGATIVE_DROPPED_GRADES_ERROR = "Dropped grades cannot be negative";
    private static final String DROPPED_GRADES_GREATER_THAN_TOTAL_ERROR = "Dropped grades cannot be greater than or equal to total grades";

    private String name;
    private int weight;
    private ArrayList<Grade> grades;
    private int totalGradeCount;
    private int droppedGradeCount;

    /**
     * Creates a Subcategory with the given name, weight, total grades, and dropped grades.
     * Total Grades is what the expected number of grades in the subcategory is supposed to be, if it is zero then there is no limit.
     * Dropped Grades is how many grades are allowed to be dropped from the subcategory, if it is zero then no grades can be dropped.
     * 
     * @param name The name of the subcategory
     * @param weight The weight of the subcategory
     * @param totalGradeCount
     * @param droppedGradeCount
     */
    public Subcategory(String name, int weight, int totalGradeCount, int droppedGradeCount) {
        if (!this.isValidName(name)) {
            throw new IllegalArgumentException(INVALID_NAME_ERROR);
        }
        if (!this.isValidWeight(weight)) {
            throw new IllegalArgumentException(INVALID_WEIGHT_ERROR);
        }
        if (totalGradeCount < 0) {
            throw new IllegalArgumentException(NEGATIVE_TOTAL_GRADES_ERROR);
        }
        if (droppedGradeCount < 0) {
            throw new IllegalArgumentException(NEGATIVE_DROPPED_GRADES_ERROR);
        }
        if (!this.isValidTotalGradeCount(totalGradeCount, droppedGradeCount)) {
            throw new IllegalArgumentException(DROPPED_GRADES_GREATER_THAN_TOTAL_ERROR);
        }
        this.name = name;
        this.weight = weight;
        this.grades = new ArrayList<Grade>();
        this.totalGradeCount = totalGradeCount;
        this.droppedGradeCount = droppedGradeCount;
    }

    //#region Getters

    public String getName() {
        return this.name;
    }

    public int getWeight() {
        return this.weight;
    }

    public ArrayList<Grade> getGrades() {
        return this.grades;
    }

    /**
     * Adds a grade to the subcategory.
     * 
     * @precondition grade != null
     * @postcondition grades.size() == grades.size()@prev + 1
     * 
     * @param grade The grade to add
     */
    public void addGrade(Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Grade cannot be null");
        }
        if (this.totalGradeCount > 0 && this.grades.size() >= this.totalGradeCount) {
            throw new IllegalArgumentException("Cannot add more grades than the total grade count");
        }
        this.grades.add(grade);
    }

    public int getTotalGradeCount() {
        return this.totalGradeCount;
    }

    public int getDroppedGradeCount() {
        return this.droppedGradeCount;
    }
    //#endregion

    //#region Private Methods
    private boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    private boolean isValidWeight(int weight) {
        return weight >= 0 && weight <= 100;
    }

    private boolean isValidTotalGradeCount(int totalGrade, int droppedGrades) {
        if (totalGrade > 0) {
            return droppedGrades < totalGrade;
        }
        return true;
    }
    //#endregion
}
