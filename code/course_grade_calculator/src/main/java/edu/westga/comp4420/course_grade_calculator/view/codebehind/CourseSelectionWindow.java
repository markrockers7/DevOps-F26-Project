package edu.westga.comp4420.course_grade_calculator.view.codebehind;

import java.util.ArrayList;

import edu.westga.comp4420.course_grade_calculator.model.Course;
import edu.westga.comp4420.course_grade_calculator.model.Grade;
import edu.westga.comp4420.course_grade_calculator.model.Subsection;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class CourseSelectionWindow {

    @FXML
    private TableColumn<Subsection, String> nameTableColumn;

    @FXML
    private TableColumn<Subsection, Double> averageTableColumn;

    @FXML
    private TableColumn<Subsection, Double> endGradeTableColumn;

    @FXML
    private TableColumn<Subsection, Integer> assignmentsTableColumn;

    @FXML
    private TableColumn<Subsection, Integer> weightTableColumn;

    @FXML
    private TableView<Subsection> courseInfoTableView;

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
    private Label courseGradeResultLabel;

    @FXML
    void initialize() {
        this.setBindings();
        this.inputDummyCourse();
        this.setTableViewFormatting();

        this.courseListView.getSelectionModel().selectedItemProperty().addListener((obs, oldCourse, course) -> this.showCourse(course));
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

    private void setBindings() {
        this.inputGradesButton.disableProperty().bind(this.courseListView.getSelectionModel().selectedItemProperty().isNull());
        this.deleteCourseButton.disableProperty().bind(this.courseListView.getSelectionModel().selectedItemProperty().isNull());
    }

    private void setTableViewFormatting() {
        this.nameTableColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        this.averageTableColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getAverageGrade()));
        this.endGradeTableColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getEndGrade()));
        this.assignmentsTableColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getGradeCount()));
        this.weightTableColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getWeight()));

        this.averageTableColumn.setCellFactory(column -> new TableCell<Subsection, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item));
                }
            }
        });
        this.endGradeTableColumn.setCellFactory(column -> new TableCell<Subsection, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item));
                }
            }
        });
    }

    private void showCourse(Course course) {
        if (course == null) {
            this.courseInfoTableView.getItems().clear();
            this.courseGradeResultLabel.setText("");
            this.nameTableColumn.setText("Course");
            return;
        }
        this.nameTableColumn.setText(course.getName());
        this.courseInfoTableView.getItems().setAll(course.getSubsections());
        this.courseGradeResultLabel.setText(String.format("Average Grade: %.2f | End Grade: %.2f", course.getWeightedAverageGrade(), course.getWeightedEndGrade()));
    }

    private void inputDummyCourse() {
        var midtermExams = new Subsection("Midterm Exams", 30, 2, 0);
        var midtermExam1 = new Grade("Midterm 1", 103, 88);
        midtermExams.addGrade(midtermExam1);

        var finalExam = new Subsection("Final Exams", 15, 1, 0);
        var finalExamGrade = new Grade("Final Exam", 100, 84);
        finalExam.addGrade(finalExamGrade);

        var projects = new Subsection("Projects", 30, 0, 0);
        var project1 = new Grade("Project 1", 100, 95);
        var project2 = new Grade("Project 2", 100, 90);
        projects.addGrade(project1);
        projects.addGrade(project2);

        var labs = new Subsection("Labs", 25, 6, 0);
        var lab1 = new Grade("Lab 1", 10, 10);
        var lab2 = new Grade("Lab 2", 10, 10);
        labs.addGrade(lab1);
        labs.addGrade(lab2);

        var subsections = new ArrayList<Subsection>();
        subsections.add(midtermExams);
        subsections.add(finalExam);
        subsections.add(projects);
        subsections.add(labs);
        var course = new Course("DevOps", "COMP 4420", "Fall 2026", subsections);
        this.courseListView.getItems().add(course);
    }
}
