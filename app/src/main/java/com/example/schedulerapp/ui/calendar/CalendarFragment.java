package com.example.schedulerapp.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.R;
import com.example.schedulerapp.databinding.FragmentCalendarBinding;
import com.example.schedulerapp.ui.checklist.ChecklistItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;

public class CalendarFragment extends Fragment {

    private FragmentCalendarBinding binding;
    private static ArrayList<eventObject> eventArrayList;
    private static final ArrayList<eventObject> storedEventArrayList = new ArrayList<>();
    private static eventListAdapter myAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalendarBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public static void removeSpecificStoredEvent(UUID id) {
        eventArrayList.removeIf(obj -> obj.getId().equals(id));
        storedEventArrayList.removeIf(obj -> obj.getId().equals(id));
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CalendarViewModel calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);
        dataInitialize();

        // Set up RecyclerView for displaying events
        RecyclerView recyclerView = view.findViewById(R.id.eventRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        myAdapter = new eventListAdapter(getContext(), storedEventArrayList);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        // Set up button click listener to navigate to NewEventFragment
        binding.addButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new NewEventFragment());
            }
        });

        // Set up CalendarView listener to filter events based on selected date
        CalendarView calView = (CalendarView) view.findViewById(R.id.simple_calendar_view);
        sortArraytoDate((Calendar.getInstance().get(Calendar.MONTH) + 1) + "/" +
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" +
                Calendar.getInstance().get(Calendar.YEAR));
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //+1 is added to the month b/c CalendarView starts Jan with a 0.
                sortArraytoDate((month + 1) + "/" + dayOfMonth + "/" + year);
                myAdapter.notifyDataSetChanged();
            }
        });
    }
    /**
     * Filters events based on the selected date and updates the displayed array.
     *
     * @param dateString The selected date in "MM/dd/yyyy" format.
     */
    private void sortArraytoDate(String dateString) {
        storedEventArrayList.clear();
        System.out.println(dateString);
        for(eventObject obj : eventArrayList) {
            if (obj.getSelectedDate().equals(dateString)) {
                storedEventArrayList.add(obj);
            }
        }
    }
    /**
     * Initializes data, populating eventArrayList with sample events if it is empty.
     */
    private void dataInitialize() {
            eventArrayList = CalendarViewModel.getEventArrayList();
            if (eventArrayList.isEmpty()) {
                eventObject[] eventArray = new eventObject[]{
                        new eventObject("1/29/2024", "Class", "Skiles 112", "CS 1331", "3:00 PM"),
                        new eventObject("1/12/2024", "Class", "Skiles 112", "CS 1331", "3:00 PM"),
                };
                eventArrayList.addAll(Arrays.asList(eventArray));
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