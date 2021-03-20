package com.example.tema1.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tema1.R;
import com.example.tema1.interfaces.ActivityFragmentCommunication;

public class Fragment2Activity2 extends Fragment {

    private ActivityFragmentCommunication activityFragmentCommunication;

    public static Fragment2Activity2 newInstance() {

        Bundle args = new Bundle();

        Fragment2Activity2 fragment = new Fragment2Activity2();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2_activity2,container,false);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ActivityFragmentCommunication) {
            activityFragmentCommunication = (ActivityFragmentCommunication) context;
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.btn1_f2a2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityFragmentCommunication!=null){
                    activityFragmentCommunication.replaceWithF3A2();
                }

            }
        });

        Button button2=view.findViewById(R.id.btn2_f2a2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityFragmentCommunication!=null){
                    activityFragmentCommunication.goBackToF1A2();
                }
            }
        });

        Button button3=view.findViewById(R.id.btn3_f2a2);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityFragmentCommunication!=null){
                    activityFragmentCommunication.closeActivity();
                }
            }
        });
    }
}
