package com.example.schedulerapp.ui.calendar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.schedulerapp.R;
import com.example.schedulerapp.databinding.FragmentNewEventBinding;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NewEventFragment extends Fragment {

    public NewEventFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FragmentNewEventBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNewEventBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Creating a calendar implementation for the date input for an event
        EditText eDate = root.findViewById(R.id.editTextDate);
        Button addButton = (Button) root.findViewById(R.id.addEventButton);
        TextView name_location = (TextView) root.findViewById(R.id.name_location);
        EditText editNameLoc = (EditText) root.findViewById(R.id.name_input);

        //Spinner object
        Spinner spinner = (Spinner) root.findViewById(R.id.spinnerEvent);
        Spinner classSpinner = (Spinner) root.findViewById(R.id.spinnerClass);

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.events,
                android.R.layout.simple_spinner_item
        );



        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.classes,
                android.R.layout.simple_spinner_item
        );

        //Spinner Listener that updates the add button when selection is made
        //TODO implement the spinner listener to possibly change views too!
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                addButton.setText("ADD " + spinner.getSelectedItem().toString());
                name_location.setText((spinner.getSelectedItem().equals("Exam")) ? "Location" : "Name");
                editNameLoc.setText((spinner.getSelectedItem().equals("Exam")) ? "Location" : "Name");
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                addButton.setText("ADD EVENT");
                name_location.setText("Location");
                editNameLoc.setText("Location");
            }
        });


        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                        String selectedDate = (selectedMonth + 1) + "/" + selectedDay + "/" + selectedYear;
                        eDate.setText(selectedDate);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select event date");
                mDatePicker.show();

            }
        });
        return root;
    }
}