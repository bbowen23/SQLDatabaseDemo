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
import models.ClassStudent;

/**
 * FXML Controller class for ClassStudent
 * @author Benjamin Bowen
 */
public class ClassStudentDetailController implements Initializable
{
    @FXML Label lblClassID;
    @FXML Label lblStudentName;
    @FXML Label lblSemester;
    @FXML Label lblYear;
    @FXML Label lblDates;
    @FXML Label lblTimes;
    @FXML Label lblMaxStudents;

    @FXML Button btnDelete;
    @FXML Button btnCancel;

    String classID;
    String studentID;

    // Initialize controller
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO: blah
    }

    public void setClassStudent(ClassStudent classStudent)
    {
        lblClassID.setText(classStudent.getClassID());
        lblStudentName.setText(classStudent.getFullName()); // fix
        lblSemester.setText(classStudent.getSemester());
        lblYear.setText(String.valueOf(classStudent.getYear())); // THAT'S AN INTEGER, NOT FRESH/SOPH/ETC.
        lblDates.setText(classStudent.getStartDate() + " - " + classStudent.getEndDate());
        lblTimes.setText(classStudent.getStartTime() + " - " + classStudent.getEndTime());

        classID = classStudent.getClassID();
        studentID = classStudent.getStudentID();
    }

    public void cancel(ActionEvent actionEvent)
    {
        closeDialog(actionEvent);
    }

    private void closeDialog(ActionEvent actionEvent)
    {
        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void delete(ActionEvent actionEvent)
    {
        try {
            Connection conn = DBHelper.connect();

            String SQL = "DELETE FROM ClassInstructorStudent " +
                    "WHERE classID=?" +
                    "AND studentID=?";

            PreparedStatement pst = conn.prepareStatement(SQL);

            pst.setString(1, classID);
            pst.setString(2, studentID);

            pst.execute(SQL);
            conn.close();
            closeDialog(actionEvent);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
