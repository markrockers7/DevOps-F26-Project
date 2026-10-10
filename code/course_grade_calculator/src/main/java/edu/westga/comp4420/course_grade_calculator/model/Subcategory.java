package edu.westga.comp4420.course_grade_calculator.model;

import java.util.ArrayList;

public class Subcategory {

    private static final String INVALID_NAME_ERROR = "Name cannot be null or empty";
    private static final String INVALID_WEIGHT_ERROR = "Weight must be between 0 and 100";
    private static final String NEGATIVE_TOTAL_GRADES_ERROR = "Total grades cannot be negative";
    private static final String NEGATIVE_DROPPED_GRADES_ERROR = "Dropped grades cannot be negative";
    private static final String DROPPED_GRADES_GREATER_THAN_TOTAL_ERROR = "Dropped grades cannot be greater than or equal to total grades";

    private static final Double DEFAULT_MINIMUM_GRADE = 0.0;

    private String name;
    private int weight;
    private ArrayList<Grade> grades;
    private int maxGradeCount;
    private int droppedGradeCount;

    /**
     * Creates a Subcategory with the given name, weight, total grades, and dropped
     * grades.
     * Total Grades is what the expected number of grades in the subcategory is
     * supposed to be, if it is zero then there is no limit.
     * Dropped Grades is how many grades are allowed to be dropped from the
     * subcategory, if it is zero then no grades can be dropped.
     * 
     * @param name              The name of the subcategory
     * @param weight            The weight of the subcategory
     * @param maxGradeCount   The total number of grades in the subcategory
     * @param droppedGradeCount The number of grades that can be dropped from the subcategory
     */
    public Subcategory(String name, int weight, int maxGradeCount, int droppedGradeCount) {
        if (!this.isValidName(name)) {
            throw new IllegalArgumentException(INVALID_NAME_ERROR);
        }
        if (!this.isValidWeight(weight)) {
            throw new IllegalArgumentException(INVALID_WEIGHT_ERROR);
        }
        if (maxGradeCount < 0) {
            throw new IllegalArgumentException(NEGATIVE_TOTAL_GRADES_ERROR);
        }
        if (droppedGradeCount < 0) {
            throw new IllegalArgumentException(NEGATIVE_DROPPED_GRADES_ERROR);
        }
        if (!this.isValidTotalGradeCount(maxGradeCount, droppedGradeCount)) {
            throw new IllegalArgumentException(DROPPED_GRADES_GREATER_THAN_TOTAL_ERROR);
        }
        this.name = name;
        this.weight = weight;
        this.grades = new ArrayList<Grade>();
        this.maxGradeCount = maxGradeCount;
        this.droppedGradeCount = droppedGradeCount;
    }

    // #region Getters

    /**
     * Gets the name of the subcategory.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return The name of the subcategory
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the weight of the subcategory.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return The weight of the subcategory
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Gets the grades of the subcategory.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return The grades of the subcategory
     */
    public ArrayList<Grade> getGrades() {
        return this.grades;
    }

    /**
     * Gets the total grade count of the subcategory.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return The total grade count of the subcategory
     */
    public int getMaxGradeCount() {
        return this.maxGradeCount;
    }

    /**
     * Gets the dropped grade count of the subcategory.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return The dropped grade count of the subcategory
     */
    public int getDroppedGradeCount() {
        return this.droppedGradeCount;
    }

    /**
     * Calculates the average grade of the subcategory, taking into account any dropped grades.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return the average grade of the subcategory, or 0.0 if there are no grades or not enough grades to calculate an average
     */
    public double getAverageGrade() {
        if (this.grades.isEmpty()) {
            return DEFAULT_MINIMUM_GRADE;
        }
        double total = DEFAULT_MINIMUM_GRADE;
        if (!this.hasDroppedGrades()) {
            for (Grade grade : this.grades) {
                total += grade.getScore();
            }
            total = total / this.grades.size();
        } else {
            if (this.hasMoreGradesThanDropped()) {
                ArrayList<Grade> sortedGrades = new ArrayList<Grade>(this.grades);
                sortedGrades.sort((g1, g2) -> Double.compare(g1.getScore(), g2.getScore()));
                for (int i = this.droppedGradeCount; i < sortedGrades.size(); i++) {
                    total += sortedGrades.get(i).getScore();
                }
                total = total / (sortedGrades.size() - this.droppedGradeCount);
            } else {
                total = DEFAULT_MINIMUM_GRADE;
            }
        }
        return total;
    }

    /**
     * Calculates the weighted average grade of the subcategory.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return the weighted average grade of the subcategory
     */
    public double getWeightedAverageGrade() {
        return this.getAverageGrade() * (this.weight / 100.0);
    }

    // #endregion

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
        if (this.maxGradeCount > 0 && this.grades.size() >= this.maxGradeCount) {
            throw new IllegalArgumentException("Cannot add more grades than the total grade count");
        }
        this.grades.add(grade);
    }

    // #region Private Methods
    private boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    private boolean isValidWeight(int weight) {
        return weight >= 0 && weight <= 100;
    }

    private boolean isValidTotalGradeCount(int maxGradeCount, int droppedGrades) {
        if (maxGradeCount > 0) {
            return droppedGrades < maxGradeCount;
        }
        return true;
    }

    private boolean hasDroppedGrades() {
        return this.droppedGradeCount > 0;
    }

    private boolean hasMoreGradesThanDropped() {
        return this.grades.size() > this.droppedGradeCount;
    }
    // #endregion
}
