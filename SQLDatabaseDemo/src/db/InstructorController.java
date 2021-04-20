package db;

import common.DBHelper;
import models.ClassInstructor;
import models.Instructor;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Instructor controller class
 * @author Benjamin Bowen
 */
public class InstructorController implements Initializable
{
    // Instructor TableView and Table Columns
    @FXML public TableView<Instructor> tblInstructor;
    @FXML public TableColumn<Instructor, String> colID;
    @FXML public TableColumn<Instructor, String> colFirstName;
    @FXML public TableColumn<Instructor, String> colLastName;
    @FXML public TableColumn<Instructor, String> colEmail;
    @FXML public TableColumn<Instructor, String> colOffice;
    @FXML public TableColumn<Instructor, String> colPhone;

    // ClassInstructor TableView and Table Columns
    @FXML public TableView<ClassInstructor> tblClassInstructor;
    @FXML public TableColumn<ClassInstructor, String> colClassID;
    @FXML public TableColumn<ClassInstructor, String> colName;
    @FXML public TableColumn<ClassInstructor, String> colSemester;
    @FXML public TableColumn<ClassInstructor, Integer> colYear;
    @FXML public TableColumn<ClassInstructor, String> colRoom;
    @FXML public TableColumn<ClassInstructor, String> colStartDate;
    @FXML public TableColumn<ClassInstructor, String> colStartTime;
    @FXML public TableColumn<ClassInstructor, String> colEndDate;
    @FXML public TableColumn<ClassInstructor, String> colEndTime;
    @FXML public TableColumn<ClassInstructor, Integer> colMaxStudents;

    // Detail Fields
    @FXML public TextField txtID;
    @FXML public TextField txtFirstName;
    @FXML public TextField txtLastName;
    @FXML public TextField txtEmail;
    @FXML public TextField txtOffice;
    @FXML public TextField txtPhone;

    // Butons
    @FXML public Button btnSave;
    @FXML public Button btnAdd;
    @FXML public Button btnDelete;
    @FXML public Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setupInstructorTable();
        loadInstructorTable();
        setInstructorTableSelection();

