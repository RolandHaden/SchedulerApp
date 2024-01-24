package com.example.schedulerapp.ui.checklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.R;
import com.example.schedulerapp.databinding.FragmentChecklistBinding;
import com.example.schedulerapp.taskListAdapter;

import java.util.ArrayList;

public class ChecklistFragment extends Fragment {

    private FragmentChecklistBinding binding;
    private RecyclerView recyclerView;
    private ArrayList<String> taskArrayList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

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

        binding.addButtonTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new NewTaskFragment());
            }
        });

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


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}