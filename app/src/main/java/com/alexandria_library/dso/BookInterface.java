package com.alexandria_library.dso;

public interface BookInterface {

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

    public void setDate(String date);

    public String getDate();

}
