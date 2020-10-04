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

import static android.content.ContentValues.TAG;
import static androidx.core.content.ContextCompat.getSystemService;

public class BooksFragment extends Fragment {

    private RecyclerView mRecyclerView;
    ExampleAdapter exampleAdapter;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    FirebaseRecyclerAdapter<Document, exampleViewholder2> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Document> options;

    String docName, docAuth, docLink, docID;
    ArrayList<ExampleItem> exampleList = new ArrayList<>();
    SearchView searchView;
    ProgressBar progressBar;
    public static final String TAG = "TAG";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_books, container, false);
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference().child("Documents");

        Query query = myRef.orderByChild("docuName");

        mRecyclerView = rootView.findViewById(R.id.bookRecycle);
        searchView = rootView.findViewById(R.id.findBooks);
        progressBar = rootView.findViewById(R.id.progressBar);

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
                        String key = exampleAdapter.getItem(position).getDocuLink();
                        Toast.makeText(BooksFragment.this.getActivity(), "Your file is downloading ..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(key));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new EditDocuFragment()).addToBackStack(null).commit();
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
//                    search(s);
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

//    private void search(final String str) {
//        progressBar.setVisibility(View.VISIBLE);
//        Toast.makeText(this.getActivity(), "Started Search " + str, Toast.LENGTH_LONG).show();
//
//
//        Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference().child("Documents")
//                .startAt(str)
//                .endAt(str + "\uf8ff");
//
//        FirebaseRecyclerOptions<Document> options =
//                new FirebaseRecyclerOptions.Builder<Document>()
//                        .setQuery(firebaseSearchQuery, Document.class)
//                        .setLifecycleOwner(this.getActivity())
//                        .build();
//
//        exampleAdapter = new ExampleAdapter(options);
//
//        mRecyclerView.setLayoutManager(
//                new LinearLayoutManager(this.getContext()));
//        mRecyclerView.setAdapter(exampleAdapter);
//    }

    private void firebaseSearch(String searchText) {
        progressBar.setVisibility(View.VISIBLE);

        String query = searchText;

        Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference().child("Documents").orderByChild("docuName")
                .startAt(query).endAt(query + "\uf8ff");


        options = new FirebaseRecyclerOptions.Builder<Document>().setQuery(firebaseSearchQuery, Document.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Document, exampleViewholder2>(options) {
            @Override
            protected void onBindViewHolder(@NonNull exampleViewholder2 holder, int position, @NonNull Document document) {
                holder.setDetails(getContext(), document.getDocuName(), document.getDocuAuth());
                progressBar.setVisibility(View.GONE);
            }

            @NonNull
            @Override
            public exampleViewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                //Inflating layout row.xml
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);

                exampleViewholder2 viewHolder = new exampleViewholder2(itemView);
//                //item click listener
//                viewHolder.setOnClickListener(new exampleViewholder2.ClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        //get data from firebase at the position clicked
//                        String mTitle = getItem(position).getTitle();
//                        String mDesc = getItem(position).getDescription();
//                        String mImagse = getItem(position).getImage();
//
//                        //pass this data to new activity
//                        Intent intent = new Intent(view.getContext(), PostDetailActivity.class);
//                        intent.putExtra("title", mTitle); // put title
//                        intent.putExtra("description", mDesc); //put description
//                        intent.putExtra("image", mImage); //put image url
//                        startActivity(intent); //start activity
//                    }
//
//                    @Override
//                    public void onItemLongClick(View view, int position) {
//                        //Todo implement you on long click functionality here
//                    }
//                });

                return viewHolder;
            }
        };

        //set layout as LinearLayout
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this.getContext()));
//        mRecyclerView.setLayoutManager(mLayoutManager);
        firebaseRecyclerAdapter.startListening();
        //set adapter to firebase recycler view
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
