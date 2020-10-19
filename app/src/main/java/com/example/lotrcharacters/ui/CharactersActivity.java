package com.example.lotrcharacters.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lotrcharacters.R;
import com.example.lotrcharacters.adapters.CharacterListArrayAdapter;
import com.example.lotrcharacters.models.Doc;
import com.example.lotrcharacters.models.MyPreciousResponse;
import com.example.lotrcharacters.network.LotrAPI;
import com.example.lotrcharacters.network.LotrClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharactersActivity extends AppCompatActivity {
    public static final String TAG = CharactersActivity.class.getSimpleName();

    @BindView(R.id.welcomeTextView) TextView mWelcomeName;
    @BindView(R.id.charactersList) ListView mList;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        ButterKnife.bind(this);

        //getting/pull data from intent extra
        Intent intent = getIntent();
        String input = intent.getStringExtra("myName");
        mWelcomeName.setText("Welcome "+input+"!");


        //apicall
        LotrAPI client = LotrClient.getClient();
        Call<MyPreciousResponse> call = client.getCharacters(); //query limit and sort

        //call response and/or failure
        call.enqueue(new Callback<MyPreciousResponse>() {

            @Override
            public void onResponse(Call<MyPreciousResponse> call, Response<MyPreciousResponse> response) {
                if (response.isSuccessful()) {

                    MyPreciousResponse lotrRes = response.body();
                    Log.i("Response Body",lotrRes.toString());

                    List<Doc> newList = lotrRes.getDocs();
                    Log.i("Characters List",newList.toString());
                    if(newList==null){
                        Log.i("Empty results","Can' believe this Empty Things");
                    }else {
                        Log.i("Some results","I believe this! Wonders Man!!!");
                    }

                    String[] characterNames = new String[newList.size()];
                    String[] characterRaces = new String[newList.size()];    //Am not Racist

                    for (int i = 0; i < characterNames.length; i++){
                        characterNames[i] = newList.get(i).getName();
                    }

                    for (int i = 0; i < characterRaces.length; i++) {
                        characterRaces[i] = newList.get(i).getRace();
                    }

                    //Custom adapter
                    ArrayAdapter adapter = new CharacterListArrayAdapter(CharactersActivity.this, android.R.layout.simple_list_item_1, characterNames, characterRaces);
                    mList.setAdapter(adapter);

                    showCharacters();
                    hideProgressBar();
                }else {
                    showUnsuccessfulMessage();
                    hideProgressBar();
                }
            }
            @Override
            public void onFailure(Call<MyPreciousResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                showFailureMessage();
                hideProgressBar();
            }

        });

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String listItem = ((TextView)view).getText().toString();
                Toast.makeText(CharactersActivity.this, listItem, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showCharacters() {
        mList.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

}


//    String[] names = new String[]{"xy","xy","xy","xy","xy","xy","xy","xy","xy","xy"};
//    String[] races = new String[]{"z","z","z","z","z","z","z","z","z","z"};     // #JoynerLucas - I'm Not Racist