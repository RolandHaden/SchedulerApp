package com.example.schedulerapp.ui.checklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.R;
import com.example.schedulerapp.RecyclerRowMoveCallback;
import com.example.schedulerapp.databinding.FragmentChecklistBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class ChecklistFragment extends Fragment {
    private static ChecklistViewModel checklistViewModel;
    private FragmentChecklistBinding binding;
    private RecyclerView recyclerView;
    private ArrayList<ChecklistItem> taskArrayList = ChecklistViewModel.getTaskArrayList();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checklistViewModel = new ViewModelProvider(this).get(ChecklistViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentChecklistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    public static void addToTaskArrayList(String taskTitle, String taskDescription, String taskDueDate, Boolean isChecked, UUID id) {
        checklistViewModel.addToTaskArrayList(taskTitle, taskDescription, taskDueDate, false, id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        recyclerView = view.findViewById(R.id.tasksRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        // creates the adapter for the recycler view that holds the tasks
        taskListAdapter myAdapter = new taskListAdapter(getContext(), taskArrayList);

        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        binding.addButtonTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new NewTaskFragment());
            }
        });

        ItemTouchHelper.Callback callback = new RecyclerRowMoveCallback(myAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void dataInitialize() {
        if (checklistViewModel.getTaskArrayList().isEmpty()) {
            addToTaskArrayList(
                    "Sample Task Title",
                    "Sample task description",
                    "01/01/2024",
                    false,
                    UUID.randomUUID()
            );
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}