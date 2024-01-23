package com.example.schedulerapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class taskListAdapter extends RecyclerView.Adapter<taskListAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> taskObjectArrayList;
    public taskListAdapter(Context context, ArrayList<String> taskObjectArrayList) {
        this.context = context;
        this.taskObjectArrayList = taskObjectArrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.task_layout,parent,false);

        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String taskSelected = taskObjectArrayList.get(position);
        holder.taskTitle.setText(taskSelected);
    }

    @Override
    public int getItemCount() {
        return taskObjectArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox taskTitle;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.taskCheckBox);
        }
    }
}
