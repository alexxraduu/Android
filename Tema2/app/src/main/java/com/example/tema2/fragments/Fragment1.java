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
import com.example.tema2.adapters.UserAndPostAdapter;
import com.example.tema2.interfaces.ActivityFragmentCommunication;
import com.example.tema2.interfaces.OnItemClickListener;
import com.example.tema2.models.Album;
import com.example.tema2.models.ItemClass;
import com.example.tema2.models.Post;
import com.example.tema2.models.User;
import com.example.tema2.singletons.VolleyConfigSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class Fragment1 extends Fragment {
    private ActivityFragmentCommunication activityFragmentCommunication;
    private ArrayList<ItemClass> itemsList= new ArrayList<ItemClass>();
    private UserAndPostAdapter userAndPostAdapter = new UserAndPostAdapter(itemsList, new OnItemClickListener() {
        @Override
        public void onUserClick(User user) {
            activityFragmentCommunication.addF2(user);
        }

       @Override
        public void onArrowClicked(User user) {
           getUserPosts(user);
        }

        @Override
        public void onAlbumClick(Album album) {

        }

        @Override
        public void onPostClick(int userId) {
            User user = new User(-1," "," "," ");
            for(ItemClass item: itemsList){
                if(item instanceof User){
                    if(((User) item).getId() == userId){
                        user = ((User) item);
                    }
                }
            }
            if(user.getId() != -1){
                activityFragmentCommunication.addF2(user);
            }
            else {
                Toast.makeText(getContext(), "User not found!", Toast.LENGTH_SHORT).show();
            }

        }


    });


    public static Fragment1 newInstance() {
        
        Bundle args = new Bundle();
        
        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUsers();
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment1, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rv_users_and_posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemsList.clear();
        recyclerView.setAdapter(userAndPostAdapter);
        return view;
    }

   public void getUsers(){
       VolleyConfigSingleton volleyConfigSingleton =VolleyConfigSingleton.getInstance(getActivity().getApplicationContext());
        RequestQueue queue= volleyConfigSingleton.getRequestQueue();
        String url = "https://jsonplaceholder.typicode.com/users";
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handleUserResponse(response);
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

    public void handleUserResponse(String response){
        try {
            JSONArray userJsonArray = new JSONArray(response);
            for(int index=0;index<userJsonArray.length();index++){
                JSONObject userObj = userJsonArray.getJSONObject(index);
                int id = userObj.getInt("id");
                String name = userObj.getString("name");
                String username = userObj.getString("username");
                String email = userObj.getString("email");

                User user= new User(id,name,username,email);
                itemsList.add(user);
            }
            userAndPostAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void getUserPosts(User user){
        VolleyConfigSingleton volleyConfigSingleton =VolleyConfigSingleton.getInstance(getActivity().getApplicationContext());
        RequestQueue queue= volleyConfigSingleton.getRequestQueue();
        String url = "https://jsonplaceholder.typicode.com/posts";
        url+="?userId="+user.getId();
        /*
        * String url = "https://jsonplaceholder.typicode.com/users";
        url+="/"+user.getId()+"/posts";
        * */
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        user.setPressed(!user.isPressed());
                        handleUsersPostResponse(response,user);
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

    public void handleUsersPostResponse(String response, User user){
        try {
            JSONArray postsJsonArray = new JSONArray(response);
            for(int index=0;index<postsJsonArray.length();index++){
                JSONObject postsObj = postsJsonArray.getJSONObject(index);
                int id = postsObj.getInt("id");
                int userId = postsObj.getInt("userId");
                String title =postsObj.getString("title");

                Post post =  new Post(id,userId,title);
                if(user.isPressed()){
                    if(!itemsList.contains(post))
                        itemsList.add(itemsList.indexOf(user)+1,post);
                } else {
                    if(itemsList.contains(post)){
                        itemsList.remove(post);
                    }
                }

            }
             userAndPostAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUsers();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ActivityFragmentCommunication) {
            activityFragmentCommunication = (ActivityFragmentCommunication) context;
        }
    }
}
