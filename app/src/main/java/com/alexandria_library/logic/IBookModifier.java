package com.alexandria_library.logic;

import android.content.Context;
import android.net.Uri;

import com.alexandria_library.dso.IUser;
import com.alexandria_library.dso.User;

import java.util.ArrayList;

public interface IBookModifier {

    public void sendImageToDB(Context context, Uri imageUri);
    public boolean uploadBook(IUser user, int id, String bookName, String author, String date,
                              ArrayList<String> tags, ArrayList<String> genres);
}
