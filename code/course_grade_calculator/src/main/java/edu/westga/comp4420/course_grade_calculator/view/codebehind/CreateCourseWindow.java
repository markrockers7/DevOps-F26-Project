package edu.westga.comp4420.course_grade_calculator.view.codebehind;

import edu.westga.comp4420.course_grade_calculator.model.Subsection;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;



public class CreateCourseWindow {

    private static final String VALID_NUMBER_REGEX = "^[0-9]*$";

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
    private ListView<Subsection> subsectionsListView;

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
    
    @FXML
    void initialize() {
        this.errorLabel.setVisible(false);
        this.setBindings();
    }

    @FXML
    void onAddCourseClick()  {
        // Add course logic here
    }

    @FXML
    void onDeleteSubsectionClick() {
        this.subsectionsListView.getItems().remove(this.subsectionsListView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onAddSubsectionClick() {
        var subsectionName = this.subsectionNameTextField.textProperty().get().trim();
        var subsectionWeight = this.subsectionWeightTextField.textProperty().get().trim();
        var maxGrades = this.maxGradesTextField.textProperty().get().trim();
        var droppedGrades = this.droppedGradesTextField.textProperty().get().trim();

        if (subsectionName.isEmpty() || subsectionWeight.isEmpty() || maxGrades.isEmpty() || droppedGrades.isEmpty()) {
            this.errorLabel.setText("All fields must be not empty.");
            this.errorLabel.setVisible(true);
            return;
        }

        var subsectionWeightNumber = Integer.parseInt(subsectionWeight);
        var maxGradesNumber = Integer.parseInt(maxGrades);
        var droppedGradesNumber = Integer.parseInt(droppedGrades);

        try {
            var subsection = new Subsection(subsectionName, subsectionWeightNumber, maxGradesNumber, droppedGradesNumber);
            if (!this.canAddSubsection(subsectionWeightNumber)) {
                throw new IllegalArgumentException("Total weight of all subsections cannot exceed 100%");
            }
            this.subsectionsListView.getItems().add(subsection);
            this.clearSubsectionFields();
            this.errorLabel.setVisible(false);
        } catch (IllegalArgumentException e) {
            this.errorLabel.setText(e.getMessage());
            this.errorLabel.setVisible(true);
        }
    }

    @FXML
    void onBackClick() {
        // Back button logic here
    }

    private void setBindings() {
        this.addCourseButton.disableProperty().bind(this.courseNameTextField.textProperty().isEmpty()
                .or(this.courseIDTextField.textProperty().isEmpty())
                .or(this.semesterTextField.textProperty().isEmpty())
                .or(Bindings.isEmpty(this.subsectionsListView.getItems())));

        this.addSubsectionButton.disableProperty().bind(this.subsectionNameTextField.textProperty().isEmpty()
                .or(this.subsectionWeightTextField.textProperty().isEmpty())
                .or(this.maxGradesTextField.textProperty().isEmpty())
                .or(this.droppedGradesTextField.textProperty().isEmpty()));

        this.deleteSubsectionButton.disableProperty().bind(this.subsectionsListView.getSelectionModel().selectedItemProperty().isNull());

        this.subsectionWeightTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(VALID_NUMBER_REGEX)) {
                this.subsectionWeightTextField.setText(oldValue);
            }
        });

        this.maxGradesTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(VALID_NUMBER_REGEX)) {
                this.maxGradesTextField.setText(oldValue);
            }
        });

        this.droppedGradesTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(VALID_NUMBER_REGEX)) {
                this.droppedGradesTextField.setText(oldValue);
            }
        });
    }

    private void clearSubsectionFields() {
        this.subsectionNameTextField.clear();
        this.subsectionWeightTextField.clear();
        this.maxGradesTextField.clear();
        this.droppedGradesTextField.clear();
    }

    private boolean canAddSubsection(int subsectionWeightNumber) {
        var totalWeight = 0;
        for (Subsection existingSubsection : this.subsectionsListView.getItems()) {
            totalWeight += existingSubsection.getWeight();
        }
        return totalWeight + subsectionWeightNumber <= 100;
    }
}
