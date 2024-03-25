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

    @Override
    public void sendImageToDB(Context context, Uri imageUri){
        try{
            byte[] imageData = getImageByte (context, imageUri);
            //using database's function to passing the imageData arrary
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private byte[] getImageByte(Context context, Uri imageUri) throws IOException{
        byte[] byteResult;
        InputStream stream = context.getContentResolver().openInputStream(imageUri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int length;
        while(true){
            assert stream != null;
            if ((length = stream.read(buffer)) == -1)
                break;
            else
                byteBuffer.write(buffer, 0, length);
        }
        byteResult = byteBuffer.toByteArray();
        return byteResult;
    }


    @Override
    public boolean uploadBook(IUser user, String bookName, String author, String date,
                              ArrayList<String> tags, ArrayList<String> genres){

        boolean succeed = false;
        Book newBook = null;
        if(bookName != null && author != null && date != null){
            if(!tags.isEmpty() && !genres.isEmpty()){
                newBook = new Book(0, bookName, author, date, tags, genres);
                succeed = bookPersistent.upload(newBook, user);
            }
        }
        return succeed;
    }
}
