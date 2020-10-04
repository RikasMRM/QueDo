package com.example.quedo;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class exampleViewholder2 extends RecyclerView.ViewHolder {
    View mView;
    TextView DocuName, DocuAuth;

    public exampleViewholder2(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setDetails(final Context ctx, final String docuNmae, final String docuAuth) {

        DocuName = itemView.findViewById(R.id.docuNameEx);
        DocuAuth = itemView.findViewById(R.id.docuAuthEx);

        DocuName.setText(docuNmae);
        DocuAuth.setText(docuAuth);
    }
}
