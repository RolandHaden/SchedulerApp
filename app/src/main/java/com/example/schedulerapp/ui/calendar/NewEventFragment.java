package com.example.schedulerapp.ui.calendar;

import android.app.DatePickerDialog;
import android.os.Bundle;
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
import com.example.schedulerapp.databinding.FragmentNewEventBinding;
import com.example.schedulerapp.ui.profile.ProfileViewModel;
import com.example.schedulerapp.ui.profile.classObject;

import java.util.ArrayList;
import java.util.Calendar;

public class NewEventFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FragmentNewEventBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNewEventBinding.inflate(inflater, container, false);
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

                addButton.setText("ADD " + spinner.getSelectedItem().toString());
                name_location.setHint((spinner.getSelectedItem().equals("Exam")) ? "Exam Location" : "Assignment Title");
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                addButton.setBackgroundColor(ContextCompat.getColor(addButton.getContext(), R.color.ltgray));
                addButton.setText("ADD EVENT");
                name_location.setHint("Description");
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
                CalendarViewModel.addToEventArrayList(new eventObject(eDate.getText().toString(),spinner.getSelectedItem().toString(),name_location.getText().toString(),classSpinner.getSelectedItem().toString(),eTime.getText().toString()));
                replaceFragment(new CalendarFragment());
            }
        });

        return root;
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}