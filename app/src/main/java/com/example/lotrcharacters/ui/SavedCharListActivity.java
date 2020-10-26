package com.example.lotrcharacters.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lotrcharacters.Constants;
import com.example.lotrcharacters.R;
import com.example.lotrcharacters.adapters.FirebaseCharacterViewHolder;
import com.example.lotrcharacters.models.Doc;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedCharListActivity extends AppCompatActivity {

    private DatabaseReference mRestaurantReference;
    private FirebaseRecyclerAdapter<Doc, FirebaseCharacterViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters_list);
        ButterKnife.bind(this);
        mRestaurantReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CHARACTERS);
        setUpFirebaseAdapter();
    }


    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Doc> options =
                new FirebaseRecyclerOptions.Builder<Doc>()
                        .setQuery(mRestaurantReference, Doc.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Doc, FirebaseCharacterViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseCharacterViewHolder firebaseCharacterViewHolder, int position, @NonNull Doc doc) {
                firebaseCharacterViewHolder.bindCharacter(doc);
            }

            @NonNull
            @Override
            public FirebaseCharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_list_item, parent, false);
                return new FirebaseCharacterViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }

}