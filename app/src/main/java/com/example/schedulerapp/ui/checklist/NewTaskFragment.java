package com.example.schedulerapp.ui.checklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.schedulerapp.MainActivity;
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
        addButton.setOnClickListener(new View.OnClickListener() {
            EditText textBox = (EditText) root.findViewById(R.id.task_input);
            @Override
            public void onClick(View view) {
                ChecklistFragment.addToTaskArrayList(textBox.getText().toString());
                replaceFragment(MainActivity.getCheckFrag());
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