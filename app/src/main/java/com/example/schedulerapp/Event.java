package com.example.schedulerapp;
import java.time.LocalTime;
import java.util.Calendar;
//This is for any event created. Subclasses include: Class, Exam, Assignment, & Other
//TODO Subclass implementation if needed. Also need to look at what object is best at storing time
public class Event {
    private Calendar selectedDate;
    private String name;
    private LocalTime selectedTime;

    public Event(Calendar selectedDate, String name, LocalTime selectedTime) {
        this.selectedDate = selectedDate;
        this.name = name;
        this.selectedTime = selectedTime;
    }

    public Calendar getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Calendar selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(LocalTime selectedTime) {
        this.selectedTime = selectedTime;
    }

}
