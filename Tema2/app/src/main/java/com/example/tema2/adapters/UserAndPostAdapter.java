package com.example.tema2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema2.R;
import com.example.tema2.interfaces.OnItemClickListener;
import com.example.tema2.models.ItemClass;
import com.example.tema2.models.ItemType;
import com.example.tema2.models.Post;
import com.example.tema2.models.User;

import java.util.ArrayList;
import java.util.HashSet;

public class UserAndPostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private ArrayList<ItemClass> items;

    public UserAndPostAdapter(ArrayList<ItemClass> items, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if(viewType == ItemType.USER.getId()){
            View view =  inflater.inflate(R.layout.item_user,parent,false);
            UserViewHolder userViewHolder = new UserViewHolder(view);
            return userViewHolder;
        } else if(viewType == ItemType.POST.getId()){
            View view = inflater.inflate(R.layout.item_post,parent,false);
            PostViewHolder postViewHolder = new PostViewHolder(view);
            return postViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof UserViewHolder){
            User user = (User) items.get(position);
            ((UserViewHolder)holder).bind(user);
        } else if( holder instanceof PostViewHolder){
            Post post = (Post) items.get(position);
            ((PostViewHolder) holder).bind(post);
        }

    }

    @Override
    public int getItemViewType(int position) {
        ItemClass itemClass = items.get(position);
        return itemClass.getItemType().getId();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView username;
        private TextView email;
        private View view;
        private ImageView arrow;


        public UserViewHolder(View view) {
            super(view);
            this.view=view;
            name=view.findViewById(R.id.name);
            username=view.findViewById(R.id.username);
            email=view.findViewById(R.id.email);
            arrow=view.findViewById(R.id.arrow_image);
        }

        public void bind(User user) {
            name.setText(user.getName());
            username.setText(user.getUsername());
            email.setText(user.getEmail());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onUserClick(user);

                }
            });
            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
               public void onClick(View v) {
                    onItemClickListener.onArrowClicked(user);
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private View view;
        public PostViewHolder(View view){
            super(view);
            this.view = view;
            title = view.findViewById(R.id.post_title);
        }
        public void bind(Post post){
            title.setText(post.getTitle());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onPostClick(post.getUserId());
                }
            });
        }
    }
}
