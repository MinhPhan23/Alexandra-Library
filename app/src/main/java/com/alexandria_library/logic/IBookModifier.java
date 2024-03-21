package com.alexandria_library.logic;

import android.content.Context;
import android.net.Uri;

public interface IBookModifier {

    public void sendImageToDB(Context context, Uri imageUri);
}
