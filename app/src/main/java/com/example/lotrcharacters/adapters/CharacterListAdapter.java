package com.example.lotrcharacters.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lotrcharacters.R;
import com.example.lotrcharacters.models.Doc;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder> {

    private List<Doc> mDocList;
    private Context mContext;

    public CharacterListAdapter(Context context, List<Doc> docList) {
        mContext = context;
        mDocList = docList;
    }


    public class CharacterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.characterImageView) ImageView mCharPic;
        @BindView(R.id.charNameTextView) TextView mName;
        @BindView(R.id.raceTextView) TextView mRace;
        @BindView(R.id.wikiTextView) TextView mWiki;

        private Context mContext;

        public CharacterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindCharacter(Doc bugBunny) {
            mName.setText(bugBunny.getName());
            mRace.setText(bugBunny.getRace());
            mWiki.setText(bugBunny.getWikiUrl());
        }
    }

    @Override
    public CharacterListAdapter.CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_list_item, parent, false);
        CharacterViewHolder viewHolder = new CharacterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CharacterListAdapter.CharacterViewHolder holder, int position) {
        holder.bindCharacter(mDocList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDocList.size();
    }

}
