package edu.westga.comp4420.course_grade_calculator.model;

/**
 * Represents a grade for a course item
 * 
 * @author Mark Rockers
 */
public class Grade {
    private static final int MIN_SCORE = 0;
    private static final String INVALID_NAME_ERROR = "Name cannot be null or empty";
    private static final String INVALID_MAX_SCORE_ERROR = "Max score must be greater than 0";
    private static final String INVALID_SCORE_ERROR = "Score must be between " + MIN_SCORE + " and max score";

    private String name;
    private int maxScore;
    private double score;

    /**
     * Creates a new Grade with the given name, maximum score, and score
     * 
     * @precondition name != null && !name.isEmpty() && 
     *               maxScore > MIN_SCORE && 
     *               score >= MIN_SCORE && score <= maxScore
     * @postcondition getName() == name && getMaxScore() == maxScore && getScore() == score
     * @param name The name
     * @param maxScore The maximum score
     * @param score The score
     */
    public Grade(String name, int maxScore, double score) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME_ERROR);
        }
        if (maxScore <= MIN_SCORE) {
            throw new IllegalArgumentException(INVALID_MAX_SCORE_ERROR);
        }
        if (this.isScoreInvalid(score, maxScore)) {
            throw new IllegalArgumentException(INVALID_SCORE_ERROR);
        }
        this.name = name;
        this.maxScore = maxScore;
        this.score = score;
    }

    /**
     * Gets the name of the course item
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return The name of the Grade
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the maximum score for the course item
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return The maximum score of the Grade
     */
    public int getMaxScore() {
        return this.maxScore;
    }

    /**
     * Sets the maximum score for the course item
     * 
     * @precondition maxScore > MIN_SCORE && maxScore >= score
     * @postcondition none
     * 
     * @param maxScore The new maximum score for the Grade
     */
    public void setMaxScore(int maxScore) {
        if (maxScore <= MIN_SCORE || maxScore < this.score) {
            throw new IllegalArgumentException(INVALID_MAX_SCORE_ERROR);
        }
        this.maxScore = maxScore;
    }

    /**
     * Gets the score for the course item
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return The score of the Grade
     */
    public double getScore() {
        return this.score;
    }

    /**
     * Sets the score for the course item
     * 
     * @precondition score >= MIN_SCORE && score <= maxScore
     * @postcondition none
     * 
     * @param score The new score for the Grade
     */
    public void setScore(double score) {
        if (this.isScoreInvalid(score, this.maxScore)) {
            throw new IllegalArgumentException(INVALID_SCORE_ERROR);
        }
        this.score = score;
    }

    private boolean isScoreInvalid(double score, double maxScore) {
        return score < MIN_SCORE || score > maxScore;
    }

    /**
     * Gets the percentage score for the course item
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return The percentage score of the Grade
     */
    public double getPercentage() {
        return (this.score / this.maxScore) * 100;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.score + "/" + this.maxScore + "";
    }
}
