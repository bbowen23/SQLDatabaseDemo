package db;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;

/**
 * Main class
 *
 * Program version
 * @version 0.8.3
 *
 * @author Benjamin Bowen
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Scanner s = new Scanner(System.in);
        String answer = "";

        while (!answer.equals("instructor") && !answer.equals("student") && !answer.equals("course"))
        {
            System.out.println("Enter 'instructor' to manage instructors. Enter 'student' to manage students. Enter 'course' to manage courses.");
            answer = s.nextLine();

            if (!answer.equals("instructor") && !answer.equals("student") && !answer.equals("course"))
                System.out.println("ERROR: Please provide a valid response.");
        }

        answer += ".fxml";

        Parent root = FXMLLoader.load(getClass().getResource(answer));

        Scene scene = new Scene(root);
        stage.setTitle("Student Registration");

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
