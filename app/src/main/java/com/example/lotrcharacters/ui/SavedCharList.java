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
import com.example.lotrcharacters.adapters.MyViewHolder;
import com.example.lotrcharacters.models.Doc;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SavedCharList extends AppCompatActivity {
    private static final String TAG = "Saved Characters!!!";

    DatabaseReference reference;
    private FirebaseRecyclerOptions<Doc> options;
    private FirebaseRecyclerAdapter<Doc, MyViewHolder> adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_char);
        //
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        //
        recyclerView = findViewById(R.id.saveRecycler);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        reference = FirebaseDatabase.getInstance().getReference().child("characters");
        reference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CHARACTERS).child(uid);
//                        .getReference(Constants.FIREBASE_CHILD_CHARACTERS).child(uid);

        options = new FirebaseRecyclerOptions.Builder<Doc>()
                .setQuery(reference,Doc.class).build();
        adapter = new FirebaseRecyclerAdapter<Doc, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Doc model) {
                holder.nameView.setText(model.getName());
                holder.raceView.setText(model.getRace());
                holder.wikiView.setText(model.getWikiUrl());
                holder.image.setImageResource(R.drawable.lotr_most);
//                Picasso.get().load(R.drawable.lotr_most).into(mCharPic)
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_list_item,parent,false);
                return new MyViewHolder(v);
//                return null;
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(adapter!= null) {
            adapter.stopListening();
        }
    }

}