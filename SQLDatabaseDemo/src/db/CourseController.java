package db;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.ClassInstructor;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView.TableViewSelectionModel;

import models.Student;
import models.ClassStudent;
import models.Course;
import common.DBHelper;

/**
 * Course controller class
 * @author Benjamin Bowen
 */
public class CourseController implements Initializable
{
    // Classes table view and columns
    @FXML TableView<Course> tblCourse;
    @FXML TableColumn<Course, String> colClassID;
    @FXML TableColumn<Course, String> colClassName;
    @FXML TableColumn<Course, String> colInstructorID;
    @FXML TableColumn<Course, String> colInstructorName;
    @FXML TableColumn<Course, String> colStartTime;
    @FXML TableColumn<Course, String> colEndTime;

    // Detail fields
    @FXML public TextField txtClassID;
    @FXML public TextField txtInstructorID;
    @FXML public TextField txtStudentID;

    // Buttons
    @FXML public Button btnSave;
    @FXML public Button btnDelete;
    @FXML public Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO: finish this new piece of technology
        setupClassTable();
        loadClassTable();
        // Is this all?
    }

    public void setupClassTable()
    {
        colClassID.setCellValueFactory(new PropertyValueFactory<>("classID"));
        colClassName.setCellValueFactory(new PropertyValueFactory<>("className"));
        colInstructorID.setCellValueFactory(new PropertyValueFactory<>("instructorID"));
        colInstructorName.setCellValueFactory(new PropertyValueFactory<>("instructorName"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    }

    public void loadClassTable()
    {
        // oh no
        try {
            tblCourse.setItems(null);
            Connection conn = DBHelper.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vwinstructorclass");

            ObservableList<Course> data = FXCollections.observableArrayList();

            while (rs.next())
            {
                String fullName = rs.getString("fName") + " " + rs.getString("lName");
                Course co = new Course(rs.getString("classID"),
                        rs.getString("name"),
                        rs.getString("instructorID"),
                        fullName,
                        rs.getString("startTime"),
                        rs.getString("endTime"));
                data.add(co);
            }

            tblCourse.setItems(data);
            conn.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void add(ActionEvent ae)
    {
        try {
            Connection conn = DBHelper.connect();

            if (txtClassID.getText() != null && txtInstructorID.getText() != null && txtStudentID.getText() == null)
            {
                String SQL = "INSERT INTO ClassInstructor VALUES (?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)";
                PreparedStatement pst = conn.prepareStatement(SQL);

                pst.setString(1, txtClassID.getText());
                pst.setString(2, txtInstructorID.getText());

                pst.executeUpdate();
                System.out.println("Access the database to fill in the rest of the items. I apologize because this project was very hard.");
            }
            else if (txtClassID.getText() != null && txtInstructorID.getText() != null && txtStudentID.getText() != null)
            {
                String SQL = "INSERT INTO ClassInstructorStudent VALUES (?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(SQL);

                pst.setString(1, txtClassID.getText());
                pst.setString(2, txtInstructorID.getText());
                pst.setString(3, txtStudentID.getText());

                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(ActionEvent ae)
    {
        try {
            Connection conn = DBHelper.connect();

            if (txtClassID.getText() != null && txtInstructorID.getText() != null && txtStudentID.getText() == null)
            {
                String SQL = "DELETE FROM ClassInstructor WHERE classID=? AND instructorID=?";
                PreparedStatement pst = conn.prepareStatement(SQL);

                pst.setString(1, txtClassID.getText());
                pst.setString(2, txtInstructorID.getText());

                pst.executeUpdate();
            }
            else if (txtClassID.getText() != null && txtInstructorID.getText() != null && txtStudentID.getText() != null)
            {
                String SQL = "DELETE FROM ClassInstructorStudent WHERE classID=? AND instructorID=? AND studentID=?";
                PreparedStatement pst = conn.prepareStatement(SQL);

                pst.setString(1, txtClassID.getText());
                pst.setString(2, txtInstructorID.getText());
                pst.setString(3, txtStudentID.getText());

                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void cancel(ActionEvent ae)
    {
        txtClassID.setText("");
        txtInstructorID.setText("");
        txtStudentID.setText("");
    }
}
