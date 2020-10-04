package com.example.quedo;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.Objects;

public class ExampleAdapter extends FirebaseRecyclerAdapter<
        Document, ExampleAdapter.exampleViewholder> {

    String docType, docuID, childRes, docuLink, userID;
    Document doc = new Document();

    public ExampleAdapter(
            @NonNull FirebaseRecyclerOptions<Document> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void onBindViewHolder(@NonNull exampleViewholder holder,
                     int position, @NonNull Document model)
    {
        doc = model;
        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.DocuName.setText(model.getDocuName());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.DocuAuth.setText(model.getDocuAuth());

        docuLink = model.getDocuLink();
        docType = model.getDocType();
        childRes = model.getChildRes();
        userID = model.getUserID();

    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public exampleViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.example_item, parent, false);
        return new exampleViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    public static class exampleViewholder
            extends RecyclerView.ViewHolder {
        TextView DocuName, DocuAuth;
        public exampleViewholder(@NonNull View itemView)
        {
            super(itemView);

            DocuName = itemView.findViewById(R.id.docuNameEx);
            DocuAuth = itemView.findViewById(R.id.docuAuthEx);
        }
    }

}
