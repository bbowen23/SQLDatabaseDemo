package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * Class instructor
 * @author Benjamin Bowen
 */
public class ClassInstructor
{
    private SimpleStringProperty classID;
    private SimpleStringProperty name;
    private SimpleStringProperty instructorID;
    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private SimpleStringProperty semester;
    private SimpleIntegerProperty year;
    private SimpleStringProperty room;
    private SimpleStringProperty startDate;
    private SimpleStringProperty startTime;
    private SimpleStringProperty endDate;
    private SimpleStringProperty endTime;
    private SimpleIntegerProperty maxStudents;

    public ClassInstructor() { }

    public ClassInstructor(String classID, String name, String instructorID, String fName, String lName, String semester,
                           int year, String room, String startDate, String startTime, String endDate, String endTime,
                           int maxStudents)
    {
        this.classID = new SimpleStringProperty(classID);
        this.name = new SimpleStringProperty(name);
        this.instructorID = new SimpleStringProperty(instructorID);
        this.fName = new SimpleStringProperty(fName);
        this.lName = new SimpleStringProperty(lName);
        this.semester = new SimpleStringProperty(semester);
        this.year = new SimpleIntegerProperty(year);
        this.room = new SimpleStringProperty(room);
        this.startDate = new SimpleStringProperty(startDate);
        this.startTime = new SimpleStringProperty(startTime);
        this.endDate = new SimpleStringProperty(endDate);
        this.endTime = new SimpleStringProperty(endTime);
        this.maxStudents = new SimpleIntegerProperty(maxStudents);
    }

    public String getFullName() {
        return this.fName + " " + this.lName;
    }
    public String getClassID() {
        return this.classID.get();
    }
    public String getName() {
        return this.name.get();
    }
    public String getInstructorID() {
        return this.instructorID.get();
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
    public int getMaxStudents() {
        return this.maxStudents.get();
    }

    public void setClassID(String classID) {
        System.out.println(classID);
        this.classID.set(classID);
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public void setInstructorID(String instructorID) {
        this.instructorID.set(instructorID);
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
    public void setMaxStudents(int maxStudents) {
        this.maxStudents.set(maxStudents);
    }
}
