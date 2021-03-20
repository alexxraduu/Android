package com.example.tema1.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tema1.R;
import com.example.tema1.interfaces.ActivityFragmentCommunication;

public class Fragment3Activity2 extends Fragment {

    private ActivityFragmentCommunication activityFragmentCommunication;

    public static Fragment3Activity2 newInstance() {

        Bundle args = new Bundle();

        Fragment3Activity2 fragment = new Fragment3Activity2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment3_activity2,container,false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ActivityFragmentCommunication) {
            activityFragmentCommunication = (ActivityFragmentCommunication) context;
        }
    }
}
