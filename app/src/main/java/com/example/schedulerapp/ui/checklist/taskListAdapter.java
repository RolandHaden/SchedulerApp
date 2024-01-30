package com.example.schedulerapp.ui.checklist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.R;
import com.example.schedulerapp.RecyclerRowMoveCallback;
import com.example.schedulerapp.ui.calendar.CalendarViewModel;

import java.util.ArrayList;
import java.util.Collections;

/*
 * The taskListAdapter class is an adapter for a RecyclerView that displays a list of tasks.
 * It extends the RecyclerView.Adapter class and implements the
 * RecyclerRowMoveCallback.RecyclerViewRowTouchHelperContract interface to handle item
 * drag-and-drop actions within the RecyclerView.
 *
 * This adapter manages an ArrayList of String objects, where each String represents an individual task.
 * The adapter is responsible for creating ViewHolder instances for each item in the list and binding
 * the data from the task list to the views in the ViewHolder.
 *
 * The MyViewHolder inner class defines the UI components of each RecyclerView item, which includes
 * a CardView for the task, a TextView for the task title, and a CheckBox to mark the task as completed.
 * The adapter also includes logic to handle the checking off of tasks, which removes the task from the
 * list and updates the RecyclerView accordingly.
 */
public class taskListAdapter extends RecyclerView.Adapter<taskListAdapter.MyViewHolder>
            implements RecyclerRowMoveCallback.RecyclerViewRowTouchHelperContract {

    private final Context context;
    private final ArrayList<ChecklistItem> taskObjectArrayList;


    public taskListAdapter(Context context, ArrayList<ChecklistItem> taskObjectArrayList) {
        this.context = context;
        this.taskObjectArrayList = taskObjectArrayList;

    }

    @Override
    public int getItemCount() {
        return taskObjectArrayList.size();
    }

    @Override
    /*
     * Handles moving checklist items via drag and drop.
     */
    public void onRowMoved(int from, int to) {
        if (from < to) {
            for (int i=from; i<to; i++) {
                Collections.swap(taskObjectArrayList,i,i+1);
            }
        } else {
            for (int i=from; i>to; i--) {
                Collections.swap(taskObjectArrayList,i,i-1);
            }
        }

        notifyItemMoved(from,to);
    }

    @Override
    /*
     * Handles changing the color of the task card when drag and drop is initiated.
     * Purely visual feedback.
     */
    public void onRowSelected(MyViewHolder myViewHolder) {
        myViewHolder.taskCardView.setCardBackgroundColor(Color.GRAY);
    }

    @Override
    /*
     * Handles resetting the color when drag and drop ends on a card.
     */
    public void onRowClear(MyViewHolder myViewHolder) {
        myViewHolder.taskCardView.setCardBackgroundColor(Color.WHITE);
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
     * This method is used to update the data in an already created view that went off screen (for efficiency).
     * Basically it recycles the card views by just changing the contents.
     */
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChecklistItem specificTask = taskObjectArrayList.get(position);
        holder.taskTitle.setText(specificTask.getTaskTitle());
        holder.taskDescription.setText(specificTask.getTaskDescription());
        holder.taskDueDate.setText(specificTask.getTaskDueDate());
        holder.checkBox.setChecked(specificTask.isChecked());
    }

    /*
     * This inner class is a ViewHolder that describes an item view and metadata about its place within the RecyclerView.
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView taskCardView;
        TextView taskTitle;
        TextView taskDescription;
        TextView taskDueDate;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            taskCardView = itemView.findViewById(R.id.taskCardView);
            taskTitle = itemView.findViewById(R.id.taskTitleText);
            taskDescription = itemView.findViewById(R.id.taskDescriptionText);
            taskDueDate = itemView.findViewById(R.id.taskDueDateText);
            checkBox = itemView.findViewById(R.id.taskCheckBox);

            taskTitle.setSelected(true);

            /*
             * For completing & removing tasks.
             * This sets a listener that will be called when the checked state of the CheckBox changes.
             * In this listener, when a task is checked off, the code finds the index of the task in the
             * taskObjectArrayList, removes it, and notifies the adapter that the item has been removed
             * with notifyItemRemoved(index). This will result in the RecyclerView updating to reflect the item's removal.
             */
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                       ChecklistItem item = taskObjectArrayList.get(getAdapterPosition());

                       item.setChecked(isChecked);
                       if (isChecked) {
                           taskObjectArrayList.remove(getAdapterPosition());
                           CalendarViewModel.removeSpecificEvent(item.getID());
                           notifyItemRemoved(getAdapterPosition());
                       }
                   }
               }
            });
        }
    }
}
