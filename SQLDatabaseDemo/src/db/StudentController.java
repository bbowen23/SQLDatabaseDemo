package db;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.ClassInstructor;
import models.Instructor;

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
import common.DBHelper;

/**
 * Student controller class
 * @author Benjamin Bowen
 */
public class StudentController implements Initializable
{
    // Student table view and columns
    @FXML public TableView<Student> tblStudent;
    @FXML public TableColumn<Student, String> colID;
    @FXML public TableColumn<Student, String> colFirstName;
    @FXML public TableColumn<Student, String> colLastName;
    @FXML public TableColumn<Student, String> colMajor;
    @FXML public TableColumn<Student, String> colStudentYear;
    @FXML public TableColumn<Student, String> colEmail;
    @FXML public TableColumn<Student, String> colPhone;

    // ClassStudent table view and columns
    @FXML public TableView<ClassStudent> tblClassStudent;
    @FXML public TableColumn<ClassInstructor, String> colClassID;
    @FXML public TableColumn<ClassInstructor, String> colStudentID;
    @FXML public TableColumn<ClassInstructor, String> colSemester;
    @FXML public TableColumn<ClassInstructor, Integer> colYear;
    @FXML public TableColumn<ClassInstructor, String> colRoom;
    @FXML public TableColumn<ClassInstructor, String> colStartDate;
    @FXML public TableColumn<ClassInstructor, String> colStartTime;
    @FXML public TableColumn<ClassInstructor, String> colEndDate;
    @FXML public TableColumn<ClassInstructor, String> colEndTime;
    @FXML public TableColumn<ClassInstructor, Integer> colMaxStudents;

    // Detail fields
    @FXML public TextField txtID;
    @FXML public TextField txtFirstName;
    @FXML public TextField txtLastName;
    @FXML public TextField txtEmail;
    @FXML public TextField txtMajor;
    @FXML public TextField txtYear;
    @FXML public TextField txtPhone;

    // Buttons
    @FXML public Button btnSave;
    @FXML public Button btnAdd;
    @FXML public Button btnDelete;
    @FXML public Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // done

        setupStudentTable();
        loadStudentTable();
        setStudentTableSelection();

