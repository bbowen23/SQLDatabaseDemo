package db;

import common.DBHelper;
import models.ClassInstructor;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Benjamin Bowen
 */
public class ClassInstructorDetailController implements Initializable {

    @FXML Label lblClassID;
    @FXML Label lblName;
    @FXML Label lblInstructorName;
    @FXML Label lblSemester;
    @FXML Label lblYear;
    @FXML Label lblDates;
    @FXML Label lblTimes;
    @FXML Label lblMaxStudents;

    @FXML Button btnDelete;
    @FXML Button btnCancel;

    String classID;
    String instructorID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    public void setClassInstructor(ClassInstructor classInstructor)
    {
        lblClassID.setText(classInstructor.getClassID());
        lblName.setText(classInstructor.getName());
        lblInstructorName.setText(classInstructor.getFullName());
        lblSemester.setText(classInstructor.getSemester());
        lblYear.setText(String.valueOf(classInstructor.getYear()));
        lblDates.setText(classInstructor.getStartDate() + " - " + classInstructor.getEndDate());
        lblTimes.setText(classInstructor.getStartTime() + " - " + classInstructor.getEndTime());
        lblMaxStudents.setText(String.valueOf(classInstructor.getMaxStudents()));

        classID = classInstructor.getClassID();
        instructorID = classInstructor.getInstructorID();
    }

    public void cancel(ActionEvent actionEvent)
    {
        closeDialog(actionEvent);
    }

    public void delete(ActionEvent actionEvent)
    {
        try {
            Connection conn = DBHelper.connect();

            String SQL = "DELETE FROM ClassInstructor " +
                    "WHERE classID=?" +
                    "AND instructorID=?";

            PreparedStatement pst = conn.prepareStatement(SQL);

            pst.setString(1, classID);
            pst.setString(2, instructorID);

            pst.execute(SQL);
            conn.close();
            closeDialog(actionEvent);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void closeDialog(ActionEvent actionEvent)
    {
        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
