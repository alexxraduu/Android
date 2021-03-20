package com.example.tema1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.SurfaceControl;

import com.example.tema1.R;
import com.example.tema1.fragments.Fragment1Activity2;
import com.example.tema1.fragments.Fragment2Activity2;
import com.example.tema1.fragments.Fragment3Activity2;
import com.example.tema1.interfaces.ActivityFragmentCommunication;

public class SecondActivity extends AppCompatActivity implements ActivityFragmentCommunication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        addF1A2();
    }

    private void addF1A2(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        String tag= Fragment1Activity2.class.getName();
        FragmentTransaction addTransaction=transaction.add(
                R.id.frame_activity2,
                Fragment1Activity2.newInstance(),
                tag
        );
        addTransaction.commit();
    }

    @Override
    public void openSecondActivity() {
        // TODO
    }

    @Override
    public void replaceWithF2A2() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        String tag= Fragment2Activity2.class.getName();
        FragmentTransaction replaceTransaction=transaction.replace(
                R.id.frame_activity2,
                Fragment2Activity2.newInstance(),
                tag
        );
        replaceTransaction.addToBackStack(tag);
        replaceTransaction.commit();
    }

    @Override
    public void replaceWithF3A2() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        String tag= Fragment3Activity2.class.getName();
        FragmentTransaction replaceTransaction=transaction.replace(
                R.id.frame_activity2,
                Fragment3Activity2.newInstance(),
                tag
        );
        replaceTransaction.addToBackStack(tag);
        replaceTransaction.commit();
    }

    @Override
    public void goBackToF1A2() {
        FragmentManager fragmentManager=getSupportFragmentManager();
         fragmentManager.popBackStack();
    }


    public void removeF1A2() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        String tag= Fragment1Activity2.class.getName();
        FragmentTransaction replaceTransaction=transaction.replace(
                R.id.frame_activity2,
                Fragment1Activity2.newInstance(),
                tag
        );
        replaceTransaction.addToBackStack(tag);
        replaceTransaction.commit();
    }

    @Override
    public void closeActivity() {
        finish();
    }
}