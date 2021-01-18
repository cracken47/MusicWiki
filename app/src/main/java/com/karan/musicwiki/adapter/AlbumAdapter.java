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
import com.karan.musicwiki.database.entity.Album;
import com.karan.musicwiki.utils.RecyclerViewClickListener;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.HeroViewHolder> {
    private static RecyclerViewClickListener itemListener;
    public List<Album> albumTypeList;
    public Album album;
    private Context mContext;


    public AlbumAdapter(Context context, List<Album> assetTypeList, RecyclerViewClickListener itemListener) {
        this.mContext = context;
        this.albumTypeList = assetTypeList;
        AlbumAdapter.itemListener = itemListener;

    }


    @NonNull
    @Override
    public AlbumAdapter.HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.album_iten, parent, false);
        return new AlbumAdapter.HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.HeroViewHolder holder, int position) {

        album = albumTypeList.get(position);
        holder.title.setText(album.getName());
        holder.artist.setText(album.getArtist().getName());
        Glide.with(mContext).load(album.getImage().get(0).getText()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return albumTypeList.size();
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