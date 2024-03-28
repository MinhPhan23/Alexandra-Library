package com.alexandria_library.dso;

import java.util.List;
import java.util.Objects;

public class Book implements IBook {

    // instance variables
    private int id;
    private String name;
    private String author;
    private String date;
    private List<String>tags;
    private List<String>genres;

    public Book (int bookID, String bookName, String bookAuthor, String bookDate,
                 List<String>bookTags, List<String>bookGenres) {
        id = bookID;
        name = bookName;
        author = bookAuthor;
        date = bookDate;
        tags = bookTags;
        genres = bookGenres;
    }

    @Override
    public Book clone(){
        return new Book(this.id, this.name, this.author, this.date, this.tags, this.genres);
    }
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getGenres() {
        return genres;
    }


    public void setID(int bookID) {
        id = bookID;
    }

    public void setName(String bookName) {
        name = bookName;
    }

    public void setAuthor(String bookAuthor) {
        author = bookAuthor;
    }

    public void setDate(String bookDate) {
        date = bookDate;
    }

    public void setTags(List<String> bookTags) {
        tags = bookTags;
    }

    public void setGenres(List<String> bookGenres) {
        genres = bookGenres;
    }

    public boolean equals(final Book book) {
        return Objects.equals(this.id, book.getID()) &&
                Objects.equals(this.name, book.getName()) &&
                Objects.equals(this.author, book.getAuthor()) &&
                Objects.equals(this.date, book.getDate()) &&
                Objects.equals(this.tags, book.getTags()) &&
                Objects.equals(this.genres, book.getGenres());
    }

    @Override
    public boolean noOrderEquals(final Book book){
        boolean equals = false;
        if(book.getName().equals(this.name) && book.getAuthor().equals(this.author)){
            if(book.getDate().equals(this.date)){
                if(book.getTags().size() == this.tags.size()){
                    if(book.getGenres().size() == this.genres.size()){
                        equals = true;
                        for(int i = 0; i<tags.size(); i++){
                            if(!book.getTags().contains(tags.get(i))){
                                equals = false;
                                break;
                            }
                        }
                        for(int j = 0; j<genres.size(); j++){
                            if(!book.getGenres().contains(genres.get(j))){
                                equals = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return equals;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", author = '" + author + '\'' +
                ", date = '" + date + '\'' +
                ", tag(s) = " + tags.toString() +
                ", genre(s) = " + genres.toString() +
                '}';
    }
}
