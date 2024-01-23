package com.example.schedulerapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.classListAdapter;
import com.example.schedulerapp.R;
import com.example.schedulerapp.classObject;
import com.example.schedulerapp.databinding.FragmentProfileBinding;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ArrayList<classObject> classArrayList;
    private String[] className;
    private String[] professorName;
    private String[] classStartTimes;
    private String[] classEndTimes;
    private RecyclerView recyclerview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.profileText;
        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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

        recyclerview = view.findViewById(R.id.profileRecyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        classListAdapter myAdapter = new classListAdapter(getContext(), classArrayList);
        recyclerview.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {
        classArrayList = new ArrayList<>();

        className = new String[] {
                getString(R.string.class_1),
                getString(R.string.class_2)
        };

        professorName = new String[] {
                getString(R.string.prof_1),
                getString(R.string.prof_2)
        };


        classStartTimes = new String[] {
                getString(R.string.classStart_1),
                getString(R.string.classStart_2)
        };

        classEndTimes = new String[] {
                getString(R.string.classEnd_1),
                getString(R.string.classEnd_2)
        };

        for(int i = 0; i < className.length; i++) {
            classObject classSelected = new classObject(className[i], classStartTimes[i], classEndTimes[i], professorName[i]);
            classArrayList.add(classSelected);
        }
    }
}