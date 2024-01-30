package com.example.schedulerapp.ui.checklist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.schedulerapp.ui.calendar.CalendarFragment;
import com.example.schedulerapp.ui.calendar.CalendarViewModel;
import com.example.schedulerapp.ui.calendar.eventObject;

import java.util.ArrayList;
import java.util.UUID;

public class ChecklistViewModel extends ViewModel {
    private static final ArrayList<ChecklistItem> taskArrayList = new ArrayList<>();

    public static ArrayList<ChecklistItem> getTaskArrayList() {
        return taskArrayList;
    }

    public static void addToTaskArrayList(String taskTitle, String taskDescription, String taskDueDate, Boolean isChecked, UUID id) {
        ChecklistItem newItem = new ChecklistItem(taskTitle, taskDescription, taskDueDate, isChecked, id);
        taskArrayList.add(newItem);
        eventObject newEvent = new eventObject(newItem.getTaskDueDate(), "Task", newItem.getTaskDescription(), newItem.getTaskTitle(), newItem.isChecked() ? "Completed" : "Incomplete");
        newEvent.setId(newItem.getID());
        CalendarViewModel.addToEventArrayList(newEvent);
    }
}
