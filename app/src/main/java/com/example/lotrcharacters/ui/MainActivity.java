package com.example.lotrcharacters.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.lotrcharacters.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    //    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//    private DatabaseReference mNamesCalledReference;
//    private ValueEventListener mNamesCalledReferenceListener;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

//    @BindView(R.id.nameEditText) EditText mNameToCall;
    @BindView(R.id.nextActButton) Button mNextActivity;
    @BindView(R.id.savedButton) Button msaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        mNamesCalledReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_NAMES); //pinpoint names node
//
//        mNamesCalledReferenceListener = mNamesCalledReference.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot namesSnapshot : dataSnapshot.getChildren()) {
//                    String location = namesSnapshot.getValue().toString();
//                    Log.d("Names updated", "New Name: " + location);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                }else {

                }
            }
        };

        //scklistener onclicklistener on a view
        mNextActivity.setOnClickListener(this);
        msaved.setOnClickListener(this);

        //data persistence next up
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
    }


        @Override
        public void onClick (View v){
            if (v == mNextActivity) {
                //intent and toasts
//            if (mNameToCall.getText().toString().isEmpty()) {
//                Toast.makeText(MainActivity.this, "Text is Required", Toast.LENGTH_LONG).show();
//                mNameToCall.setError("Text is Required");
//                Toast.makeText(MainActivity.this, "Text is Required", Toast.LENGTH_LONG).show();
//            } else {
//                String myNameIs = mNameToCall.getText().toString();
//                saveLocationToFirebase(myNameIs);
                //shared pref
//                if(!(myNameIs).equals("")) {
//                addToSharedPreferences(myNameIs);
//            }

                //
                Intent intent = new Intent(MainActivity.this, CharactersListActivity.class);
//                intent.putExtra("myName", myNameIs);
                startActivity(intent);
//                Toast.makeText(MainActivity.this, "Welcome " + myNameIs + "!", Toast.LENGTH_LONG).show();
            }
            //
            if(v==msaved){
//                Intent savedIntent = new Intent(MainActivity.this,SavedCharListActivity.class);
//                Intent intent2 = new Intent(MainActivity.this,SavedList_Try.class);
                Intent intent3 = new Intent(MainActivity.this, SavedCharList.class);
//                startActivity(savedIntent);
//                startActivity(intent2);
                startActivity(intent3);
            }

        }

//        public void saveLocationToFirebase (String names){
//            mNamesCalledReference.push().setValue(names);
//        }

//    @Override
//    protected void onDestroy() {
//        //    defined in 'top level' of activity, not nested within another block.
//        //    code here is executed when the user quits the activity.
//        super.onDestroy();
//        mNamesCalledReference.removeEventListener(mNamesCalledReferenceListener);
////        Log.d("On Destroy","Data change Listener Destroyed");
//    }

    //inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //on log-out option select
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //firebase logout
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        //return to login after log out of session
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
//    private void addToSharedPreferences(String name) {
//            mEditor.putString(Constants.PREFERENCES_NAME_KEY, name).apply();
//    }
    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }
    }