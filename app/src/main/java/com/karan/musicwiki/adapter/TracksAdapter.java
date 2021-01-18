package com.karan.musicwiki.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.karan.musicwiki.R;
import com.karan.musicwiki.database.entity.Track;
import com.karan.musicwiki.utils.RecyclerViewClickListener;

import java.util.List;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.HeroViewHolder> {
    private static RecyclerViewClickListener itemListener;
    public List<Track> trackTypeList;
    public Track track;
    private Context mContext;


    public TracksAdapter(Context context, List<Track> assetTypeList, RecyclerViewClickListener itemListener) {
        this.mContext = context;
        this.trackTypeList = assetTypeList;
        TracksAdapter.itemListener = itemListener;

    }


    @NonNull
    @Override
    public TracksAdapter.HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.album_iten, parent, false);
        return new TracksAdapter.HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TracksAdapter.HeroViewHolder holder, int position) {

        track = trackTypeList.get(position);
        holder.title.setText(track.getName());
        holder.artist.setText(track.getArtist().getName());
        Glide.with(mContext).load(track.getImage().get(0).getText()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return trackTypeList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView title;
        TextView artist;
        ImageView imageView;


        HeroViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_tv);
            artist = itemView.findViewById(R.id.subtitle_tv);
            imageView = itemView.findViewById(R.id.imageview);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getPosition());
        }
    }
}


