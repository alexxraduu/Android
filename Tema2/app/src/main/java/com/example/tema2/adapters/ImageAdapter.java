package com.example.tema2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.tema2.R;
import com.example.tema2.interfaces.OnItemClickListener;
import com.example.tema2.models.ImageClass;
import com.example.tema2.singletons.VolleyConfigSingleton;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private ArrayList<ImageClass> images;

    public ImageAdapter(ArrayList<ImageClass> images, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.images = images;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.item_image,parent,false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);
       return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageClass image =  images.get(position);
        ((ImageViewHolder)holder).bind(image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private View view;

        public ImageViewHolder( View itemView) {
            super(itemView);
            view=itemView;
           imageView = view.findViewById(R.id.image_view);
        }

        void bind(ImageClass image){
            ImageLoader imageLoader = VolleyConfigSingleton.getInstance(imageView.getContext().getApplicationContext()).getImageLoader();
            imageLoader.get(image.getThumbnailUrl()+".png", new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    imageView.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

        }
    }
}
