package com.example.schedulerapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.schedulerapp.R;
import com.example.schedulerapp.databinding.FragmentNewClassBinding;


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
        //Creating a calendar implementation for the date input for an event
        Button addButton = (Button) root.findViewById(R.id.addClassButton);
        //Spinner object
        // Create an ArrayAdapter using the string array and a default spinner layout.
        //Spinner Listener that updates the add button when selection is made
        //TODO implement the spinner listener to possibly change views too!
        addButton.setText("Add Class");

        return root;
    }
}