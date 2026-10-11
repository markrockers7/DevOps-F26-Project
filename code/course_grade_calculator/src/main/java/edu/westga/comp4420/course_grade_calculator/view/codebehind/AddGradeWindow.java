package edu.westga.comp4420.course_grade_calculator.view.codebehind;

import java.io.IOException;

import edu.westga.comp4420.course_grade_calculator.Main;
import edu.westga.comp4420.course_grade_calculator.model.Course;
import edu.westga.comp4420.course_grade_calculator.model.Grade;
import edu.westga.comp4420.course_grade_calculator.model.Subsection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private ListView<Subsection> courseSubsectionListView;

    @FXML
    private Button backButton;

    @FXML
    private Label errorLabel;

    private Course course;

    @FXML
    void initialize() {
        this.errorLabel.setVisible(false);
        this.setBindings();
    }

    public void setCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        this.course = course;
        this.loadCourseSubsections();
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
            if (this.courseSubsectionListView.getSelectionModel().getSelectedItem() == null) {
                this.errorLabel.setText("Please select a subsection to add the grade to.");
                this.errorLabel.setVisible(true);
                return;
            }
            this.courseSubsectionListView.getSelectionModel().getSelectedItem().addGrade(newGrade);
            this.loadSubsectionGrades(this.courseSubsectionListView.getSelectionModel().getSelectedItem());
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
        try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(Main.COURSE_SELECTION_WINDOW));
			loader.load();
            CourseSelectionWindow controller = loader.getController();
            controller.loadCourse(this.course);
			Parent parent = loader.getRoot();
			Scene scene = new Scene(parent);
			Stage activeStage = (Stage) this.backButton.getScene().getWindow();
            activeStage.setTitle(Main.WINDOW_TITLE);
			activeStage.setScene(scene);
			activeStage.show();
		} catch (IOException error) {
			Alert errorBox = new Alert(AlertType.ERROR);
			errorBox.setContentText("Unable to open add window");
			errorBox.showAndWait();
		}
    }

    private void setBindings() {
        this.setPointInputValidation();
        this.setAddButtonToggling();

        this.deleteGradeButton.disableProperty().bind(
            this.subsectionGradesListView.getSelectionModel().selectedItemProperty().isNull()
        );

        this.courseSubsectionListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.loadSubsectionGrades(newValue);
        });
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

    private void loadCourseSubsections() {
        if (this.course != null) {
            this.courseSubsectionListView.getItems().clear();
            this.courseSubsectionListView.getItems().addAll(this.course.getSubsections());
        }
    }

    private void loadSubsectionGrades(Subsection subsection) {
        if (subsection != null) {
            this.subsectionGradesListView.getItems().clear();
            this.subsectionGradesListView.getItems().addAll(subsection.getGrades());
        }
    }
}
