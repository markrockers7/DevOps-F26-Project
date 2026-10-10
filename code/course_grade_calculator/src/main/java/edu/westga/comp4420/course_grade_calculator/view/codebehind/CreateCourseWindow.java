package edu.westga.comp4420.course_grade_calculator.view.codebehind;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;



public class CreateCourseWindow {

    @FXML
    private AnchorPane createCourseAnchorPane;

    @FXML
    private TextField courseNameTextField;

    @FXML
    private TextField courseIDTextField;

    @FXML
    private TextField semesterTextField;

    @FXML
    private Button addCourseButton;

    @FXML
    private ListView<?> subsectionsListView;

    @FXML
    private Button deleteSubsectionButton;

    @FXML
    private TextField subsectionNameTextField;

    @FXML
    private TextField subsectionWeightTextField;

    @FXML
    private TextField maxGradesTextField;
    
    @FXML
    private TextField droppedGradesTextField;

    @FXML
    private Button addSubsectionButton;

    @FXML
    private Button backButton;

    @FXML
    private Label errorLabel;
    

}
