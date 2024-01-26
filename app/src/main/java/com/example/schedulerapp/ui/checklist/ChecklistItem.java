package com.example.schedulerapp.ui.checklist;

public class ChecklistItem {
    private String taskTitle;
    private String taskDescription;
    private String taskDueDate;
    private boolean isChecked;

    public ChecklistItem(String taskTitle, boolean isChecked) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskDueDate = taskDueDate;
        this.isChecked = isChecked;
    }

    public String getTaskTitle() {
        return taskTitle;
    }
    public void setTaskTitle(String newTitle) {
        this.taskTitle = newTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String newDescription) {
        this.taskDescription = newDescription;
    }

    public String getTaskDueDate() {
        return taskDueDate;
    }
    public void setTaskDueDate(String newDueDate) {
        this.taskDueDate = newDueDate;
    }

    public boolean isChecked() {
        return isChecked;
    }
    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
