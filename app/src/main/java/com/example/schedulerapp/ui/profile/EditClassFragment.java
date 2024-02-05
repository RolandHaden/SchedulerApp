package com.example.schedulerapp.ui.profile;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.schedulerapp.R;
import com.example.schedulerapp.databinding.FragmentEditClassBinding;
import com.example.schedulerapp.databinding.FragmentNewClassBinding;
import com.example.schedulerapp.ui.calendar.CalendarFragment;
import com.example.schedulerapp.ui.calendar.CalendarViewModel;
import com.example.schedulerapp.ui.calendar.EditEventFragment;
import com.example.schedulerapp.ui.calendar.eventObject;

import java.util.Calendar;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class EditClassFragment extends Fragment {

    private static classObject selectedClass;
    private static UUID selectedID;
    public EditClassFragment() {
        // Required empty public constructor
    }
    public static EditClassFragment newInstance(classObject selectedClass) {
        EditClassFragment.selectedClass = selectedClass;
        selectedID = selectedClass.getID();
        return new EditClassFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private FragmentEditClassBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentEditClassBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        importData(selectedClass);
        Button addButton = (Button) root.findViewById(R.id.addClassButton);
        EditText eName = root.findViewById(R.id.name_input);
        EditText eInstructor = root.findViewById(R.id.instructor_input);
        CheckBox eM = root.findViewById(R.id.monday_box);
        CheckBox eTu = root.findViewById(R.id.tuesday_box);
        CheckBox eW = root.findViewById(R.id.wednesday_box);
        CheckBox eTh = root.findViewById(R.id.thursday_box);
        CheckBox eF = root.findViewById(R.id.friday_box);
        EditText eStart = root.findViewById(R.id.editTextTimeStart);
        EditText eEnd = root.findViewById(R.id.editTextTimeEnd);


        addButton.setText("Confirm");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(classObject obj : ProfileViewModel.getClassArrayList()) {
                    if (obj.getID().equals(selectedID)) {
                        String weekdays = "";
                        if (eM.isChecked()) {
                            weekdays += "M ";
                        }
                        if (eTu.isChecked()) {
                            weekdays += "Tu ";
                        }
                        if (eW.isChecked()) {
                            weekdays += "W ";
                        }
                        if (eTh.isChecked()) {
                            weekdays += "Th ";
                        }
                        if (eF.isChecked()) {
                            weekdays += "F ";
                        }
                        obj.setCourseName(eName.getText().toString());
                        obj.setStartTime(eStart.getText().toString());
                        obj.setEndTime(eEnd.getText().toString());
                        obj.setProfessorName(eInstructor.getText().toString());
                        obj.setClassDays(weekdays);
                    }
                }
                replaceFragment(new ProfileFragment());
            }
        });

        eStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker = new TimePickerDialog(getContext(),
                        (timePicker, selectedHour, selectedMinute) -> {
                            String selectedTime = String.format("%02d:%02d%s", selectedHour > 12 ? selectedHour - 12 : selectedHour, selectedMinute,selectedHour > 12 ? " PM" : " AM");
                            eStart.setText(selectedTime);
                        }, hour, minute, false);

                mTimePicker.show();
            }
        });

        eEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker = new TimePickerDialog(getContext(),
                        (timePicker, selectedHour, selectedMinute) -> {
                            String selectedTime = String.format("%02d:%02d%s", selectedHour > 12 ? selectedHour - 12 : selectedHour, selectedMinute,selectedHour > 12 ? " PM" : " AM");
                            eEnd.setText(selectedTime);
                        }, hour, minute, false);

                mTimePicker.show();
            }
        });

        return root;
    }

    private void importData(classObject classData) {
        String daysOfWeek = classData.getClassDays();
        binding.editTextTimeEnd.setText(classData.getEndTime());
        binding.editTextTimeStart.setText(classData.getStartTime());
        binding.nameInput.setText(classData.getCourseName());
        binding.instructorInput.setText(classData.getProfessorName());
        if(daysOfWeek.contains("M")) {
            binding.mondayBox.setChecked(true);
        }
        if(daysOfWeek.contains("Tu")) {
            binding.tuesdayBox.setChecked(true);
        }
        if(daysOfWeek.contains("W")) {
            binding.wednesdayBox.setChecked(true);
        }
        if(daysOfWeek.contains("Th")) {
            binding.thursdayBox.setChecked(true);
        }
        if(daysOfWeek.contains("F")) {
            binding.fridayBox.setChecked(true);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}