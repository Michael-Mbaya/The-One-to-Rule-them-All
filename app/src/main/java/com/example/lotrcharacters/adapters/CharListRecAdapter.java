package com.example.lotrcharacters.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lotrcharacters.R;
import com.example.lotrcharacters.models.Doc;
import com.example.lotrcharacters.ui.DetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharListRecAdapter extends RecyclerView.Adapter<CharListRecAdapter.CharacterViewHolder> implements Filterable {


    private List<Doc> mDocList;
    private List<Doc> mDocListCopy;
    private Context mContext;

    public CharListRecAdapter(Context context, List<Doc> docList) {
        mContext = context;
        mDocList = docList;
        mDocListCopy = new ArrayList<>(mDocList);
    }

    @Override
    public CharListRecAdapter.CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.char_list_item, parent, false);
        CharacterViewHolder viewHolder = new CharacterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CharListRecAdapter.CharacterViewHolder holder, int position) {
        holder.bindCharacter(mDocList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDocList.size();
    }

    //Filterable method implementation
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Doc> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(mDocListCopy);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Doc item:mDocListCopy){
                    if(item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
//            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mDocList.clear();
            mDocList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };
    //

    public class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.charImageView) ImageView mCharPic;
        @BindView(R.id.charNameTextView) TextView mName;
        @BindView(R.id.raceTextView) TextView mRace;
        @BindView(R.id.wikiTextView) TextView mWiki;

        private Context mContext;

        public CharacterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindCharacter(Doc bugBunny) {
            Picasso.get().load(R.drawable.lotr_most).into(mCharPic);
            mName.setText(bugBunny.getName());
            mRace.setText(bugBunny.getRace());
            mWiki.setText(bugBunny.getWikiUrl());
        }

        @Override
        public void onClick(View v) {

            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("characters", Parcels.wrap(mDocList));
            mContext.startActivity(intent);
        }
    }


}
