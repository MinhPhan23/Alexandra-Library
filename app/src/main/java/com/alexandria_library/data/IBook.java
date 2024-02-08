package com.alexandria_library.data;

import androidx.annotation.NonNull;

import com.alexandria_library.dso.Book;

import java.time.LocalDate;

public interface IBook {

    public void setID(int id);

    public int getID();

    public void setName(String name);

    public String getName();

    public void setAuthor(String author);

    public String getAuthor();

    public void setTags(String[] tags);

    public String[] getTags();

    public void setGenres(String[] genres);

    public String[] getGenres();

    public void setDate(LocalDate date);

    public void setDate(String date);

    public LocalDate getDate();

    public boolean equals(final Book book);

    @NonNull
    public String toString();

}
