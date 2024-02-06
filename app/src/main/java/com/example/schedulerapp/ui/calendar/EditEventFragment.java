package com.example.schedulerapp.ui.calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.schedulerapp.R;
import com.example.schedulerapp.databinding.FragmentEditEventBinding;
import com.example.schedulerapp.ui.profile.ProfileViewModel;
import com.example.schedulerapp.ui.profile.classObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class EditEventFragment extends Fragment {
    private static eventObject selectedEvent;

    private static UUID selectedID;

    public static EditEventFragment newInstance(eventObject selectedEvent) {
        EditEventFragment.selectedEvent = selectedEvent;
        return new EditEventFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FragmentEditEventBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentEditEventBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        EditText eDate = root.findViewById(R.id.editTextDate);
        Button addButton = (Button) root.findViewById(R.id.addEventButton);
        TextView name_location = (TextView) root.findViewById(R.id.name_input);
        EditText eTime = root.findViewById(R.id.editTextTime);
        ArrayList<classObject> classes = ProfileViewModel.getClassArrayList();
        int n = classes.size();
        String[] CourseNames = new String[n];
        for(int i = 0; i < n; i++) {
            CourseNames[i] = classes.get(i).getCourseName();
        }

        if (ProfileViewModel.getClassArrayList().size() == 0) {
            CourseNames = new String[]{"No Courses Added"};
        }

        //Spinner object
        Spinner spinner = (Spinner) root.findViewById(R.id.spinnerEvent);
        Spinner classSpinner = (Spinner) root.findViewById(R.id.spinnerClass);

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.events,
                android.R.layout.simple_spinner_item
        );

        ArrayAdapter<CharSequence> classAdapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                CourseNames
        );

        //Spinner Listener that updates the add button when selection is made
        //TODO implement the spinner listener to possibly change views too!
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) view).setTextColor(ContextCompat.getColor(spinner.getContext(), R.color.beige));
                ((TextView) view).setTextSize(18);
                ((TextView) view).setPadding(8, 0, 0, 0);

                addButton.setText("CONFIRM");
                name_location.setHint((spinner.getSelectedItem().equals("Exam")) ? "Exam Location" : "Assignment Title");
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                addButton.setBackgroundColor(ContextCompat.getColor(addButton.getContext(), R.color.ltgray));
            }
        });

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) view).setTextColor(ContextCompat.getColor(spinner.getContext(), R.color.beige));
                ((TextView) view).setTextSize(18);
                ((TextView) view).setPadding(8, 0, 0, 0);
            }

            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        classAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        classSpinner.setAdapter(classAdapter);



        //Listens for when the user clicks on the EditText view. Focusable is disabled
        //so this is how the user will interact w/ date editing.
        eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear=mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), R.style.CustomDatePickerDialog, (datepicker, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = (selectedMonth + 1) + "/" + selectedDay + "/" + selectedYear;
                    eDate.setText(selectedDate);
                },mYear, mMonth, mDay);

                mDatePicker.show();

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(eventObject obj : CalendarViewModel.getEventArrayList()) {
                    if (obj.getID().equals(selectedID)) {
                        obj.setSelectedDate(String.valueOf(eDate.getText()));
                        obj.setSelectedTime(String.valueOf(eTime.getText()));
                        obj.setType(spinner.getSelectedItem().toString());
                        obj.setLocation(String.valueOf(name_location.getText()));
                        obj.setClassName(classSpinner.getSelectedItem().toString());
                    }
                }
                replaceFragment(new CalendarFragment());
            }
        });
        eTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker = new TimePickerDialog(getContext(),
                        R.style.CustomTimePickerDialog,
                        (timePicker, selectedHour, selectedMinute) -> {
                            String selectedTime = String.format("%02d:%02d%s", selectedHour > 12 ? selectedHour - 12 : selectedHour, selectedMinute,selectedHour >= 12 ? " PM" : " AM");
                            eTime.setText(selectedTime);
                        }, hour, minute, false);

                mTimePicker.show();
            }
        });
        if (selectedEvent != null) {
            // Use the data to fill in the views or perform any other necessary tasks
            importData(selectedEvent);
        } else {
            Log.e("EditEventFragment", "Selected Event is null");
        }

        return root;
    }

    private void importData(eventObject eventData) {
        binding.editTextDate.setText(eventData.getSelectedDate());
        binding.editTextTime.setText(eventData.getSelectedTime());
        binding.nameInput.setText(eventData.getLocation());
        int eventPosition = ((ArrayAdapter<CharSequence>) binding.spinnerEvent.getAdapter()).getPosition(eventData.getType());
        binding.spinnerEvent.setSelection(eventPosition);
        int classPosition = ((ArrayAdapter<String>) binding.spinnerClass.getAdapter()).getPosition(eventData.getClassName());
        binding.spinnerClass.setSelection(classPosition);
        selectedID = eventData.getID();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}