package com.alexandria_library.logic;

import android.content.Context;
import android.net.Uri;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.IUser;
import com.alexandria_library.dso.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class BookModifier implements IBookModifier{

    IBookPersistent bookPersistent;
    public BookModifier(){
        bookPersistent = Service.getBookPersistent();
    }
    public BookModifier(IBookPersistent persistent){this.bookPersistent = persistent;}

    @Override
    public boolean uploadBook(IUser user, int id, String bookName, String author, String date,
                              ArrayList<String> tags, ArrayList<String> genres){

        boolean succeed = false;
        Book newBook = null;
        if(bookName != null && author != null && date != null){
            if(!tags.isEmpty() && !genres.isEmpty()){
                newBook = new Book(id, bookName, author, date, tags, genres);
                succeed = bookPersistent.upload(newBook, user);
            }
        }
        return succeed;
    }
}
