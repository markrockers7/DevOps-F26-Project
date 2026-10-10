package edu.westga.comp4420.course_grade_calculator.view.codebehind;

import edu.westga.comp4420.course_grade_calculator.model.Grade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddGradeWindow {

    private static final String MAX_POINTS_REGEX = "^[0-9]*$";
    private static final String ACTUAL_POINTS_REGEX = "^[0-9]*([.][0-9]{0,2})?$";

    @FXML
    private AnchorPane addGradeWindow;

    @FXML
    private ListView<Grade> subsectionGradesListView;

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
    private Label errorLabel;

    @FXML
    void initialize() {
        this.errorLabel.setVisible(false);
        this.setBindings();
    }

    @FXML
    void onDeleteGradeClick() {
        var selectedGrade = this.subsectionGradesListView.getSelectionModel().getSelectedItem();
        this.subsectionGradesListView.getItems().remove(selectedGrade);
    }

    @FXML
    void onAddGradeClick() {
        try {
            String gradeName = this.gradeNameTextField.getText();
            String maxPoints = this.maxPointsTextField.getText();
            String actualPoints = this.actualPointsTextField.getText();
            var maxPointsValue = Integer.parseInt(maxPoints);
            var actualPointsValue = Double.parseDouble(actualPoints);

            if (gradeName.isBlank()) {
                this.errorLabel.setText("Grade name cannot be blank.");
                this.errorLabel.setVisible(true);
                return;
            }
            if (actualPointsValue > maxPointsValue) {
                this.errorLabel.setText("Actual points cannot exceed maximum points.");
                this.errorLabel.setVisible(true);
                return;
            }

            Grade newGrade = new Grade(gradeName, maxPointsValue, actualPointsValue);
            this.subsectionGradesListView.getItems().add(newGrade);
            this.errorLabel.setVisible(false);
            this.clearFields();

        } catch (Exception e) {
            this.errorLabel.setText("An error occurred while adding the grade.");
            this.errorLabel.setVisible(true);
        }
    }

    @FXML
    void onClearFieldClick() {
        this.clearFields();
    }

    private void clearFields() {
        this.gradeNameTextField.clear();
        this.maxPointsTextField.clear();
        this.actualPointsTextField.clear();
    }

    @FXML
    void onBackClick() {
        // Handle back button action
    }

    private void setBindings() {
        this.setPointInputValidation();
        this.setAddButtonToggling();

        this.deleteGradeButton.disableProperty().bind(
            this.subsectionGradesListView.getSelectionModel().selectedItemProperty().isNull()
        );
    }

    private void setAddButtonToggling() {
        this.addGradeButton.disableProperty().bind(
            this.gradeNameTextField.textProperty().isEmpty()
                .or(this.maxPointsTextField.textProperty().isEmpty())
                .or(this.actualPointsTextField.textProperty().isEmpty())
        );
    }

    private void setPointInputValidation() {
        this.maxPointsTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(MAX_POINTS_REGEX)) {
                this.maxPointsTextField.setText(oldValue);
            }
        });
        this.actualPointsTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(ACTUAL_POINTS_REGEX)) {
                this.actualPointsTextField.setText(oldValue);
            }
        });
    }
}
