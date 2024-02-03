package com.example.schedulerapp.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.R;
import com.example.schedulerapp.databinding.FragmentCalendarBinding;
import com.example.schedulerapp.ui.checklist.ChecklistViewModel;
import com.example.schedulerapp.ui.profile.ProfileViewModel;
import com.example.schedulerapp.ui.profile.classListAdapter;
import com.example.schedulerapp.ui.profile.classObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public class CalendarFragment extends Fragment {

    private FragmentCalendarBinding binding;
    private static ArrayList<eventObject> eventArrayList;
    private static ArrayList<classObject> classArrayList;
    private static final ArrayList<classObject> storedClassArrayList = new ArrayList<>();

    private static final ArrayList<eventObject> storedEventArrayList = new ArrayList<>();
    private static eventListAdapter myAdapter;
    private static classListAdapter myClassAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public static void removeSpecificStoredEvent(UUID id) {
        eventArrayList.removeIf(obj -> obj.getID().equals(id));
        storedEventArrayList.removeIf(obj -> obj.getID().equals(id));
        ChecklistViewModel.removeSpecificTask(id);
        classArrayList.removeIf(obj -> obj.getID().equals(id));
        storedClassArrayList.removeIf(obj -> obj.getID().equals(id));
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CalendarViewModel calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);
        dataInitialize();

        // Set up RecyclerView for displaying events
        RecyclerView recyclerView = view.findViewById(R.id.eventRecyclerView);
        //RecyclerView recyclerClassView = view.findViewById(R.id.classRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerClassView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        //recyclerClassView.setHasFixedSize(true);
        myAdapter = new eventListAdapter(getContext(), storedEventArrayList);
        myClassAdapter = new classListAdapter(getContext(), storedClassArrayList);
        recyclerView.setAdapter(myAdapter);
        //ecyclerClassView.setAdapter(myClassAdapter);
        myAdapter.notifyDataSetChanged();
        myClassAdapter.notifyDataSetChanged();

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
                myClassAdapter.notifyDataSetChanged();
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
        storedClassArrayList.clear();
        System.out.println(dateString);
        for(eventObject obj : eventArrayList) {
            if (obj.getSelectedDate().equals(dateString)) {
                storedEventArrayList.add(obj);
            }
        }
        for(classObject obj : classArrayList) {
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(Objects.requireNonNull(new SimpleDateFormat("MM/dd/yyyy").parse(dateString)));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            eventObject newClassEvent = new eventObject(dateString, "Class", obj.getProfessorName(), obj.getCourseName(), obj.getClassDays() + " | " + obj.getStartTime() + " - " + obj.getEndTime());
            newClassEvent.setIsClass(true);
            switch (dayOfWeek) {
                case 2: if (obj.getClassDays().contains("M")) {
                    //newClassEvent = new eventObject(dateString, "Class", obj.getProfessorName(), obj.getCourseName(), obj.getClassDays() + " | " + obj.getStartTime() + " - " + obj.getEndTime());
                    storedEventArrayList.add(newClassEvent);
                }
                    break;
                case 3: if (obj.getClassDays().contains("Tu")) {
                    //storedEventArrayList.add(new eventObject(dateString, "Class", obj.getProfessorName(), obj.getCourseName(), obj.getClassDays() + " | " + obj.getStartTime() + " - " + obj.getEndTime()));
                    storedEventArrayList.add(newClassEvent);
                }
                    break;
                case 4: if (obj.getClassDays().contains("W")) {
                    //storedEventArrayList.add(new eventObject(dateString, "Class", obj.getProfessorName(), obj.getCourseName(), obj.getClassDays() + " | " + obj.getStartTime() + " - " + obj.getEndTime()));
                    storedEventArrayList.add(newClassEvent);
                }
                    break;
                case 5: if (obj.getClassDays().contains("Th")) {
                    //storedEventArrayList.add(new eventObject(dateString, "Class", obj.getProfessorName(), obj.getCourseName(), obj.getClassDays() + " | " + obj.getStartTime() + " - " + obj.getEndTime()));
                    storedEventArrayList.add(newClassEvent);
                }
                    break;
                case 6: if (obj.getClassDays().contains("F")) {
                    //storedEventArrayList.add(new eventObject(dateString, "Class", obj.getProfessorName(), obj.getCourseName(), obj.getClassDays() + " | " + obj.getStartTime() + " - " + obj.getEndTime()));
                    storedEventArrayList.add(newClassEvent);
                }
                    break;
            }
        }
    }
    /**
     * Initializes data, populating eventArrayList with sample events if it is empty.
     */
    private void dataInitialize() {
            eventArrayList = CalendarViewModel.getEventArrayList();
            classArrayList = ProfileViewModel.getClassArrayList();
            if (eventArrayList.isEmpty()) {
                eventObject[] eventArray = new eventObject[]{
                        new eventObject("2/1/2024", "Class", "Skiles 112", "CS 1331", "3:00 PM"),
                        new eventObject("2/1/2024", "Class", "Boggs B24", "CS 2340", "6:00 PM"),
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