package com.example.lotrcharacters.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lotrcharacters.R;
import com.example.lotrcharacters.adapters.CharacterListAdapter;
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


public class CharactersListActivity extends AppCompatActivity {
    public static final String TAG = CharactersListActivity.class.getSimpleName();
//    private SharedPreferences mSharedPreferences;
//    private String mRecentName;

    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
//    @BindView(R.id.welcomeTextView) TextView mWelcomeName;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private CharacterListAdapter mAdapter;
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        ButterKnife.bind(this);

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
//                    List<Doc> newList = lotrRes.getDocs();
                    Log.i("Characters List",newList.toString());
                    if(newList==null){
                        Log.i("Empty results","Can' believe this Empty Things");
                    }else {
                        Log.i("Some results","I believe this! Wonders Man!!!");
                    }


                    mAdapter = new CharacterListAdapter(CharactersListActivity.this, newList);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(CharactersListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showCharacters();
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
//        mList.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

}


//    String[] names = new String[]{"xy","xy","xy","xy","xy","xy","xy","xy","xy","xy"};
//    String[] races = new String[]{"z","z","z","z","z","z","z","z","z","z"};     // #JoynerLucas - I'm Not Racist