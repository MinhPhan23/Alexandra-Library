package com.alexandria_library.logic;

import android.content.Context;
import android.net.Uri;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

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
}
