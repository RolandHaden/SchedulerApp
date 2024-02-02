package com.example.schedulerapp;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.schedulerapp.databinding.ActivityMainBinding;
import com.example.schedulerapp.ui.calendar.CalendarFragment;
import com.example.schedulerapp.ui.calendar.CalendarViewModel;
import com.example.schedulerapp.ui.calendar.eventObject;
import com.example.schedulerapp.ui.checklist.ChecklistFragment;
import com.example.schedulerapp.ui.checklist.ChecklistItem;
import com.example.schedulerapp.ui.checklist.ChecklistViewModel;
import com.example.schedulerapp.ui.profile.ProfileFragment;
import com.example.schedulerapp.ui.profile.ProfileViewModel;
import com.example.schedulerapp.ui.profile.classObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private static CalendarFragment calFrag = new CalendarFragment();
    private static ProfileFragment profFrag = new ProfileFragment();
    private static ChecklistFragment checkFrag = new ChecklistFragment();

    public static CalendarFragment getCalFrag() {
        return calFrag;
    }

    public static ProfileFragment getProfFrag() {
        return profFrag;
    }

    public static ChecklistFragment getCheckFrag() {
        return checkFrag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Restore data from SharedPreferences for CalendarViewModel
        loadCalendarDataFromSharedPreferences();

        // Restore data from SharedPreferences for ChecklistViewModel
        loadChecklistDataFromSharedPreferences();

        // Restore data from SharedPreferences for ProfileViewModel
        loadClassDataFromSharedPreferences();

        replaceFragment(new CalendarFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_calendar) {
                replaceFragment(calFrag);
            } else if (itemId == R.id.nav_profile) {
                replaceFragment(profFrag);
            } else if (itemId == R.id.nav_checklist) {
                replaceFragment(checkFrag);
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);  // Add to back stack
        fragmentTransaction.commit();
    }

    //Saving data
    @Override
    protected void onPause() {
        super.onPause();
        // Save data to SharedPreferences for CalendarViewModel
        saveCalendarDataToSharedPreferences();

        // Save data to SharedPreferences for ChecklistViewModel
        saveChecklistDataToSharedPreferences();

        // Save data to SharedPreferences for ProfileViewModel
        saveClassDataToSharedPreferences();
    }
    //Loading Calendar Data
    private void loadCalendarDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String eventsJson = sharedPreferences.getString("eventArrayList", "");

        Type listType = new TypeToken<ArrayList<eventObject>>() {}.getType();
        ArrayList<eventObject> loadedEventArrayList = new Gson().fromJson(eventsJson, listType);

        if (loadedEventArrayList != null) {
            CalendarViewModel.getEventArrayList().clear();
            CalendarViewModel.getEventArrayList().addAll(loadedEventArrayList);
        }

    }
    //Saving Calendar .
    private void saveCalendarDataToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String eventsJson = new Gson().toJson(CalendarViewModel.getEventArrayList());
        editor.putString("eventArrayList", eventsJson);
        editor.apply();
    }
    //Loading Data from Checklist
    private void loadChecklistDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String tasksJson = sharedPreferences.getString("taskArrayList", "");

        Type listType = new TypeToken<ArrayList<ChecklistItem>>() {}.getType();
        ArrayList<ChecklistItem> loadedTaskArrayList = new Gson().fromJson(tasksJson, listType);

        if (loadedTaskArrayList != null) {
            ChecklistViewModel.getTaskArrayList().clear();
            ChecklistViewModel.getTaskArrayList().addAll(loadedTaskArrayList);
        }
    }
    //Saving Data from Checklist
    private void saveChecklistDataToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String tasksJson = new Gson().toJson(ChecklistViewModel.getTaskArrayList());
        editor.putString("taskArrayList", tasksJson);
        editor.apply();
    }
    //Load Data from Profile
    private void loadClassDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String classJson = sharedPreferences.getString("classArrayList", "");

        Type listType = new TypeToken<ArrayList<classObject>>() {}.getType();
        ArrayList<classObject> loadedClassArrayList = new Gson().fromJson(classJson, listType);

        if (loadedClassArrayList != null) {
            ProfileViewModel.getClassArrayList().clear();
            ProfileViewModel.getClassArrayList().addAll(loadedClassArrayList);
        }
    }
    //Saving Data from Profile
    private void saveClassDataToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String classJson = new Gson().toJson(ProfileViewModel.getClassArrayList());
        editor.putString("classArrayList", classJson);
        editor.apply();
    }
}