package com.example.tema1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tema1.R;
import com.example.tema1.interfaces.ActivityFragmentCommunication;

public class MainActivity extends AppCompatActivity implements ActivityFragmentCommunication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void openSecondActivity() {
        Intent intent= new Intent(this,SecondActivityKotlin.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void replaceWithF2A2() {

    }

    @Override
    public void replaceWithF3A2() {

    }

    @Override
    public void goBackToF1A2() {

    }


    @Override
    public void closeActivity() {

    }
}