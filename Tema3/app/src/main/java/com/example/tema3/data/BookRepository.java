package com.example.tema3.data;

import android.text.style.AlignmentSpan;

import com.example.tema3.controllers.ApplicationController;
import com.example.tema3.data.tasks.DeleteBookTask;
import com.example.tema3.data.tasks.GetAllBooksTask;
import com.example.tema3.data.tasks.InsertBookTask;
import com.example.tema3.data.tasks.UpdateBookTask;
import com.example.tema3.models.dbEntities.BookItem;

import java.util.List;

public class BookRepository {
    public static interface  OnBookRepositoryListener{
        void onSuccess();
    }
    public static interface  OnGetBooksListener{
        void onSuccess(List<BookItem> items);
    }
    private BookDataBase bookDataBase;

    public BookRepository(){
        bookDataBase = ApplicationController.getBookDataBase();
    }

    public void insertBook(BookItem book, OnBookRepositoryListener listener){
        new InsertBookTask(bookDataBase,listener).execute(book);
    }

    public void getAllBooks(OnGetBooksListener listener){
        new GetAllBooksTask(bookDataBase, listener).execute();
    }
    public void updateBook(BookItem book, OnBookRepositoryListener listener) {
        new UpdateBookTask(bookDataBase, listener).execute(book);
    }

    public void deleteBook(BookItem book, OnBookRepositoryListener listener) {
        new DeleteBookTask(bookDataBase, listener).execute(book);
    }
}
