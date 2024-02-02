package com.example.schedulerapp.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.ArrayList;
import java.util.UUID;

public class ProfileViewModel extends ViewModel {

    private static final ArrayList<classObject> classArrayList = new ArrayList<>();
    private final MutableLiveData<String> mText;

    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is profile fragment");
    }

    public static void removeSpecificClass(UUID id) {
//        classArrayList.removeIf(obj -> obj.getId().equals(id));
    }

    public LiveData<String> getText() {
        return mText;
    }

    public static ArrayList<classObject> getClassArrayList() {
        return classArrayList;
    }

    public static void addToClassArrayList(classObject obj) {
        classArrayList.add(obj);
    }
}