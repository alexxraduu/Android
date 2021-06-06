package com.example.tema3.interfaces;

import com.example.tema3.models.BookItemElement;
import com.example.tema3.models.dbEntities.BookItem;

public interface OnItemClickListener {
    public void onBookClick(BookItemElement book);
    public void onBookDeleteClick(BookItemElement book);
}
