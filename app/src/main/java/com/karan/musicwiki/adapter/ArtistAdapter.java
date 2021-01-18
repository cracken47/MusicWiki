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
import com.karan.musicwiki.database.entity.Artist1;
import com.karan.musicwiki.utils.RecyclerViewClickListener;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.HeroViewHolder> {
    private static RecyclerViewClickListener itemListener;
    public List<Artist1> artistTypeList;
    public Artist1 artist1;
    private Context mContext;


    public ArtistAdapter(Context context, List<Artist1> assetTypeList, RecyclerViewClickListener itemListener) {
        this.mContext = context;
        this.artistTypeList= assetTypeList;
        ArtistAdapter.itemListener = itemListener;

    }


    @NonNull
    @Override
    public ArtistAdapter.HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.album_iten, parent, false);
        return new ArtistAdapter.HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.HeroViewHolder holder, int position) {

        artist1 = artistTypeList.get(position);
        holder.title.setText(artist1.getName());
        holder.artist.setText(artist1.getStreamable());
        Glide.with(mContext).load(artist1.getImage().get(0).getText()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return artistTypeList.size();
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