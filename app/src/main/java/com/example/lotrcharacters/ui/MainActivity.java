package com.example.lotrcharacters.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lotrcharacters.Constants;
import com.example.lotrcharacters.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @BindView(R.id.nameEditText) EditText mNameToCall;
    @BindView(R.id.nextActButton) Button mNextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //scklistener onclicklistener on a view
        mNextActivity.setOnClickListener(this);

        //data persistence next up
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
    }

    @Override
    public void onClick(View v) {
        if (v == mNextActivity) {
                //intent and toasts
//            if (mNameToCall.getText().toString().isEmpty()) {
//                Toast.makeText(MainActivity.this, "Text is Required", Toast.LENGTH_LONG).show();
//                mNameToCall.setError("Text is Required");
//                Toast.makeText(MainActivity.this, "Text is Required", Toast.LENGTH_LONG).show();
//            } else {
                String myNameIs = mNameToCall.getText().toString();
                //shared pref
                if(!(myNameIs).equals("")) {
                addToSharedPreferences(myNameIs);
            }

            //
                Intent intent = new Intent(MainActivity.this, CharactersListActivity.class);
                intent.putExtra("myName", myNameIs);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Welcome " + myNameIs + "!", Toast.LENGTH_LONG).show();
            }

        }

    private void addToSharedPreferences(String name) {
            mEditor.putString(Constants.PREFERENCES_NAME_KEY, name).apply();
    }

}
