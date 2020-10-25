package com.example.lotrcharacters.ui;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lotrcharacters.Constants;
import com.example.lotrcharacters.models.Doc;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import com.example.lotrcharacters.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CharDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.eyeSauronImage) ImageView mImge;
    @BindView(R.id.nameTextView) TextView mName;
    @BindView(R.id.raceTextView) TextView mRace;
    @BindView(R.id.realmTextView) TextView mRealm;
    @BindView(R.id.wikiTextView) TextView mWiki;
    @BindView(R.id.birthTextView) TextView mBirth;
    @BindView(R.id.deathTextView) TextView mDeath;
    @BindView(R.id.saveButton) Button mSaveCharacter;

    private Doc mCharacter;

    public CharDetailFragment() {
        // Required empty public constructor
    }

    public static CharDetailFragment newInstance(Doc character) {
        CharDetailFragment charDetailFragment = new CharDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("character", Parcels.wrap(character));
        charDetailFragment.setArguments(args);
        return charDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCharacter = Parcels.unwrap(getArguments().getParcelable("character"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_char_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(R.drawable.eye_close_up).into(mImge);
        mName.setText(mCharacter.getName());
        mBirth.setText(mCharacter.getBirth());
        mDeath.setText(mCharacter.getDeath());
        mRace.setText(mCharacter.getRace());
        mWiki.setText(mCharacter.getWikiUrl());
        mRealm.setText(mCharacter.getRealm());

        mSaveCharacter.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveCharacter) {
            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_CHARACTERS);
            restaurantRef.push().setValue(mCharacter);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}





//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.lotrcharacters.R;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link CharDetailFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class CharDetailFragment extends Fragment {
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public CharDetailFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment CharDetailFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static CharDetailFragment newInstance(String param1, String param2) {
//        CharDetailFragment fragment = new CharDetailFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //
//
//        //
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_char_detail, container, false);
//    }
//}