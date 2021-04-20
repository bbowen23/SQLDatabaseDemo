package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Class for courses
 * @author Benjamin Bowen
 */
public class Course
{
    private SimpleStringProperty classID;
    private SimpleStringProperty className;
    private SimpleStringProperty instructorID;
    private SimpleStringProperty instructorName;
    private SimpleStringProperty startTime;
    private SimpleStringProperty endTime;

    public Course() {}

    public Course(String classID, String className, String instructorID, String instructorName, String startTime, String endTime)
    {
        this.classID = new SimpleStringProperty(classID);
        this.className = new SimpleStringProperty(className);
        this.instructorID = new SimpleStringProperty(instructorID);
        this.instructorName = new SimpleStringProperty(instructorName);
        this.startTime = new SimpleStringProperty(startTime);
        this.endTime = new SimpleStringProperty(endTime);
    }

    public String getClassID() { return this.classID.get(); }
    public String getClassName() { return this.className.get(); }
    public String getInstructorID() { return this.instructorID.get(); }
    public String getInstructorName() { return this.instructorName.get(); }
    public String getStartTime() { return this.startTime.get(); }
    public String getEndTime() { return this.endTime.get(); }

    public void setClassID(String classID) { this.classID.set(classID); }
    public void setClassName(String className) { this.className.set(className); }
    public void setInstructorID(String instructorID) { this.instructorID.set(instructorID); }
    public void setInstructorName(String instructorName) { this.instructorName.set(instructorName); }
    public void setStartTime(String startTime) { this.startTime.set(startTime); }
    public void setEndTime(String endTime) { this.endTime.set(endTime); }
}
