//package com.example.quedo;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//
//public class noteAdapter extends FirebaseRecyclerAdapter<
//        Note, noteAdapter.notesViewholder> {
//
//    public noteAdapter(
//            @NonNull FirebaseRecyclerOptions<Note> options)
//    {
//        super(options);
//    }
//
//    @Override
//    protected void
//    onBindViewHolder(@NonNull noteAdapter.notesViewholder holder,
//                     int position, @NonNull Note model)
//    {
//
//        holder.title.setText(model.getTitle());
//
//        holder.desc.setText(model.getDesc());
//
//    }
//
//    @NonNull
//    @Override
//    public notesViewholder
//    onCreateViewHolder(@NonNull ViewGroup parent,
//                       int viewType)
//    {
//        View view
//                = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.note, parent, false);
//        return new noteAdapter.notesViewholder(view);
//    }
//    class notesViewholder
//            extends RecyclerView.ViewHolder {
//        TextView title, desc;
//        public notesViewholder(@NonNull View itemView)
//        {
//            super(itemView);
//
//            title = itemView.findViewById(R.id.title);
//            desc = itemView.findViewById(R.id.Note);
//        }
//    }
//}

package com.example.quedo;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.firebase.ui.database.FirebaseRecyclerAdapter;
        import com.firebase.ui.database.FirebaseRecyclerOptions;

public class noteAdapter extends FirebaseRecyclerAdapter<
        Note, noteAdapter.noteViewholder> {

    public noteAdapter(
            @NonNull FirebaseRecyclerOptions<Note> options)
    {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull noteViewholder holder,
                     int position, @NonNull Note model)
    {

        holder.title.setText(model.getTitle());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.note.setText(model.getDesc());

    }

    @NonNull
    @Override
    public noteViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note, parent, false);
        return new noteAdapter.noteViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class noteViewholder
            extends RecyclerView.ViewHolder {
        TextView title, note;
        public noteViewholder(@NonNull View itemView)
        {
            super(itemView);

            title
                    = itemView.findViewById(R.id.title);
            note = itemView.findViewById(R.id.Note);
        }
    }
}
