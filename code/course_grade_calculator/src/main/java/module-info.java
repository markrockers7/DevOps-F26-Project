module edu.westga.comp4420.course_grade_calculator {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.westga.comp4420.course_grade_calculator to javafx.fxml;
    exports edu.westga.comp4420.course_grade_calculator;
}
