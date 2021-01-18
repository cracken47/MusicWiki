package com.karan.musicwiki.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.karan.musicwiki.R;
import com.karan.musicwiki.database.entity.Tag;
import com.karan.musicwiki.utils.RecyclerViewClickListener;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.HeroViewHolder> {
    private static RecyclerViewClickListener itemListener;
    public List<Tag> tagTypeList;
    public Tag tag;
    private Context mContext;


    public GenreAdapter(Context context, List<Tag> assetTypeList, RecyclerViewClickListener itemListener) {
        this.mContext = context;
        this.tagTypeList = assetTypeList;
        GenreAdapter.itemListener = itemListener;

    }


    @NonNull
    @Override
    public GenreAdapter.HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.genre_item, parent, false);
        return new GenreAdapter.HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.HeroViewHolder holder, int position) {

        tag = tagTypeList.get(position);
        holder.textView.setText(tag.getName());

    }

    @Override
    public int getItemCount() {
        return tagTypeList.size();
    }

    public void setAddtionalData(List<Tag> arr) {
        this.tagTypeList.addAll(arr);
        this.notifyDataSetChanged();
    }

    public void removeAddtionalData(List<Tag> arr) {
        this.tagTypeList.removeAll(arr);
        this.notifyDataSetChanged();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textView;


        HeroViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.genre_name_tv);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getPosition());
        }
    }
}