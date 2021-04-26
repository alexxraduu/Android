package com.example.tema2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tema2.R;
import com.example.tema2.adapters.ImageAdapter;
import com.example.tema2.interfaces.ActivityFragmentCommunication;
import com.example.tema2.interfaces.OnItemClickListener;
import com.example.tema2.models.Album;
import com.example.tema2.models.ImageClass;
import com.example.tema2.models.User;
import com.example.tema2.singletons.VolleyConfigSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment3 extends Fragment {
    private ActivityFragmentCommunication activityFragmentCommunication;
    private ArrayList<ImageClass> imagesList = new ArrayList<ImageClass>();
    private Album album;

    private ImageAdapter imageAdapter =  new ImageAdapter(imagesList, new OnItemClickListener() {
        @Override
        public void onUserClick(User user) {

        }

        @Override
        public void onArrowClicked(User user) {

        }

        @Override
        public void onAlbumClick(Album album) {

        }

        @Override
        public void onPostClick(int userId) {

        }


    });

    public Fragment3(Album album) {
        this.album = album;
    }

    public static Fragment3 newInstance(Album album) {

        Bundle args = new Bundle();

        Fragment3 fragment = new Fragment3(album);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getImages();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment3,container,false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rv_images);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(imageAdapter);
        return view;
    }
    public void getImages(){
        VolleyConfigSingleton volleyConfigSingleton =VolleyConfigSingleton.getInstance(getActivity().getApplicationContext());
        RequestQueue queue= volleyConfigSingleton.getRequestQueue();
        String url = "https://jsonplaceholder.typicode.com/photos";
        url+="?albumId=";
        url+=album.getId();
         /*
        * String url = "https://jsonplaceholder.typicode.com/albums";
        url+="/"+album.getId()+"/photos";
        * */
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handleImagesResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(stringRequest);
    }

    public void handleImagesResponse(String response){
        try {
            JSONArray imageJsonArray = new JSONArray(response);
            for(int index=0;index<imageJsonArray.length();index++){
                JSONObject imageObj = imageJsonArray.getJSONObject(index);
                int id = imageObj.getInt("id");
                int albumId = imageObj.getInt("albumId");
                String title = imageObj.getString("title");
                String thumbnailUrl =  imageObj.getString("thumbnailUrl");

                ImageClass image = new ImageClass(id,albumId,title,thumbnailUrl);
                imagesList.add(image);
            }
            imageAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ActivityFragmentCommunication) {
            activityFragmentCommunication = (ActivityFragmentCommunication) context;
        }
    }
}
