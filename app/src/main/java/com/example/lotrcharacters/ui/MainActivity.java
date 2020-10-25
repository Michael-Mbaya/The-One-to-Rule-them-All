package com.example.lotrcharacters.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lotrcharacters.Constants;
import com.example.lotrcharacters.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    //    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
    private DatabaseReference mNamesCalledReference;

    @BindView(R.id.nameEditText) EditText mNameToCall;
    @BindView(R.id.nextActButton) Button mNextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mNamesCalledReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_NAMES); //pinpoint names node



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //scklistener onclicklistener on a view
        mNextActivity.setOnClickListener(this);

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
                String myNameIs = mNameToCall.getText().toString();
                saveLocationToFirebase(myNameIs);
                //shared pref
//                if(!(myNameIs).equals("")) {
//                addToSharedPreferences(myNameIs);
//            }

                //
                Intent intent = new Intent(MainActivity.this, CharactersListActivity.class);
                intent.putExtra("myName", myNameIs);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Welcome " + myNameIs + "!", Toast.LENGTH_LONG).show();
            }

        }

        public void saveLocationToFirebase (String names){
            mNamesCalledReference.push().setValue(names);
        }

//    private void addToSharedPreferences(String name) {
//            mEditor.putString(Constants.PREFERENCES_NAME_KEY, name).apply();
//    }

    }

