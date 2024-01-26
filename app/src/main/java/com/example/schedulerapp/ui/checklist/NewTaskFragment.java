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

import java.util.UUID;

public class NewTaskFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentNewTaskBinding binding = FragmentNewTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button addButton = (Button) root.findViewById(R.id.addTaskButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            final EditText taskTitleField = (EditText) root.findViewById(R.id.taskTitleInput);
            final EditText taskDescriptionField = (EditText) root.findViewById(R.id.taskDescriptionInput);
            final EditText taskDueDate = (EditText) root.findViewById(R.id.taskDueDateInput);

            @Override
            public void onClick(View view) {
                ChecklistViewModel.addToTaskArrayList(
                        taskTitleField.getText().toString(),
                        taskDescriptionField.getText().toString(),
                        taskDueDate.getText().toString(),
                        false,
                        UUID.randomUUID()
                );
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