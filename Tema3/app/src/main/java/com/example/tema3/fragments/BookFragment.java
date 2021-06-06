package com.example.tema3.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tema3.R;
import com.example.tema3.interfaces.ActivityFragmentCommunication;
import com.example.tema3.models.BookItemElement;

public class BookFragment  extends Fragment {
    private ActivityFragmentCommunication activityFragmentCommunication;
    private BookItemElement book;

    public BookFragment(BookItemElement book){
        this.book=book;
    }
    public static BookFragment newInstance(BookItemElement book) {

        Bundle args = new Bundle();

        BookFragment fragment = new BookFragment(book);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.book_fragment,container,false);
        TextView title = view.findViewById(R.id.tv_title);
        title.setText(book.getTitle());

        TextView author = view.findViewById(R.id.tv_author);
        author.setText(book.getAuthor());


        TextView description = view.findViewById(R.id.tv_description);
        description.setText(book.getDescription());
        return view;
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
