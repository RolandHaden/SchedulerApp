package com.example.schedulerapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.schedulerapp.databinding.ActivityMainBinding;
import com.example.schedulerapp.ui.calendar.CalendarFragment;
import com.example.schedulerapp.ui.calendar.CalendarViewModel;
import com.example.schedulerapp.ui.calendar.eventObject;
import com.example.schedulerapp.ui.checklist.ChecklistFragment;
import com.example.schedulerapp.ui.profile.ProfileFragment;
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
        loadDataFromSharedPreferences();
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
        saveDataToSharedPreferences();
    }

    private void saveDataToSharedPreferences() {
        // Get SharedPreferences instance
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Get the editor to write data
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Convert your ArrayList to JSON using Gson library (add Gson dependency if not added)
        String eventsJson = new Gson().toJson(CalendarViewModel.getEventArrayList());

        // Save the JSON string to SharedPreferences
        editor.putString("eventArrayList", eventsJson);
        editor.apply();
    }
    private void loadDataFromSharedPreferences() {
        // Get SharedPreferences instance
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Retrieve the JSON string from SharedPreferences
        String eventsJson = sharedPreferences.getString("eventArrayList", "");

        // Convert JSON string to ArrayList using Gson
        Type listType = new TypeToken<ArrayList<eventObject>>() {}.getType();
        ArrayList<eventObject> loadedEventArrayList = new Gson().fromJson(eventsJson, listType);

        // Check if data exists and update your ViewModel
        if (loadedEventArrayList != null) {
            CalendarViewModel.getEventArrayList().clear();
            CalendarViewModel.getEventArrayList().addAll(loadedEventArrayList);
        }
    }
}