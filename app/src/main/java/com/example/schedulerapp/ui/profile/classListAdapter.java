package com.example.schedulerapp.ui.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.R;
import com.example.schedulerapp.ui.calendar.CalendarViewModel;
import com.example.schedulerapp.ui.calendar.EditEventFragment;
import com.example.schedulerapp.ui.calendar.eventObject;
import com.example.schedulerapp.ui.checklist.ChecklistItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class classListAdapter extends RecyclerView.Adapter<classListAdapter.MyViewHolder> {
    Context context;
    static ArrayList<classObject> classObjectArrayList;
    public classListAdapter(Context context, ArrayList<classObject> classObjectArrayList) {
        this.context = context;
        this.classObjectArrayList = classObjectArrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.class_layout,parent,false);

        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        classObject classSelected = classObjectArrayList.get(position);
        holder.classTitle.setText(classSelected.getCourseName());
        holder.profName.setText(classSelected.getProfessorName());
        holder.times.setText(classSelected.getClassDays() + "| " + classSelected.getStartTime() + " - " + classSelected.getEndTime());
    }

    @Override
    public int getItemCount() {
        return classObjectArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView classTitle;
        TextView profName;
        TextView times;
        FloatingActionButton deleteButton;
        CardView classView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            classTitle = itemView.findViewById(R.id.eventType);
            profName = itemView.findViewById(R.id.classEvent);
            times = itemView.findViewById(R.id.classTimes);
            deleteButton = itemView.findViewById(R.id.classDeleteButton);
            classView = itemView.findViewById(R.id.classCard);
            classView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        classObject selectedClass = classObjectArrayList.get(position);
                            // Switching Views
                            EditClassFragment newFragment = EditClassFragment.newInstance(selectedClass);
                            FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.frame_layout, newFragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                    }
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        classObject item = classObjectArrayList.get(getAdapterPosition());
                        classObjectArrayList.remove(getAdapterPosition());
                        CalendarViewModel.removeSpecificEvent(item.getID());
                        notifyItemRemoved(getAdapterPosition());
                    }
                }
            });
        }
    }
}
