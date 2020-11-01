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

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    View mView;
    Context mContext;
//    public TextView nameView;
//    public TextView raceView;
//    public TextView wikiView;
    public ImageView image;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
        public void bindDoc (Doc doc) {
            image = mView.findViewById(R.id.charImageDrag);
            TextView nameView = mView.findViewById(R.id.charNameDrag);
            TextView raceView = mView.findViewById(R.id.charRaceDrag);
            TextView wikiView = mView.findViewById(R.id.charWikiDrag);
//            image = itemView.findViewById(R.id.charImageDrag);
//        nameView = itemView.findViewById(R.id.charNameTextView);
//        raceView = itemView.findViewById(R.id.raceTextView);
//        wikiView = itemView.findViewById(R.id.wikiTextView);
//        image = itemView.findViewById(R.id.charImageView);

            nameView.setText(doc.getName());
            raceView.setText(doc.getRace());
            wikiView.setText(doc.getWikiUrl());
            Picasso.get().load(R.drawable.lotr_most).into(image);

    }

    @Override
    public void onClick(View v) {
        final ArrayList<Doc> docs = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CHARACTERS)
                .child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    docs.add(snapshot.getValue(Doc.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("characters", Parcels.wrap(docs));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