        setupClassStudentTable();
        setClassStudentTableSelection();
    }

    private void setupStudentTable()
    {
        colID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colMajor.setCellValueFactory(new PropertyValueFactory<>("major"));
        colStudentYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void setupClassStudentTable()
    {
        colClassID.setCellValueFactory(new PropertyValueFactory<>("classID"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    }

    private void loadStudentTable()
    {
        try {
            tblStudent.setItems(null);
            Connection conn = DBHelper.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");

            ObservableList<Student> data = FXCollections.observableArrayList();

            while (rs.next())
            {
                Student st = new Student(rs.getString("studentID"),
                        rs.getString("fName"),
                        rs.getString("lName"),
                        rs.getString("major"),
                        rs.getString("year"),
                        rs.getString("email"),
                        rs.getString("phone"));
                data.add(st);
            }

            tblStudent.setItems(data);
            conn.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadClassStudentTable(String id)
    {
        try {
            tblClassStudent.setItems(null);
            Connection conn = DBHelper.connect();
            Statement stmt = conn.createStatement();
            String SQL = "SELECT * FROM vwstudentclass WHERE studentID=?";

            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setString(1, txtID.getText());

            ResultSet rs = pst.executeQuery();

            ObservableList<ClassStudent> data = FXCollections.observableArrayList();

            while (rs.next())
            {
                ClassStudent cs = new ClassStudent(
                        rs.getString("classID"),
                        rs.getString("semester"),
                        rs.getInt("year"),
                        rs.getString("room"),
                        rs.getString("startDate"),
                        rs.getString("startTime"),
                        rs.getString("endDate"),
                        rs.getString("endTime"),
                        rs.getString("studentID"),
                        rs.getString("fName"),
                        rs.getString("lName"));
                data.add(cs);
            }

            tblClassStudent.setItems(data);

            conn.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void setStudentTableSelection()
    {
        TableViewSelectionModel<Student> selectionModel = tblStudent.getSelectionModel();
        ObservableList<Student> selectedItems = selectionModel.getSelectedItems();

        selectedItems.addListener(new ListChangeListener<Student>()
        {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Student> change)
            {
                System.out.println("Selection changed: " + change.getList());
                Student stu = change.getList().get(0);
                loadDetails(stu);
            }
        });
    }

    private void setClassStudentTableSelection()
    {
        TableViewSelectionModel<ClassStudent> selectionModel = tblClassStudent.getSelectionModel();
        ObservableList<ClassStudent> selectedItems = selectionModel.getSelectedItems();

        selectedItems.addListener(new ListChangeListener<ClassStudent>()
        {
            @Override
            public void onChanged(ListChangeListener.Change<? extends ClassStudent> change)
            {
                System.out.println("Selection changed: " + change.getList());
                ClassStudent classStu = change.getList().get(0);

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("classStudentDetail.fxml"));
                    Parent root = loader.load();
                    ClassStudentDetailController csdc = loader.getController();

                    if (csdc != null)
                        csdc.setClassStudent(classStu);

                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    public void save(ActionEvent ae)
    {
        try {
            Connection conn = DBHelper.connect();

            if (btnSave.getText().equalsIgnoreCase("Update"))
            {
                String SQL = "UPDATE Student SET studentID=?, fName=?, lName=?, major=?, year=?, email=?, phone=? WHERE studentID=?";
                PreparedStatement pst = conn.prepareStatement(SQL);

                pst.setString(1, txtID.getText());
                pst.setString(2, txtFirstName.getText());
                pst.setString(3, txtLastName.getText());
                pst.setString(4, txtMajor.getText());
                pst.setString(5, txtYear.getText());
                pst.setString(6, txtEmail.getText());
                pst.setString(7, txtPhone.getText());
                pst.setString(8, txtID.getText());

                pst.executeUpdate();
            }
            else
            {
                String SQL = "INSERT INTO Student VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(SQL);

                pst.setString(1, txtID.getText());
                pst.setString(2, txtFirstName.getText());
                pst.setString(3, txtLastName.getText());
                pst.setString(4, txtMajor.getText());
                pst.setString(5, txtYear.getText());
                pst.setString(6, txtEmail.getText());
                pst.setString(7, txtPhone.getText());

                pst.executeUpdate();
            }

            loadStudentTable();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void add(ActionEvent actionEvent)
    {
        loadDetails(null);
    }

    public void cancel(ActionEvent actionEvent)
    {
        loadDetails(null);
    }

    public void delete(ActionEvent ae)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Record");
        alert.setContentText("Are you sure you want to delete?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeMaybe = new ButtonType("Maybe"); // ???
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeMaybe, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonTypeYes)
        {
            try {
                Connection conn = DBHelper.connect();

                String SQL = "DELETE FROM Instructor WHERE studentID=?";
                PreparedStatement pst = conn.prepareStatement(SQL);
                pst.setString(1, txtID.getText());

                pst.execute(SQL);
                conn.close();
                loadStudentTable();
                loadDetails(null);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        else
        {
            // ... user chose CANCEL or closed the dialog
        }
    }

    private void loadDetails(Student student)
    {
        if (student != null)
        {
            txtID.setText(student.getStudentID());
            txtFirstName.setText(student.getFirstName());
            txtLastName.setText(student.getLastName());
            txtMajor.setText(student.getMajor());
            txtYear.setText(student.getYear());
            txtEmail.setText(student.getEmail());
            txtPhone.setText(student.getPhone());
            btnSave.setText("Update");
            loadClassStudentTable(txtID.getText());
        }
        else
        {
            txtID.setText("");
            txtFirstName.setText("");
            txtLastName.setText("");
            txtMajor.setText("");
            txtYear.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            btnSave.setText("Insert");
            tblClassStudent.setItems(null);
        }
    }

    // When adding a student to a class, you want to grab the classID and instructorID from classinstructor. Then combine
    // those with the given studentID and put them into classinstructorstudent.
}
