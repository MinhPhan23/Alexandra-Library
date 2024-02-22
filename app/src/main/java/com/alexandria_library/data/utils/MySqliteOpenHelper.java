package com.alexandria_library.data.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/******
 * MySqliteOpenHelper is a tool class
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper {
    final private String booksTable = "create table Books " + "(" +
            "    _id         integer not null " +
            "        primary key autoincrement, " +
            "    book_name   text    not null, " +
            "    book_author text    not null " + ");";

    private static SQLiteOpenHelper mySqlite;
    public static synchronized SQLiteOpenHelper getMySqlite(Context context){
        if(mySqlite == null){
            //you should increment by 1 the version value, if you did change on database
            mySqlite = new MySqliteOpenHelper(context,"LibraryDB.db", null, 1);
        }
        return mySqlite;
    }

    private MySqliteOpenHelper(@Nullable Context context, @Nullable String name,
                              @Nullable SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
