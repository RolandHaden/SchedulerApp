package com.example.schedulerapp.ui.checklist;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.util.LruCacheKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.schedulerapp.R;
import com.example.schedulerapp.databinding.FragmentEditTaskBinding;
import com.example.schedulerapp.databinding.FragmentNewTaskBinding;
import com.example.schedulerapp.ui.calendar.CalendarFragment;
import com.example.schedulerapp.ui.calendar.CalendarViewModel;
import com.example.schedulerapp.ui.calendar.eventObject;
import com.example.schedulerapp.ui.profile.EditClassFragment;
import com.example.schedulerapp.ui.profile.classObject;

import java.util.Calendar;
import java.util.UUID;

public class EditTaskFragment extends Fragment {

    private static ChecklistItem selectedTask;
    private static UUID selectedID;

    private FragmentEditTaskBinding binding;

    public EditTaskFragment() {
        // Required empty public constructor
    }
    public static EditTaskFragment newInstance(ChecklistItem selectedTask) {
        EditTaskFragment.selectedTask = selectedTask;
        selectedID = selectedTask.getID();
        return new EditTaskFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button addButton = (Button) root.findViewById(R.id.addTaskButton);
        EditText eDate = (EditText) root.findViewById(R.id.taskDueDateInput);

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

                mDatePicker.setMessage("EDIT DUE DATE");
                mDatePicker.show();

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            final EditText taskTitleField = (EditText) root.findViewById(R.id.taskTitleInput);
            final EditText taskDescriptionField = (EditText) root.findViewById(R.id.taskDescriptionInput);
            final EditText taskDueDate = (EditText) root.findViewById(R.id.taskDueDateInput);

            @Override
            public void onClick(View view) {
                for(ChecklistItem obj : ChecklistViewModel.getTaskArrayList()) {
                    if (obj.getID().equals(selectedID)) {
                        obj.setTaskTitle(String.valueOf(taskTitleField.getText()));
                        obj.setTaskDescription(String.valueOf(taskDescriptionField.getText()));
                        obj.setTaskDueDate(String.valueOf(taskDueDate.getText()));
                    }
                }
                for(eventObject obj : CalendarViewModel.getEventArrayList()) {
                    if (obj.getID().equals(selectedID)) {
                        obj.setClassName(String.valueOf(taskTitleField.getText()));
                        obj.setLocation(String.valueOf(taskDescriptionField.getText()));
                        obj.setSelectedTime(String.valueOf(taskDueDate.getText()));
                        obj.setSelectedDate(String.valueOf(taskDueDate.getText()));
                    }
                }
                replaceFragment(new ChecklistFragment());
            }
        });
        importData(selectedTask);
        return root;
    }

    private void importData(ChecklistItem taskData) {
        try{
        binding.taskTitleInput.setText(taskData.getTaskTitle().toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try{
            binding.taskDescriptionInput.setText(taskData.getTaskDescription().toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try{
            binding.taskDueDateInput.setText(taskData.getTaskDueDate().toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        selectedID = taskData.getID();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}