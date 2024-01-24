package com.example.schedulerapp.ui.checklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.schedulerapp.R;
import com.example.schedulerapp.databinding.FragmentNewTaskBinding;
import com.example.schedulerapp.ui.calendar.NewEventFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewTaskFragment extends Fragment {


    public NewTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FragmentNewTaskBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNewTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Creating a calendar implementation for the date input for an event
        Button addButton = (Button) root.findViewById(R.id.addTaskButton);
        addButton.setText("Add Task");

        return root;
    }
}