        setupClassInstructorTable();
        setClassInstructorTableSelection();
    }

    private void setupInstructorTable()
    {
        // Setup columns to Class
        colID.setCellValueFactory(new PropertyValueFactory<>("instructorID"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colOffice.setCellValueFactory(new PropertyValueFactory<>("office"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void setupClassInstructorTable()
    {
        // Setup columns to Class
        colClassID.setCellValueFactory(new PropertyValueFactory<>("classID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colMaxStudents.setCellValueFactory(new PropertyValueFactory<>("maxStudents"));
    }

    private void loadClassInstructorTable(String id)
    {
        try {
            tblClassInstructor.setItems(null);
            Connection conn = DBHelper.connect();
            Statement stmt = conn.createStatement();
            String SQL = "SELECT * FROM vwinstructorclass " +
                    "WHERE instructorID=?";

            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setString(1, txtID.getText());

            ResultSet rs = pst.executeQuery();

            ObservableList<ClassInstructor> data =
                    FXCollections.observableArrayList();

            while(rs.next()) {
                ClassInstructor ci = new ClassInstructor(
                        rs.getString("classID"),
                        rs.getString("name"),
                        rs.getString("instructorID"),
                        rs.getString("fName"),
                        rs.getString("lName"),
                        rs.getString("semester"),
                        rs.getInt("year"),
                        rs.getString("room"),
                        rs.getString("startDate"),
                        rs.getString("startTime"),
                        rs.getString("endDate"),
                        rs.getString("endTime"),
                        rs.getInt("maxStudents"));
                data.add(ci);
            }

            tblClassInstructor.setItems(data);

            conn.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadInstructorTable()
    {
        try {
            tblInstructor.setItems(null);
            Connection conn = DBHelper.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Instructor");

            ObservableList<Instructor> data = FXCollections.observableArrayList();

            while(rs.next())
            {
                Instructor in = new Instructor(rs.getString("instructorID"),
                        rs.getString("fName"),
                        rs.getString("lName"),
                        rs.getString("email"),
                        rs.getString("officeNumber"),
                        rs.getString("phone"));
                data.add(in);
            }

            tblInstructor.setItems(data);

            conn.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void setInstructorTableSelection()
    {
        TableViewSelectionModel<Instructor> selectionModel =
                tblInstructor.getSelectionModel();
        ObservableList<Instructor> selectedItems =
                selectionModel.getSelectedItems();

        selectedItems.addListener(new ListChangeListener<Instructor>()
        {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Instructor>
                                          change)
            {
                System.out.println("Selection changed: " + change.getList());
                Instructor inst = change.getList().get(0);
                loadDetails(inst);
            }
        });
    }

    private void setClassInstructorTableSelection()
    {
        TableViewSelectionModel<ClassInstructor> selectionModel =
                tblClassInstructor.getSelectionModel();
        ObservableList<ClassInstructor> selectedItems =
                selectionModel.getSelectedItems();

        selectedItems.addListener(new ListChangeListener<ClassInstructor>()
        {
            @Override
            public void onChanged(ListChangeListener.Change<? extends ClassInstructor>
                                          change) {
                System.out.println("Selection changed: " + change.getList());
                ClassInstructor classInt = change.getList().get(0);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("classInstructorDetail.fxml"));
                    Parent root = loader.load();
                    ClassInstructorDetailController cidc = loader.getController();
                    if (cidc != null)
                        cidc.setClassInstructor(classInt);

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

    public void save(ActionEvent actionEvent)
    {
        try {
            Connection conn = DBHelper.connect();

            if (btnSave.getText().equalsIgnoreCase("Update"))
            {
                String SQL = "UPDATE Instructor SET " +
                        "instructorID=?, " +
                        "fName=?, " +
                        "lName=?, " +
                        "email=?, " +
                        "phone=?, " +
                        "officeNumber=? " +
                        "WHERE instructorID=?";

                PreparedStatement pst = conn.prepareStatement(SQL);
                pst.setString(1, txtID.getText());
                pst.setString(2, txtFirstName.getText());
                pst.setString(3, txtLastName.getText());
                pst.setString(4, txtEmail.getText());
                pst.setString(5, txtPhone.getText());
                pst.setString(6, txtOffice.getText());
                pst.setString(7, txtID.getText());

                pst.executeUpdate();
            }
            else
            {
                String SQL = "INSERT INTO Instructor " +
                        "VALUES (?, ?, ?, ?, ? , ?)";
                PreparedStatement pst = conn.prepareStatement(SQL);
                pst.setString(1, txtID.getText());
                pst.setString(2, txtFirstName.getText());
                pst.setString(3, txtLastName.getText());
                pst.setString(4, txtOffice.getText());
                pst.setString(5, txtEmail.getText());
                pst.setString(6, txtPhone.getText());

                pst.executeUpdate();
            }

            loadInstructorTable();

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

    public void delete(ActionEvent actionEvent)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Record");
        alert.setContentText("Are you sure you want to delete?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeMaybe = new ButtonType("Maybe");
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeMaybe, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonTypeYes)
        {
            try {
                Connection conn = DBHelper.connect();

                String SQL = "DELETE FROM Instructor WHERE instructorID=?";
                PreparedStatement pst = conn.prepareStatement(SQL);
                pst.setString(1, txtID.getText());

                pst.execute(SQL);
                conn.close();
                loadInstructorTable();
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

    private void loadDetails(Instructor instructor)
    {
        if (instructor != null)
        {
            txtID.setText(instructor.getInstructorID());
            txtFirstName.setText(instructor.getFirstName());
            txtLastName.setText(instructor.getLastName());
            txtEmail.setText(instructor.getEmail());
            txtOffice.setText(instructor.getOffice());
            txtPhone.setText(instructor.getPhone());
            btnSave.setText("Update");
            loadClassInstructorTable(txtID.getText());
        }
        else
        {
            txtID.setText("");
            txtFirstName.setText("");
            txtLastName.setText("");
            txtEmail.setText("");
            txtOffice.setText("");
            txtPhone.setText("");
            btnSave.setText("Insert");
            tblClassInstructor.setItems(null);
        }
    }
}
