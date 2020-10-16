package com.example.quedo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.algolia.search.saas.Client;
import com.algolia.search.saas.Index;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.stream.Stream;

import static android.content.ContentValues.TAG;
import static androidx.core.content.ContextCompat.getSystemService;

public class BooksFragment extends Fragment {

    private RecyclerView mRecyclerView;
    ExampleAdapter exampleAdapter;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    FirebaseRecyclerAdapter<Document, exampleViewholder2> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Document> options;

    SearchView searchView;
    ProgressBar progressBar;
    TextView sort;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_books, container, false);
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference().child("Books");

        Query query = myRef.orderByChild("docuName");

        mRecyclerView = rootView.findViewById(R.id.bookRecycle);
        searchView = rootView.findViewById(R.id.findBooks);
        progressBar = rootView.findViewById(R.id.progressBar);
        sort = rootView.findViewById(R.id.sortBy);

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this.getActivity()));

        FirebaseRecyclerOptions<Document> options =
                new FirebaseRecyclerOptions.Builder<Document>()
                        .setQuery(query, Document.class)
                        .build();

        exampleAdapter = new ExampleAdapter(options);

        mRecyclerView.setAdapter(exampleAdapter);

        final View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.example_item, container, false);

        final ExampleAdapter.exampleViewholder viewHolder = new ExampleAdapter.exampleViewholder(itemView);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(BooksFragment.this.getActivity(), "Long press to download", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        String key = exampleAdapter.getItem(position).getDocuLink();
                        Toast.makeText(BooksFragment.this.getActivity(), "Your file is downloading ..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(key));
                        startActivity(intent);
                    }
                })
        );

        if(searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    firebaseSearch(s);
                    return true;
                }
            });
        }
        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        exampleAdapter.startListening();
    }

    private void firebaseSearch(String searchText) {
        progressBar.setVisibility(View.VISIBLE);
        sort.setVisibility(View.GONE);

        String query = searchText;

        Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference().child("Books").orderByChild("docuName")
                .startAt(query).endAt(query + "\uf8ff");


        options = new FirebaseRecyclerOptions.Builder<Document>().setQuery(firebaseSearchQuery, Document.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Document, exampleViewholder2>(options) {
            @Override
            protected void onBindViewHolder(@NonNull exampleViewholder2 holder, int position, @NonNull Document document) {
                holder.setDetails(getContext(), document.getDocuName(), document.getDocuAuth());
                progressBar.setVisibility(View.GONE);
                sort.setVisibility(View.VISIBLE);
            }

            @NonNull
            @Override
            public exampleViewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);

                exampleViewholder2 viewHolder = new exampleViewholder2(itemView);

                return viewHolder;
            }
        };

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this.getContext()));
        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public void onStop()
    {
        super.onStop();
        exampleAdapter.stopListening();
    }

    @Override
    public void onResume() {

        super.onResume();
        this.onCreate(null);
    }
}
