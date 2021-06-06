package com.example.tema3.data.tasks;

import android.os.AsyncTask;

import com.example.tema3.data.BookDataBase;
import com.example.tema3.data.BookRepository;
import com.example.tema3.models.dbEntities.BookItem;

import java.util.List;

public class GetAllBooksTask extends AsyncTask<Void,Void, List<BookItem>> {
    private BookDataBase bookDataBase;
    private BookRepository.OnGetBooksListener listener;
    public GetAllBooksTask(BookDataBase bookDataBase, BookRepository.OnGetBooksListener listener){
        this.bookDataBase =  bookDataBase;
        this.listener=listener;
    }
    @Override
    protected List<BookItem> doInBackground(Void... voids) {
       return bookDataBase.bookDAO().getAll();
    }

    @Override
    protected void onPostExecute(List<BookItem> items) {
        super.onPostExecute(items);
        listener.onSuccess(items);
    }
}
