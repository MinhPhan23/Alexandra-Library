package com.alexandria_library.dso;

import androidx.annotation.NonNull;

import java.util.List;

public interface IBook {

    public void setID(int id);

    public int getID();

    public void setName(String name);

    public String getName();

    public void setAuthor(String author);

    public String getAuthor();

    public void setTags(List<String> tags);

    public List<String> getTags();

    public void setGenres(List<String>genres);

    public List<String> getGenres();

    public void setDate(String date);

    public String getDate();

    public boolean equals(final Book book);

    @NonNull
    public String toString();

}
