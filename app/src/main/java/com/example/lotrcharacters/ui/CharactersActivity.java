package com.example.lotrcharacters.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lotrcharacters.R;
import com.example.lotrcharacters.adapters.CharacterListArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharactersActivity extends AppCompatActivity {

    String[] names = new String[]{"xy","xy","xy","xy","xy","xy","xy","xy","xy","xy"};
    String[] races = new String[]{"z","z","z","z","z","z","z","z","z","z"};     // #JoynerLucas - I'm Not Racist

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

        //Custom Array and/or List adapters
        ArrayAdapter adapter
                = new CharacterListArrayAdapter(CharactersActivity.this, android.R.layout.simple_list_item_1, names, races);
        mList.setAdapter(adapter);
        //Toast on list items
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String listItem = ((TextView)view).getText().toString();
                Toast.makeText(CharactersActivity.this, listItem, Toast.LENGTH_LONG).show();
            }
        });

    }

}
