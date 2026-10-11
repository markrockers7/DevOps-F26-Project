package edu.westga.comp4420.course_grade_calculator.view.codebehind;

import edu.westga.comp4420.course_grade_calculator.model.Course;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class CourseSelectionWindow {

    @FXML
    private TableColumn<Course, ?> nameTableColumn;

    @FXML
    private TableColumn<Course, ?> averageTableColumn;

    @FXML
    private TableColumn<Course, ?> endGradeTableColumn;

    @FXML
    private TableColumn<Course, ?> assignmentsTableColumn;

    @FXML
    private TableColumn<Course, ?> weightTableColumn;

    @FXML
    private TableView<Course> courseInfoTableView;

    @FXML
    private ListView<Course> courseListView;

    @FXML
    private AnchorPane courseSelectionAnchorPane;

    @FXML
    private Button createCourseButton;

    @FXML
    private Button deleteCourseButton;

    @FXML
    private Button inputGradesButton;

    @FXML
    void initialize() {
        // Initialization logic here
    }

    @FXML
    void onCreateCourseClick() {

    }

    @FXML
    void onDeleteCourseClick() {

    }

    @FXML
    void onInputGradeClick() {

    }
}
