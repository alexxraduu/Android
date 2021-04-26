package com.example.tema2.models;

import java.util.ArrayList;
import java.util.HashSet;

public class User extends ItemClass{

    private int id;
    private String name;
    private String username;
    private String email;
    private boolean isPressed;

    public User(int id, String name, String username, String email) {
        super(ItemType.USER);
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.isPressed = false;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
