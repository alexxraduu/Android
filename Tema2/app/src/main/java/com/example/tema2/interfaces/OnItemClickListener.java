package com.example.tema2.interfaces;

import com.example.tema2.models.Album;
import com.example.tema2.models.ImageClass;
import com.example.tema2.models.User;

public interface OnItemClickListener {
    public void onUserClick(User user);
    public void onArrowClicked(User user);
    public void onAlbumClick(Album album);
    public void onPostClick(int userId);
}
