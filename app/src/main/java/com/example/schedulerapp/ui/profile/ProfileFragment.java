package com.example.schedulerapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.R;
import com.example.schedulerapp.databinding.FragmentProfileBinding;

import java.util.ArrayList;
import java.util.UUID;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ArrayList<classObject> classArrayList;
    private String[] className;
    private String[] professorName;
    private String[] classStartTimes;
    private String[] classEndTimes;
    private String[] classDays;
    private RecyclerView recyclerview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dataInitialize();

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

        recyclerview = view.findViewById(R.id.profileRecyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        classListAdapter myAdapter = new classListAdapter(getContext(), classArrayList);
        recyclerview.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        binding.addButtonClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new NewClassFragment());
            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


    public void dataInitialize() {
        classArrayList = ProfileViewModel.getClassArrayList();
    }
}