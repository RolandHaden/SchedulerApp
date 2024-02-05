package com.example.schedulerapp.ui.profile;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.schedulerapp.R;
import com.example.schedulerapp.databinding.FragmentNewClassBinding;

import java.util.Calendar;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NewClassFragment extends Fragment {


    public NewClassFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private FragmentNewClassBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNewClassBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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


        addButton.setText("Add Class");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                ProfileViewModel.addToClassArrayList(new classObject(
                        eName.getText().toString(),
                        eEnd.getText().toString(),
                        eStart.getText().toString(),
                        weekdays,
                        eInstructor.getText().toString()
                        , UUID.randomUUID()
                ));

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
                        R.style.CustomTimePickerDialog,
                        (timePicker, selectedHour, selectedMinute) -> {
                            String selectedTime = String.format("%02d:%02d%s", selectedHour > 12 ? selectedHour - 12 : selectedHour, selectedMinute,selectedHour > 12 ? " PM" : " AM");
                            eStart.setText(selectedTime);
                        }, hour, minute, false);

                mTimePicker.setMessage("SELECT START TIME");
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
                        R.style.CustomTimePickerDialog,
                        (timePicker, selectedHour, selectedMinute) -> {
                            String selectedTime = String.format("%02d:%02d%s", selectedHour > 12 ? selectedHour - 12 : selectedHour, selectedMinute,selectedHour > 12 ? " PM" : " AM");
                            eEnd.setText(selectedTime);
                        }, hour, minute, false);

                mTimePicker.setMessage("SELECT END TIME");
                mTimePicker.show();
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