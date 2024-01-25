package com.example.schedulerapp.ui.checklist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ChecklistViewModel extends ViewModel {
    private ArrayList<String> taskArrayList = new ArrayList<>();

    public ArrayList<String> getTaskArrayList() {
        return taskArrayList;
    }

    public void addToTaskArrayList(String str) {
        taskArrayList.add(str);
    }
}
