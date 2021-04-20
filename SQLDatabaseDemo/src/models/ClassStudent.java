package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Class instructor student
 * @author Benjamin Bowen
 */
public class ClassStudent
{
    private SimpleStringProperty classID;
    private SimpleStringProperty studentID;
    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private SimpleStringProperty semester;
    private SimpleIntegerProperty year;
    private SimpleStringProperty room;
    private SimpleStringProperty startDate;
    private SimpleStringProperty startTime;
    private SimpleStringProperty endDate;
    private SimpleStringProperty endTime;

    public ClassStudent() { }

    public ClassStudent(String classID, String semester, int year, String room, String startDate, String startTime,
                        String endDate, String endTime, String studentID, String fName, String lName)
    {
        this.classID = new SimpleStringProperty(classID);
        this.semester = new SimpleStringProperty(semester);
        this.year = new SimpleIntegerProperty(year);
        this.room = new SimpleStringProperty(room);
        this.startDate = new SimpleStringProperty(startDate);
        this.startTime = new SimpleStringProperty(startTime);
        this.endDate = new SimpleStringProperty(endDate);
        this.endTime = new SimpleStringProperty(endTime);
        this.studentID = new SimpleStringProperty(studentID);
        this.fName = new SimpleStringProperty(fName);
        this.lName = new SimpleStringProperty(lName);
    }

    public String getClassID() {
        return this.classID.get();
    }
    public String getStudentID() {
        return this.studentID.get();
    }
    public String getFname() {
        return this.fName.get();
    }
    public String getLname() {
        return this.lName.get();
    }
    public String getSemester() {
        return this.semester.get();
    }
    public int getYear() {
        return this.year.get();
    }
    public String getRoom() {
        return this.room.get();
    }
    public String getStartDate() {
        return this.startDate.get();
    }
    public String getStartTime() {
        return this.startTime.get();
    }
    public String getEndDate() {
        return this.startDate.get();
    }
    public String getEndTime() {
        return this.startTime.get();
    }

    public String getFullName() { return fName + " " + lName; }

    public void setClassID(String classID) {
        System.out.println(classID);
        this.classID.set(classID);
    }
    public void setStudentID(String instructorID) {
        this.studentID.set(instructorID);
    }
    public void setFname(String fName) {
        this.fName.set(fName);
    }
    public void setLname(String lName) {
        this.lName.set(lName);
    }
    public void setSemester(String semester) {
        this.semester.set(semester);
    }
    public void setYear(int year) {
        this.year.set(year);
    }
    public void setRoom(String room) {
        this.room.set(room);
    }
    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }
    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }
    public void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }
    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }
}
