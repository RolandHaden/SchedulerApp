package com.example.schedulerapp.ui.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class eventListAdapter extends RecyclerView.Adapter<eventListAdapter.MyViewHolder> {
    Context context;
    private final ArrayList<eventObject> eventObjectArrayList;
    public eventListAdapter(Context context, ArrayList<eventObject> eventObjectArrayList) {
        this.context = context;
        this.eventObjectArrayList = eventObjectArrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.event_layout,parent,false);

        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        eventObject eventSelected = eventObjectArrayList.get(position);
        holder.selectedDate.setText(eventSelected.getLocation() + " - " + eventSelected.getSelectedTime());
        holder.type.setText(eventSelected.getType());
        holder.className.setText(eventSelected.getClassName());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //removing all instances
                    CalendarFragment.removeSpecificStoredEvent(eventSelected.getId());
                    //CalendarViewModel.removeSpecificEvent(eventSelected.getId());
                    notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventObjectArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView selectedDate;
        TextView type;
        TextView className;
        FloatingActionButton deleteButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            selectedDate = itemView.findViewById(R.id.eventTime);
            type = itemView.findViewById(R.id.eventType);
            className = itemView.findViewById(R.id.classEvent);
            deleteButton = itemView.findViewById(R.id.classDeleteButton);
//            deleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
//                        eventObject item = CalendarViewModel.getEventArrayList().get(getAdapterPosition());
//                        System.out.println("View Model Array: " + CalendarViewModel.getEventArrayList());
//                        System.out.println("View Model Array Size: " + CalendarViewModel.getEventArrayList().size());
//                        System.out.println("UUID Looking for: " + item.getId());
//                        CalendarViewModel.removeSpecificEvent(item.getId());
//
//                        for(eventObject eO : CalendarViewModel.getEventArrayList()) {
//                            System.out.println(eO.getId());
//                        }
//                        //CalendarFragment.removeSpecificStoredEvent(item.getId());
//                        //eventObjectArrayList.removeIf(obj -> obj.getId().equals(item.getId()));
//                        notifyItemRemoved(getAdapterPosition());
//                    }
//
//                }
//            });

        }

    }
}
