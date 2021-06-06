package com.example.tema3.data.tasks;

import android.os.AsyncTask;

import com.example.tema3.data.BookDataBase;
import com.example.tema3.data.BookRepository;
import com.example.tema3.models.dbEntities.BookItem;

public class InsertBookTask extends AsyncTask<BookItem,Void,Void> {
    private BookDataBase bookDataBase;
    private BookRepository.OnBookRepositoryListener listener;
    public InsertBookTask(BookDataBase bookDataBase, BookRepository.OnBookRepositoryListener listener){
        this.bookDataBase =  bookDataBase;
        this.listener=listener;
    }
    @Override
    protected Void doInBackground(BookItem... bookItems) {
        bookDataBase.bookDAO().insertBook(bookItems[0]);
        return null ;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
