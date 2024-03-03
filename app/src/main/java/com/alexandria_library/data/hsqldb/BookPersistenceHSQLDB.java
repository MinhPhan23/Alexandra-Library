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
    private static int bookID = 1;
    private static int tagID = 0;
    private static int genreID = 0;

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

            String tag = rs.getString("TAG_NAME");
            if(tag != null){
                tags.add(tag);
            }
            String genre = rs.getString("GENRE_NAME");
            if(tag != null){
                genres.add(genre);
            }
        }
        if(book != null){
            book.setTags(tags);
            book.setGenres(genres);
        }
        return book;
    }


    @Override
    public int checkList(ArrayList<Book> list){
        int status = 0;
        if(list != null) {
            for (int i = 0; i < list.size(); i++) {
                status += checkBook(list.get(i));
            }
        }
        else{
            status = -1;
        }
        return status;
    }

    @Override
    public int checkBook(Book book){
        int status = 0;
        if(book == null){
            status = -1;
        }
        else if(book.getName() == null || book.getName().equals("")){
            status = 1;
        }
        else if(book.getAuthor() == null || book.getAuthor().equals("")){
            status = 2;
        }
        else if(book.getTags() == null){
            status = 3;
        }
        else if(book.getGenres() == null){
            status = 4;
        }
        else if(book.getDate() == null){
            status = 5;
        }
        return status;
    }

    @Override
    public int checkCredentials(User user){
        return 0;
    }

    @Override
    public int upload(Book book, User user) {
        return 0;
    }

    private void addBook(Book newBook) throws SQLException{
        String insert = "INSERT INTO BOOKS(BOOK_ID, BOOK_NAME, BOOK_AUTHOR, BOOK_DATE) VALUES (?, ?, ?)";
        try(final Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(insert);

            statement.setInt(1, bookID);
            statement.setString(2, newBook.getName());
            statement.setString(3, newBook.getAuthor());
            statement.setString(4, newBook.getDate());
            int success = statement.executeUpdate();

            if(success == 0){
                throw new SQLException ("@BookPersistenceHSQLDB.java addBook unsuccessful");
            }
            else{
                bookID++;
            }
        }
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
    public ArrayList<Book> searchTag(List<String> tags) {
        return null;
    }

    public ArrayList<Book> searchTag(String tagName, int tagID) throws SQLException{
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT B.BOOK_ID, B.BOOK_NAME, B.BOOK_AUTHOR, B.BOOK_DATE, TG.TAG_NAME, GS.GENRE_NAME "+
                "FROM BOOKS B "+
                "JOIN BOOKTAGS BT ON B.BOOK_ID = BT.BOOK_ID "+
                "JOIN TAGS TG ON BT.TAG_ID = TG.TAG_ID " +
                "JOIN BOOKGENRES BG ON B.BOOK_ID = BG.BOOK_ID "+
                "JOIN GENRES GS ON BG.GENRE_ID = GS.GENRE_ID "+
                "WHERE TG.TAG_NAME = ?";

        try(Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Book newBook = fromResultSet(rs);
                if(newBook != null){
                    books.add(newBook);
                }
            }
        }
        return books;
    }

    @Override
    public ArrayList<Book> searchGenre(List<String> genres) {
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
