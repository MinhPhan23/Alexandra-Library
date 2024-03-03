package com.alexandria_library.data.hsqldb;

import com.alexandria_library.data.IBookPersistentIntermediate;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookPersistenceHSQLDB implements IBookPersistentIntermediate {

    private final String dbPath;

    public BookPersistenceHSQLDB(final String dbPath){this.dbPath = dbPath;}

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Book fromResultSet(final ResultSet rs) throws SQLException{
        Book book = null;
        List<String> tags = new ArrayList<>();
        List<String> genres = new ArrayList<>();

        while(rs.next()){
            if(book == null){
                //getting information
                final int bookID = rs.getInt("BOOK_ID");
                final String bookName = rs.getString("BOOK_NAME");
                final String bookAuthor = rs.getString("BOOK_AUTHOR");
                final String bookDate = rs.getString("BOOK_DATE");
                List<String> tempGenre = new ArrayList<>();
                List<String> tempTag = new ArrayList<>();
                book = new Book(bookID, bookName, bookAuthor, bookDate, tempTag, tempGenre);
            }
        }


    }

    private String[] getTagFromRS(final ResultSet rs) throws SQLException{
        final
    }

    @Override
    public int checkList(ArrayList<Book> list) {
        return 0;
    }

    @Override
    public int checkBook(Book book) {
        return 0;
    }

    @Override
    public int checkCredentials(User user) {
        return 0;
    }

    @Override
    public int upload(Book book, User user) {
        return 0;
    }

    @Override
    public int update(Book book, User user) {
        return 0;
    }

    @Override
    public void delete(Book book, User user) {

    }

    @Override
    public void delete(ArrayList<Book> list, User user) {

    }

    @Override
    public ArrayList<Book> searchTag(String[] tags) {
        return null;
    }

    @Override
    public ArrayList<Book> searchGenre(String[] genres) {
        return null;
    }

    @Override
    public ArrayList<Book> searchAuthor(String author) {
        return null;
    }

    @Override
    public ArrayList<Book> searchName(String bookName) {
        return null;
    }

    @Override
    public ArrayList<Book> search(ArrayList<Book> list) {
        return null;
    }

    @Override
    public ArrayList<Book> search(PreparedStatement statement) {
        return null;
    }

    @Override
    public Book search(Book book) {
        return null;
    }

    @Override
    public ArrayList<Book> getBookList() {
        return null;
    }
}
