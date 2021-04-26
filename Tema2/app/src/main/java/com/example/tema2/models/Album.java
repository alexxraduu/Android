package com.example.tema2.models;

import java.util.Objects;

public class Album {

    private int userId;
    private String title;
    private int id;

    public Album(int userId, String title, int id) {
        this.userId = userId;
        this.title = title;
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return userId == album.userId &&
                id == album.id &&
                title.equals(album.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, title, id);
    }
}
