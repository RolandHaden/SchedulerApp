package com.example.schedulerapp.ui.calendar;
import java.util.UUID;

//This is for any event created. Subclasses include: Class, Exam, Assignment, & Other
//TODO Subclass implementation if needed. Also need to look at what object is best at storing time
public class eventObject{
    private String selectedDate;
    private String type;
    private String location;
    private String className;
    private String selectedTime;
    private final UUID id;

    public eventObject(String selectedDate, String type, String location, String className, String selectedTime) {
        this.selectedDate = selectedDate;
        this.type = type;
        this.location = location;
        this.className = className;
        this.selectedTime = selectedTime;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

//    public void setId(UUID id) {
//        this.id = id;
//    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(String selectedTime) {
        this.selectedTime = selectedTime;
    }
}
