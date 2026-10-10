package edu.westga.comp4420.course_grade_calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Entry point for the Program
 * 
 * @author Mark Rockers
 */
public class Main extends Application {

    public static final String WINDOW_TITLE = "Course Grade Calculator";
	public static final String MAIN_WINDOW_RESOURCE = "view/codebehind/AddGradeWindow.fxml";

    /**
     * JavaFX entry point
     * 
     * @precondition none
     * @postcondition none
     * 
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(Main.MAIN_WINDOW_RESOURCE));
		Scene scene = new Scene(parent);
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    /**
	 * Primary Java entry point.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		Main.launch(args);
	}

}