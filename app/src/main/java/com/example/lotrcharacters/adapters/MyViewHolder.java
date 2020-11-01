package com.example.lotrcharacters.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lotrcharacters.Constants;
import com.example.lotrcharacters.R;
import com.example.lotrcharacters.models.Doc;
import com.example.lotrcharacters.ui.DetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class MyViewHolder extends RecyclerView.ViewHolder{
    //
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    //
    View mView;
    Context mContext;
    public ImageView image;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
//        itemView.setOnClickListener(this);
    }
        public void bindDoc (Doc doc) {
            image = mView.findViewById(R.id.charImageDrag);
            TextView nameView = mView.findViewById(R.id.charNameDrag);
            TextView raceView = mView.findViewById(R.id.charRaceDrag);
            TextView wikiView = mView.findViewById(R.id.charWikiDrag);

            nameView.setText(doc.getName());
            raceView.setText(doc.getRace());
            wikiView.setText(doc.getWikiUrl());
            Picasso.get().load(R.drawable.lotr_most).into(image);

    }


}
