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

public class TimetableeAdapter extends FirebaseRecyclerAdapter<
        Timetable, TimetableeAdapter.timetableViewholder> {

    String timetableName, userID;
    Timetable timetable = new Timetable();

    public TimetableeAdapter(
            @NonNull FirebaseRecyclerOptions<Timetable> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final timetableViewholder holder,
                                    final int position, @NonNull final Timetable model)
    {
        timetable = model;

        holder.timetableName.setText(model.getTimetableName());

        userID = model.getUserID();

    }

    @NonNull
    @Override
    public timetableViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.timetable_card, parent, false);
        return new timetableViewholder(view);
    }

    public static class timetableViewholder
            extends RecyclerView.ViewHolder {
        TextView timetableName;
        public timetableViewholder(@NonNull View itemView)
        {
            super(itemView);

            timetableName = itemView.findViewById(R.id.table_num);
        }
    }

}
