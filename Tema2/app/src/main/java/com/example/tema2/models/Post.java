package com.example.tema2.models;

import java.util.Objects;

public class Post extends ItemClass{

    private int id;
    private int userId;
    private String title;

    public Post(int id, int userId, String title) {
        super(ItemType.POST);
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                userId == post.userId &&
                title.equals(post.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title);
    }
}
