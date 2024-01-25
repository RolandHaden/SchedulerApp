package com.example.schedulerapp.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.R;
import com.example.schedulerapp.databinding.FragmentCalendarBinding;

import java.util.ArrayList;

public class CalendarFragment extends Fragment {

    private FragmentCalendarBinding binding;
    private static ArrayList<eventObject> eventArrayList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        RecyclerView recyclerView = view.findViewById(R.id.eventRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        eventListAdapter myAdapter = new eventListAdapter(getContext(), eventArrayList);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        binding.addButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new NewEventFragment());
            }
        });
    }
    private void dataInitialize() {
            eventArrayList = new ArrayList<>();
            eventObject[] eventArray = new eventObject[]{
                    new eventObject("1/12/2024","Class","Skiles 112", "CS 1331", "3:00 PM"),
                    new eventObject("1/12/2024","Class","Skiles 112", "CS 1331", "3:00 PM"),
                    new eventObject("1/12/2024","Class","Skiles 112", "CS 1331", "3:00 PM"),
                    new eventObject("1/12/2024","Class","Skiles 112", "CS 1331", "3:00 PM"),
            };

            for (int i = 0; i < eventArray.length; i++) {
                eventObject selectedEventObject = eventArray[i];
                eventArrayList.add(selectedEventObject);
            }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}