package com.example.schedulerapp.ui.checklist;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.taskListAdapter;
import com.example.schedulerapp.R;
import com.example.schedulerapp.classObject;
import com.example.schedulerapp.databinding.FragmentChecklistBinding;

import java.util.ArrayList;

public class ChecklistFragment extends Fragment {

    private FragmentChecklistBinding binding;
    private RecyclerView recyclerView;
    private ArrayList<String> taskArrayList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChecklistViewModel checklistViewModel =
                new ViewModelProvider(this).get(ChecklistViewModel.class);

        binding = FragmentChecklistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.checklistText;
        //checklistViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
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
        taskListAdapter myAdapter = new taskListAdapter(getContext(), taskArrayList);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {
        taskArrayList = new ArrayList<>();

        String[] taskNames = new String[] {
            "Sample Task",
            "Do Homework",
                "Study for 2340"
        };

        for(int i = 0; i < taskNames.length; i++) {
            String taskSelected = taskNames[i];
            taskArrayList.add(taskSelected);
        }
    }
}