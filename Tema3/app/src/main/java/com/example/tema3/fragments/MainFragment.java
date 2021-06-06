package com.example.tema3.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema3.R;
import com.example.tema3.adapters.BookAdapter;
import com.example.tema3.data.BookRepository;
import com.example.tema3.interfaces.ActivityFragmentCommunication;
import com.example.tema3.interfaces.OnItemClickListener;
import com.example.tema3.models.BookItemElement;
import com.example.tema3.models.dbEntities.BookItem;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private ActivityFragmentCommunication activityFragmentCommunication;
    private BookRepository bookRepository = new BookRepository();
    private List<BookItemElement> books = new ArrayList<BookItemElement>();
    private BookAdapter bookAdapter = new BookAdapter(books, new OnItemClickListener() {
        @Override
        public void onBookClick(BookItemElement book) {
           activityFragmentCommunication.addBookFragment(book);
        }

        @Override
        public void onBookDeleteClick(BookItemElement book) {
            BookItem bookItem = new BookItem(book.getId(),book.getTitle(),book.getAuthor(),book.getDescription());

//            bookRepository.deleteBook(bookItem, new BookRepository.OnBookRepositoryListener() {
//                @Override
//                public void onSuccess() {
//                    Toast.makeText(getContext(), "Added!", Toast.LENGTH_SHORT).show());
//                    int position = books.indexOf(book);
//                    books.remove(position);
//                    bookAdapter.notifyItemRemoved(position);
//                }
//            });

            bookRepository.deleteBook(bookItem, ()->{
                Toast.makeText(getContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                    int position = books.indexOf(book);
                    books.remove(position);
                    bookAdapter.notifyItemRemoved(position);
            });
        }
    });

    public static MainFragment newInstance() {
        
        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_add_update).setOnClickListener(it ->{
            addOrUpdate();
        });
        getBooks();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.main_fragment,container,false);
        RecyclerView booksRecyclerView = (RecyclerView) view.findViewById(R.id.rv_books);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        booksRecyclerView.setAdapter(bookAdapter);
        return view;
    }
    public void getBooks(){
        bookRepository.getAllBooks(booksResult -> {
            books.clear();
            for (BookItem book : booksResult
            ) {
                books.add(book.convert());
            }
            bookAdapter.notifyDataSetChanged();
        });
    }
    public void addOrUpdate(){
        EditText titleEdit =  getView().findViewById(R.id.edit_title);
        EditText authorEdit =  getView().findViewById(R.id.edit_author);
        EditText descriptionEdit =  getView().findViewById(R.id.edit_description);

        String title = titleEdit.getText().toString();
        String author = authorEdit.getText().toString();
        String description = descriptionEdit.getText().toString();

        if(title.isEmpty()){
            titleEdit.setError("Required field!");
            return;
        }else{
            titleEdit.setError(null);
        }

        if(author.isEmpty()){
            authorEdit.setError("Required field!");
            return;
        }else{
            authorEdit.setError(null);
        }

        if(description.isEmpty()){
            descriptionEdit.setError("Required field!");
            return;
        }else{
            descriptionEdit.setError(null);
        }

        BookItem book= new BookItem(title,author,description);
        boolean isBook=false;
        for(BookItemElement b: books){
            if(b.getTitle().equals(title) &&b.getAuthor().equals(author)){
                isBook=true;
                book.id=b.getId();
            }
        }
        if(isBook){
            updateBook(book);
        }
        else{
            addBook(book);
        }

        titleEdit.setText("");
        authorEdit.setText("");
        descriptionEdit.setText("");

    }
    public void addBook(BookItem book){
        bookRepository.insertBook(book, () ->
            Toast.makeText(getContext(), "Added!", Toast.LENGTH_SHORT).show());
        books.add(book.convert());
        bookAdapter.notifyItemChanged(books.size() - 1);
}

    public void updateBook(BookItem book){
        bookRepository.updateBook(book, () ->
                Toast.makeText(getContext(), "Updated!", Toast.LENGTH_SHORT).show());
        for(BookItemElement b: books){
            if(b.getTitle().equals(book.title) && b.getAuthor().equals(book.author)){
                books.get(books.indexOf(b)).setDescription(book.description);
                bookAdapter.notifyItemChanged(books.indexOf(b));
            }
        }
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ActivityFragmentCommunication) {
            activityFragmentCommunication = (ActivityFragmentCommunication) context;
        }
    }

}
