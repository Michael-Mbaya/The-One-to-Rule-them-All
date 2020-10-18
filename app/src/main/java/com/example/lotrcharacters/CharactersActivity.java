package com.example.lotrcharacters;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharactersActivity extends AppCompatActivity {

    @BindView(R.id.welcomeTextView) TextView mWelcomeName;
    @BindView(R.id.charactersList) ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        ButterKnife.bind(this);

        //getting/pull data from intent extra
        Intent intent = getIntent();
        String input = intent.getStringExtra("myName");
        mWelcomeName.setText("Welcome "+input+"!");
    }

}
