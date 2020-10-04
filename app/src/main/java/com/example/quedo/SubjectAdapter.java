package com.example.quedo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SubjectAdapter extends FirebaseRecyclerAdapter<
        Subject, SubjectAdapter.subjectViewholder> {

    Subject subject = new Subject();

    public SubjectAdapter(
            @NonNull FirebaseRecyclerOptions<Subject> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final subjectViewholder holder,
                                    final int position, @NonNull final Subject model)
    {

        holder.time.setText(model.getSubTime());
        holder.date.setText(model.getSubDate());
        holder.hall.setText(model.getSubHall());
        holder.type.setText(model.getSubType());
        holder.name.setText(model.getSubName());

    }

    @NonNull
    @Override
    public subjectViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subjectcard, parent, false);
        return new subjectViewholder(view);
    }

    public static class subjectViewholder
            extends RecyclerView.ViewHolder {
        TextView time, date, hall, type, name;
        public subjectViewholder(@NonNull View itemView)
        {
            super(itemView);

           time = itemView.findViewById(R.id.time);
           date = itemView.findViewById(R.id.date);
           hall = itemView.findViewById(R.id.hall);
           type = itemView.findViewById(R.id.type);
           name = itemView.findViewById(R.id.subname);
        }
    }

}
