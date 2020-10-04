package com.example.quedo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class taskAdapter extends FirebaseRecyclerAdapter<Task, taskAdapter.taskViewholder> {

    public taskAdapter(
            @NonNull FirebaseRecyclerOptions<Task> options)
    {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull taskViewholder holder, int position, @NonNull Task model)
    {

        holder.task.setText(model.getTask());

        holder.description.setText(model.getDescription());

        holder.date.setText(model.getDate());
    }

    @NonNull
    @Override
    public taskViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_retrivetask, parent, false);
        return new taskAdapter.taskViewholder(view);
    }

    class taskViewholder extends RecyclerView.ViewHolder {
        TextView task, description, date;
        public taskViewholder(@NonNull View itemView)
        {
            super(itemView);

            task = itemView.findViewById(R.id.task);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
        }
    }
}
