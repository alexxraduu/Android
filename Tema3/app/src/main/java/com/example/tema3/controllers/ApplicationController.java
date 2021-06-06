package com.example.tema3.controllers;

import android.app.Application;

import androidx.room.Room;

import com.example.tema3.data.BookDataBase;

public class ApplicationController extends Application {
    private static ApplicationController instance;

    private static BookDataBase bookDataBase;
    private final String bookDatabaseName="BookDB";

    @Override
    public void onCreate() {
        super.onCreate();
        instance =  this;
        setupDatabase();
    }

    private void setupDatabase(){
        bookDataBase= Room.databaseBuilder(getApplicationContext(),
                BookDataBase.class,
                bookDatabaseName).fallbackToDestructiveMigration().build();
    }

    public static BookDataBase getBookDataBase(){
        return bookDataBase;
    }

    public static ApplicationController getInstance(){
        return instance;
    }
}
