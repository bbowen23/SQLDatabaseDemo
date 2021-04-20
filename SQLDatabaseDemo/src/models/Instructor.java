package models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Instructor class
 * @author Benjamin Bowen
 */
public class Instructor
{
    private SimpleStringProperty instructorID;
    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private SimpleStringProperty email;
    private SimpleStringProperty officeNumber;
    private SimpleStringProperty phone;

    public Instructor() { }

    public Instructor(String instructorID, String fName, String lName, String email, String officeNumber, String phone)
    {
        this.instructorID = new SimpleStringProperty(instructorID);
        this.fName = new SimpleStringProperty(fName);
        this.lName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
        this.officeNumber = new SimpleStringProperty(officeNumber);
        this.phone = new SimpleStringProperty(phone);
    }

    public String getInstructorID() { return instructorID.get(); }
    public String getFirstName() { return fName.get(); }
    public String getLastName() { return lName.get(); }
    public String getEmail() { return email.get(); }
    public String getOffice() { return officeNumber.get(); }
    public String getPhone() { return phone.get(); }

    public void setInstructorID(String instructorID) { this.instructorID.set(instructorID); }
    public void setFirstName(String firstName) { this.fName.set(firstName); }
    public void setLastName(String lastName) { this.lName.set(lastName); }
    public void setEmail(String email) { this.email.set(email); }
    public void setOffice(String officeNumber) { this.officeNumber.set(officeNumber); }
    public void setPhone(String phone) { this.phone.set(phone); }
}
