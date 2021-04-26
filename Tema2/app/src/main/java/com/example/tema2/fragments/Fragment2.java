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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tema2.R;
import com.example.tema2.adapters.AlbumAdapter;
import com.example.tema2.interfaces.ActivityFragmentCommunication;
import com.example.tema2.interfaces.OnItemClickListener;
import com.example.tema2.models.Album;
import com.example.tema2.models.User;
import com.example.tema2.singletons.VolleyConfigSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    private ActivityFragmentCommunication activityFragmentCommunication;
    private ArrayList<Album> albumsList = new ArrayList<Album>();
    private User user;


    private AlbumAdapter albumAdapter = new AlbumAdapter(albumsList, new OnItemClickListener(){

        @Override
        public void onUserClick(User user) {

        }

        @Override
        public void onArrowClicked(User user) {

        }

        @Override
        public void onAlbumClick(Album album) {
                activityFragmentCommunication.addF3(album);

        }

        @Override
        public void onPostClick(int userId) {

        }


    });

    public Fragment2(User user) {
        this.user = user;
    }

    public static Fragment2 newInstance(User user) {
        
        Bundle args = new Bundle();
        
        Fragment2 fragment = new Fragment2(user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAlbums();
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         View view =  inflater.inflate(R.layout.fragment2,container,false);
         RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rv_albums);
         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         recyclerView.setAdapter(albumAdapter);
         return view;
    }
    public void getAlbums(){
        VolleyConfigSingleton volleyConfigSingleton =VolleyConfigSingleton.getInstance(getActivity().getApplicationContext());
        RequestQueue queue= volleyConfigSingleton.getRequestQueue();
        String url = "https://jsonplaceholder.typicode.com/albums";
        url+="?userId=";
        url+=user.getId();
         /*
        * String url = "https://jsonplaceholder.typicode.com/users";
        url+="/"+user.getId()+"/albums";
        * */
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handleAlbumResponse(response);
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

    public void handleAlbumResponse(String response){
        try {
            JSONArray albumJsonArray = new JSONArray(response);
            for(int index=0;index<albumJsonArray.length();index++){
                JSONObject albumObj = albumJsonArray.getJSONObject(index);
                int id = albumObj.getInt("id");
                int userId = albumObj.getInt("userId");
                String title = albumObj.getString("title");

                Album album = new Album(userId,title,id);
                if(!albumsList.contains(album))
                albumsList.add(album);
            }
            albumAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAlbums();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ActivityFragmentCommunication) {
            activityFragmentCommunication = (ActivityFragmentCommunication) context;
        }
    }
}
