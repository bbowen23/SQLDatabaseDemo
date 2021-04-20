package models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Student class
 * @author Benjamin Bowen
 */
public class Student
{
    private SimpleStringProperty studentID;
    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private SimpleStringProperty major;
    private SimpleStringProperty year;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;

    public Student() { }

    public Student(String studentID, String fName, String lName, String major, String year, String email, String phone)
    {
        this.studentID = new SimpleStringProperty(studentID);
        this.fName = new SimpleStringProperty(fName);
        this.lName = new SimpleStringProperty(lName);
        this.major = new SimpleStringProperty(major);
        this.year = new SimpleStringProperty(year);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }

    public String getStudentID() { return this.studentID.get(); }
    public String getFirstName() { return this.fName.get(); }
    public String getLastName() { return this.lName.get(); }
    public String getMajor() { return this.major.get(); }
    public String getYear() { return this.year.get(); }
    public String getEmail() { return this.email.get(); }
    public String getPhone() { return this.phone.get(); }

    public void setStudentID(String studentID) { this.studentID.set(studentID); }
    public void setFirstName(String fName) { this.fName.set(fName); }
    public void setLastName(String lName) { this.lName.set(lName); }
    public void setMajor(String major) { this.major.set(major); }
    public void setYear(String year) { this.year.set(year); }
    public void setEmail(String email) { this.email.set(email); }
    public void setPhone(String phone) { this.phone.set(phone); }
}
