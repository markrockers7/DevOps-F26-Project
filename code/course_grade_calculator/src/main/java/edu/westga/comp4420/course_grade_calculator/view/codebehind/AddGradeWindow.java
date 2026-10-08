package edu.westga.comp4420.course_grade_calculator.view.codebehind;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddGradeWindow {

    @FXML
    private AnchorPane addGradeWindow;

    @FXML
    private ListView<?> subsectionGradesListView;

    @FXML
    private Button deleteGradeButton;

    @FXML
    private TextField gradeNameTextField;

    @FXML
    private TextField maxPointsTextField;

    @FXML
    private TextField actualPointsTextField;

    @FXML
    private Button addGradeButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ListView<?> subsectionDetailsListView;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        // Initialization code here
    }

    @FXML
    void onDeleteGradeClick() {
        // Handle delete grade button action
    }

    @FXML
    void onAddGradeClick() {
        // Handle add grade button action
    }

    @FXML
    void onClearFieldClick() {
        // Handle clear fields button action
    }

    @FXML
    void onBackClick() {
        // Handle back button action
    }

}
