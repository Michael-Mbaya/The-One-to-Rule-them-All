package com.example.lotrcharacters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.nameEditText) EditText mNameToCall;
    @BindView(R.id.nextActButton) Button mNextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //scklistener onclicklistener on a view
        mNextActivity.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == mNextActivity) {
                //intent and toasts
            if (mNameToCall.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Text is Required", Toast.LENGTH_LONG).show();
                mNameToCall.setError("Text is Required");
                Toast.makeText(MainActivity.this, "Text is Required", Toast.LENGTH_LONG).show();
            } else {
                String myNameIs = mNameToCall.getText().toString();
                Intent intent = new Intent(MainActivity.this, CharactersActivity.class);
                //pass data with intent extras
                intent.putExtra("myName", myNameIs);
                //go to search activity
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Welcome " + myNameIs + "!", Toast.LENGTH_LONG).show();
            }

        }

    }

}