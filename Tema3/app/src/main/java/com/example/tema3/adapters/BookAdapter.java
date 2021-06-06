package com.example.tema3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema3.R;
import com.example.tema3.data.BookRepository;
import com.example.tema3.interfaces.OnItemClickListener;
import com.example.tema3.models.BookItemElement;
import com.example.tema3.models.dbEntities.BookItem;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookItemViewHolder> {
    private List<BookItemElement> bookItems;
    private OnItemClickListener onItemClickListener;

    public BookAdapter(List<BookItemElement> bookItems, OnItemClickListener onItemClickListener) {
        this.bookItems = bookItems;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BookItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.book_item, parent, false);

        BookItemViewHolder bookItemViewHolder = new BookItemViewHolder(view);

        return bookItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookItemViewHolder holder, int position) {
        BookItemElement bookItemElement = bookItems.get(position);
        holder.bind(bookItemElement);
    }

    @Override
    public int getItemCount() {
        return bookItems.size();
    }

    class BookItemViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTV;
        private TextView authorTV;
        private TextView descriptionTV;
        private Button deleteBtn;
        private View view;

        public BookItemViewHolder(View view) {
            super(view);
            this.view = view;
            titleTV = view.findViewById(R.id.title);
            authorTV = view.findViewById(R.id.author);
            descriptionTV = view.findViewById(R.id.description);
            deleteBtn = view.findViewById(R.id.btn_delete);
        }

        public void bind(BookItemElement bookItemElement) {
            titleTV.setText(bookItemElement.getTitle());
            authorTV.setText(bookItemElement.getAuthor());
            descriptionTV.setText(bookItemElement.getDescription());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onBookClick(bookItemElement);
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onBookDeleteClick(bookItemElement);
                }
            });
        }
    }
}
