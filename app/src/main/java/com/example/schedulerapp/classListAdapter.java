package com.example.schedulerapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class classListAdapter extends RecyclerView.Adapter<classListAdapter.MyViewHolder> {
    Context context;
    ArrayList<classObject> classObjectArrayList;
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
        holder.classTitle.setText(classSelected.courseName);
        holder.profName.setText(classSelected.professorName);
        holder.times.setText(classSelected.startTime + " - " + classSelected.endTime);
    }

    @Override
    public int getItemCount() {
        return classObjectArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView classTitle;
        TextView profName;
        TextView times;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            classTitle = itemView.findViewById(R.id.className);
            profName = itemView.findViewById(R.id.classDesc);
            times = itemView.findViewById(R.id.classTimes);
        }
    }
}
