package com.example.lotrcharacters.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lotrcharacters.Constants;
import com.example.lotrcharacters.R;
import com.example.lotrcharacters.adapters.FirebaseCharListAdapter;
import com.example.lotrcharacters.adapters.MyViewHolder;
import com.example.lotrcharacters.models.Doc;
import com.example.lotrcharacters.util.OnStartDragListener;
import com.example.lotrcharacters.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mRestaurantReference;
    private FirebaseCharListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @BindView(R.id.saveRecycler) RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        ButterKnife.bind(this);


        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mRestaurantReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CHARACTERS).child(uid);
        FirebaseRecyclerOptions<Doc> options =
                new FirebaseRecyclerOptions.Builder<Doc>()
                        .setQuery(mRestaurantReference, Doc.class)
                        .build();

        mFirebaseAdapter = new FirebaseCharListAdapter(options, mRestaurantReference, this, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
//        mRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
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
    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        mItemTouchHelper.startDrag(viewHolder);
    }
}