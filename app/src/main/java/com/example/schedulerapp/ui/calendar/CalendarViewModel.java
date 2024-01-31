package com.example.schedulerapp.ui.calendar;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.UUID;

public class CalendarViewModel extends ViewModel {
    private static final ArrayList<eventObject> eventArrayList = new ArrayList<>();
    private final MutableLiveData<String> mText;

    public CalendarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is calendar fragment");
    }
    public static void removeSpecificEvent(UUID id) {
        eventArrayList.removeIf(obj -> obj.getId().equals(id));

    }

    public LiveData<String> getText() {
        return mText;
    }

    public static ArrayList<eventObject> getEventArrayList() {
        return eventArrayList;
    }

    public static void addToEventArrayList(eventObject obj) {
        eventArrayList.add(obj);
    }


}