package com.example.lotrcharacters.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lotrcharacters.Constants;
import com.example.lotrcharacters.R;
import com.example.lotrcharacters.models.Doc;
import com.example.lotrcharacters.ui.CharacterDetailActivity;
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

public class FirebaseCharViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    View v;
    Context mContext;

    public FirebaseCharViewHolder(View itemView) {
        super(itemView);
        v = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindCharacter(Doc character) {

        ImageView charImageView = (ImageView) v.findViewById(R.id.charImageView);
        TextView nameTextView = (TextView) v.findViewById(R.id.charNameTextView);
        TextView raceTextView = (TextView) v.findViewById(R.id.raceTextView);
//        TextView realmTextView = (TextView) v.findViewById(R.id.realmTextView);
//        TextView birthTextView = (TextView) v.findViewById(R.id.birthTextView);
//        TextView deathTextView = (TextView) v.findViewById(R.id.deathTextView);
        TextView wikiTextView = (TextView) v.findViewById(R.id.wikiTextView);

        Picasso.get().load(R.drawable.frodo_ring).into(charImageView);
        nameTextView.setText(character.getName());
        raceTextView.setText(character.getRace());
//        realmTextView.setText(character.getRealm());
//        birthTextView.setText(character.getBirth());
//        deathTextView.setText(character.getDeath());
        wikiTextView.setText(character.getWikiUrl());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Doc> docs = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CHARACTERS).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    docs.add(snapshot.getValue(Doc.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, CharacterDetailActivity.class);
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
