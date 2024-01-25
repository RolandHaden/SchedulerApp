package com.example.schedulerapp.ui.checklist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.R;

import java.util.ArrayList;

public class taskListAdapter extends RecyclerView.Adapter<taskListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> taskObjectArrayList;
    public taskListAdapter(Context context, ArrayList<String> taskObjectArrayList) {
        this.context = context;
        this.taskObjectArrayList = taskObjectArrayList;

    }

    @Override
    public int getItemCount() {
        return taskObjectArrayList.size();
    }

    @NonNull
    @Override
    /*
     * This method is called when the RecyclerView needs a new ViewHolder of the given type to represent an item.
     * This is where you inflate the item layout (task_layout) and create the ViewHolder.
     * The ViewHolder will be used to display items of the adapter using onBindViewHolder.
     */
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.task_layout,parent,false);

        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    /*
     * This method is used to display the data at the specified position.
     * This method updates the contents of the ViewHolder.itemView to reflect the item at the given position.
     * In this case, it sets the text of a CheckBox to the corresponding string from the taskObjectArrayList.
     */
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String taskSelected = taskObjectArrayList.get(position);
        holder.checkboxObject.setText(taskSelected);
    }

    /*
     * This inner class is a ViewHolder that describes an item view and metadata about its place within the RecyclerView.
     * It holds the reference to the CheckBox which is part of the item layout and is used to display the task title.
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkboxObject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkboxObject = itemView.findViewById(R.id.taskCheckBox);

            /*
             * For completing & removing tasks.
             * This sets a listener that will be called when the checked state of the CheckBox changes.
             * In this listener, when a task is checked off, the code finds the index of the task in the
             * taskObjectArrayList, removes it, and notifies the adapter that the item has been removed
             * with notifyItemRemoved(index). This will result in the RecyclerView updating to reflect the item's removal.
             */
            checkboxObject.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                   int index = taskObjectArrayList.indexOf(checkboxObject.getText());
                   taskObjectArrayList.remove(checkboxObject.getText());
                   notifyItemRemoved(index); //Always use this
               }
            });
        }
    }
}
