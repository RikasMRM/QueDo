package com.example.quedo;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

        import com.firebase.ui.database.FirebaseRecyclerOptions;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;


public class QuickNotes extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    DatabaseReference myRef;

    private RecyclerView recyclerView;
    noteAdapter
            adapter;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_notes);

        mbase
                = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycler1);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Note> options
                = new FirebaseRecyclerOptions.Builder<Note>()
                .setQuery(mbase, Note.class)
                .build();

        adapter = new noteAdapter(options);

        recyclerView.setAdapter(adapter);

        floatingActionButton = findViewById(R.id.floatingActionButtonid);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuickNotes.this, InputActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}

//public class QuickNotes extends Fragment {
//
//    private RecyclerView recyclerView;
//    noteAdapter adapter; // Create Object of the Adapter class
//    DatabaseReference mbase;
//
//    private FloatingActionButton addBtn;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View rootView = inflater.inflate(R.layout.activity_quick_notes, container, false);
//
//        addBtn = rootView.findViewById(R.id.floatingActionButtonid);
//
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new AddNewTask()).addToBackStack(null).commit();
//            }
//        });
//
//        mbase
//                = FirebaseDatabase.getInstance().getReference().child("QuickNote");
//
//        Query query = mbase.orderByChild("QuickNote");
//
//        recyclerView = rootView.findViewById(R.id.recycler1);
//
//        // To display the Recycler view linearly
//        recyclerView.setLayoutManager(
//                new LinearLayoutManager(this.getActivity()));
//
//        // It is a class provide by the FirebaseUI to make a
//        // query in the database to fetch appropriate data
//        FirebaseRecyclerOptions<Note> options
//                = new FirebaseRecyclerOptions.Builder<Note>()
//                .setQuery(query, Note.class)
//                .build();
//        // Connecting object of required Adapter class to
//        // the Adapter class itself
//        adapter = new noteAdapter(options);
//        // Connecting Adapter class with the Recycler view*/
//        recyclerView.setAdapter(adapter);
//
//
//        return rootView;
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        adapter.startListening();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        this.onCreate(null);
//    }
//}