package com.example.lotrcharacters.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lotrcharacters.Constants;
import com.example.lotrcharacters.R;
import com.example.lotrcharacters.models.Doc;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedList_Try extends AppCompatActivity {

    private static final String TAG = "Saved Shiets";
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter adapter;
//    private RecyclerView recyclerView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        ButterKnife.bind(this);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CHARACTERS).child(uid);

//        recyclerView= findViewById(R.id.recyclerView);
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("characters");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.hasFixedSize();

        fetch();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    public void fetch() {
        Log.i(TAG, "fetch: ");
        FirebaseRecyclerOptions<Doc> options = new FirebaseRecyclerOptions.Builder<Doc>()
                .setQuery(mDatabase, Doc.class).build();

        final FirebaseRecyclerAdapter<Doc, CharViewHolder> firabaseAdapter= new FirebaseRecyclerAdapter<Doc, CharViewHolder>(options) {
            @NonNull
            @Override
            public CharViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_list_item, parent, false);
                return new CharViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull CharViewHolder holder, int position, @NonNull Doc model) {
                Log.i(TAG, "onBindViewHolder: ");

                final String characterId = getRef(position).getKey();

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.hasChildren()) {
                            String data = snapshot.getValue().toString();
                            Log.i(TAG, "onDataChange: " + data);

                            try {
                                JSONObject newsJSONObject = new JSONObject(data);
                                String charName = newsJSONObject.getJSONObject(characterId).getString("name");
                                String charRace = newsJSONObject.getJSONObject(characterId).getString("race");
                                String charWiki = newsJSONObject.getJSONObject(characterId).getString("wiki");
                                holder.wikiView.setText(charWiki);
                                holder.raceView.setText(charRace);
                                holder.nameView.setText(charName);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "onCancelled: ", error.toException());
                    }
                });
            }
        };
        mRecyclerView.setAdapter(firabaseAdapter);
        firabaseAdapter.startListening();
    }

    public class CharViewHolder extends RecyclerView.ViewHolder {

        public TextView nameView;
        public TextView raceView;
        public TextView wikiView;
        public ImageView imageView;

        public CharViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.nameTextView);
            raceView = itemView.findViewById(R.id.raceTextView);
            wikiView = itemView.findViewById(R.id.wikiTextView);
            imageView = itemView.findViewById(R.id.charImageView);
//            Picasso.get().load(R.drawable.eye_close_up).into(imageView);
        }
    }
}