package com.example.schedulerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.schedulerapp.databinding.ActivityMainBinding;
import com.example.schedulerapp.ui.calendar.CalendarFragment;
import com.example.schedulerapp.ui.checklist.ChecklistFragment;
import com.example.schedulerapp.ui.profile.ProfileFragment;

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
}