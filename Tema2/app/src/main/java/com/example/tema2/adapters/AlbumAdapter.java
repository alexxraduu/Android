package com.example.tema2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema2.R;
import com.example.tema2.interfaces.OnItemClickListener;
import com.example.tema2.models.Album;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private ArrayList<Album> albums;

    public AlbumAdapter(ArrayList<Album> albums, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.albums = albums;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.item_album,parent,false);
        AlbumViewHolder albumViewHolder = new AlbumViewHolder(view);

        return albumViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Album album = albums.get(position);
        ((AlbumViewHolder)holder).bind(album);
    }
    @Override
    public int getItemCount() {
        return albums.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private View view;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
            title=view.findViewById(R.id.album_title);
        }

        public void bind(Album album){
            title.setText(album.getTitle());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onAlbumClick(album);
                }
            });

        }
    }
}
