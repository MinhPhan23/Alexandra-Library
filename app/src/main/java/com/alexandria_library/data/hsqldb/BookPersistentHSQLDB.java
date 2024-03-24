package com.alexandria_library.data.hsqldb;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookPersistentHSQLDB implements IBookPersistent {

    private final String dbPath;
    private static int bookID = 1;
    private static int tagID = 1;
    private static int genreID = 1;
    private static int bookTagID = 1;
    private static int bookGenreID = 1;

    public BookPersistentHSQLDB(final String dbPath){this.dbPath = dbPath;}

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "123");
    }

    private Book fromResultSet(final ResultSet rs) throws SQLException{
        Book book = null;
        List<String> tags = new ArrayList<>();
        List<String> genres = new ArrayList<>();

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
            if(tag != null && !tags.contains(tag)){
                tags.add(tag);
            }
            String genre = rs.getString("GENRE_NAME");
            if(tag != null && !genres.contains(genre)){
                genres.add(genre);
            }
        if(book != null){
            book.setTags(tags);
            book.setGenres(genres);
        }
        return book;
    }

    @Override
    public int checkCredentials(User user){
        return 0;
    }

    @Override
    public boolean upload(Book book, User user) {
        boolean result = false;
        try{
            if(checkCredentials(user) == 0 && duplicateBook(book.getName())<0){
                addBook(book);
                result = true;
            }
            return result;
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    private void addBook(Book newBook) throws SQLException{
        String insert = "INSERT INTO BOOKS(BOOK_ID, BOOK_NAME, BOOK_AUTHOR, BOOK_DATE) VALUES (?, ?, ?, ?)";
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

            // adding new tag or make new relation with tag and book
            for(int i = 0; i<newBook.getTags().size(); i++){
                String currentTag = newBook.getTags().get(i);
                int findTagID = duplicateTag(currentTag);
                if(findTagID < 0){
                    int newTagID = addTag(currentTag);
                    addBookTagRelation(bookID, newTagID);
                }
                else{
                    addBookTagRelation(bookID, findTagID);
                }
            }
            // adding new genre or make new relation with genre and book
            for(int j = 0; j<newBook.getGenres().size(); j++){
                String currentGenre = newBook.getGenres().get(j);
                int findGenreID = duplicateGenre(currentGenre);
                if(findGenreID < 0){
                    int newGenreID = addGenre(currentGenre);
                    addBookGenreRelation(bookID, newGenreID);
                }
                else{
                    addBookGenreRelation(bookID, findGenreID);
                }
            }
            bookID++;
            statement.close();
        }
    }

    private int addTag(String tagName) throws SQLException {
        String insertTag = "INSERT INTO TAGS (TAG_NAME, TAG_ID) VALUES (?, ?)";
        int result = tagID;
        try(final Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(insertTag);

            statement.setString(1, tagName);
            statement.setInt(2, tagID);
            int success = statement.executeUpdate();
            if(success == 0){
                throw new SQLException ("@BookPersistenceHSQLDB.java addTag unsuccessful");
            }
            tagID++;
            statement.close();
        }
        return result;
    }

    private int addGenre(String genreName) throws SQLException{
        String insertGenre = "INSERT INTO GENRES (GENRE_NAME, GENRE_ID) VALUES (?, ?)";
        int result = genreID;
        try(final Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(insertGenre);

            statement.setString(1, genreName);
            statement.setInt(2, genreID);
            int success = statement.executeUpdate();
            if(success == 0){
                throw new SQLException ("@BookPersistenceHSQLDB.java addGenre unsuccessful");
            }
            genreID++;
            statement.close();
        }
        return result;
    }

    private int addBookTagRelation(int bookID, int tagID) throws SQLException{
        String insertBookTag = "INSERT INTO BOOKTAGS(BOOK_ID, TAG_ID, BOOKTAGS_PK) VALUES (?, ?, ?)";
        int result = bookTagID;
        try(final Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(insertBookTag);

            statement.setInt(1, bookID);
            statement.setInt(2, tagID);
            statement.setInt(3, bookTagID);
            int success = statement.executeUpdate();
            if(success == 0){
                throw new SQLException ("@BookPersistenceHSQLDB.java addBookTagRelation unsuccessful");
            }
            bookTagID++;
            statement.close();
        }
        return result;
    }

    private int addBookGenreRelation(int bookID, int genreID) throws SQLException{
        String insertBookGenre = "INSERT INTO BOOKGENRES(BOOK_ID, GENRE_ID, BOOKGENRES_PK) VALUES (?, ?, ?)";
        int result = bookGenreID;
        try(final Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(insertBookGenre);

            statement.setInt(1, bookID);
            statement.setInt(2, genreID);
            statement.setInt(3, bookGenreID);
            int success = statement.executeUpdate();
            if(success == 0){
                throw new SQLException ("@BookPersistenceHSQLDB.java addBookGenreRelation unsuccessful");
            }
            bookGenreID++;
        }
        return result;
    }


    private int duplicateBook (String bookName) throws SQLException{
        String query = "SELECT * FROM BOOKS";
        int findBookID = -1;
        try(final Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("BOOK_ID");
                if(rs.getString("BOOK_NAME").equals(bookName)){
                    findBookID = id;
                }
            }
            rs.close();
            statement.close();
        }
        return findBookID;
    }
    private int duplicateTag (String tagName) throws SQLException {
        String query = "SELECT * FROM TAGS";
        int findTagID = -1;
        try(final Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("TAG_ID");
                if(rs.getString("TAG_NAME").equals(tagName)){
                    findTagID = id;
                }
            }
            rs.close();
            statement.close();
        }
        return findTagID;
    }

    private int duplicateGenre(String genreName) throws SQLException{
        String query = "SELECT * FROM GENRES";
        int findTagID = -1;
        try(final Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("GENRE_ID");
                if(rs.getString("GENRE_NAME").equals(genreName)){
                    findTagID = id;
                }
            }
            rs.close();
            statement.close();
        }
        return findTagID;
    }

    @Override
    public int update(Book book, User user) {
        return 0;
    }

    @Override
    public ArrayList<String> getAllTags(){
        String query = "SELECT * FROM TAGS";
        ArrayList<String> list = new ArrayList<>();
        try(final Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                String getTags = rs.getString("TAG_NAME");
                list.add(getTags);
            }
            rs.close();
            statement.close();
            return list;
        }
        catch (SQLException e){
            throw new PersistenceException(e);
        }
    }

    private ArrayList<String> getAllBookName() {
        // Only select the distinct names of the books
        String query = "SELECT DISTINCT BOOK_NAME FROM BOOKS";
        ArrayList<String> list = new ArrayList<>();
        // Use try-with-resources for better resource management
        try (Connection c = connection();
             PreparedStatement statement = c.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String getName = rs.getString("BOOK_NAME");
                list.add(getName);
            }
            return list;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public ArrayList<String> getAllGenres(){
        String query = "SELECT * FROM GENRES";
        ArrayList<String> list = new ArrayList<>();
        try(final Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                String getGenre = rs.getString("GENRE_NAME");
                list.add(getGenre);
            }
            rs.close();
            statement.close();
            return list;
        }
        catch (SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteLibraryBook(Booklist list, User user) {
        try {
            for(int i = 0; i<list.size(); i++){
                deleteFromLibrary(list.get(i));
            }
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }
    private void deleteFromLibrary(Book book) throws SQLException {
        int bookID = book.getID();
        String query = "DELETE FROM BOOKS WHERE BOOK_ID = ?";
        try(Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            statement.setInt(1, bookID);
            ResultSet rs = statement.executeQuery();
            statement.close();
            rs.close();
        }
    }

    @Override
    public ArrayList<String> searchTagByBook (Book book){
        ArrayList<String> result = new ArrayList<>();
        String query = "SELECT TG.TAG_NAME FROM TAGS TG "+
                        "JOIN BOOKTAGS BT ON TG.TAG_ID = BT.TAG_ID "+
                        "JOIN BOOKS B ON BT.BOOK_ID = B.BOOK_ID " +
                        "WHERE B.BOOK_NAME = ?";
        try(Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, book.getName());
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                String tagName = rs.getString("TAG_NAME");
                result.add(tagName);
            }
            rs.close();

            return result;
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public Booklist getBookList(){
        Booklist books = new Booklist();
        ArrayList<String> nameList = getAllBookName();
        for(int i = 0; i < nameList.size(); i++){
            Book getBook = getEachBooks(nameList.get(i));
            books.add(getBook);
        }
        return books;
    }

    public Book getEachBooks(String require){
        Book result = null;
        String query = "SELECT B.BOOK_ID, B.BOOK_NAME, B.BOOK_AUTHOR, B.BOOK_DATE, " +
                "TG.TAG_NAME, GS.GENRE_NAME FROM BOOKS B "+
                "JOIN BOOKTAGS BT ON B.BOOK_ID = BT.BOOK_ID " +
                "JOIN TAGS TG ON BT.TAG_ID = TG.TAG_ID "+
                "JOIN BOOKGENRES BG ON B.BOOK_ID = BG.BOOK_ID "+
                "JOIN GENRES GS ON BG.GENRE_ID = GS.GENRE_ID " +
                "WHERE B.BOOK_NAME = ?";

        try(Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1, require);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                result = fromResultSet(rs);
            }
            rs.close();
            return result;
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }


/*************************************************************************************************
 * === SEARCH book by multiple requests START ===
 */
    @Override
    public Booklist searchTag(String tagName){
        String query ="SELECT B.BOOK_ID, B.BOOK_NAME, B.BOOK_AUTHOR, B.BOOK_DATE, GS.GENRE_NAME, TG.TAG_NAME FROM BOOKS B "+
                        "JOIN BOOKTAGS BT ON B.BOOK_ID = BT.BOOK_ID "+
                        "JOIN TAGS TG ON BT.TAG_ID = TG.TAG_ID "+
                        "JOIN BOOKGENRES BG ON B.BOOK_ID = BG.BOOK_ID "+
                        "JOIN GENRES GS ON BG.GENRE_ID = GS.GENRE_ID "+
                        "WHERE TG.TAG_NAME = ?";
        try{
            return searchBook(query, tagName);
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public Booklist searchGenre (String genreName){
        String query = "SELECT B.BOOK_ID, B.BOOK_NAME, B.BOOK_AUTHOR, B.BOOK_DATE, GS.GENRE_NAME, TG.TAG_NAME FROM BOOKS B "+
                        "JOIN BOOKTAGS BT ON B.BOOK_ID = BT.BOOK_ID "+
                        "JOIN TAGS TG ON BT.TAG_ID = TG.TAG_ID "+
                        "JOIN BOOKGENRES BG ON B.BOOK_ID = BG.BOOK_ID "+
                        "JOIN GENRES GS ON BG.GENRE_ID = GS.GENRE_ID "+
                        "WHERE GS.GENRE_NAME = ?";
        try {
            return searchBook(query, genreName);
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public Booklist searchAuthor(String author){

        String query = "SELECT B.BOOK_ID, B.BOOK_NAME, B.BOOK_AUTHOR, B.BOOK_DATE, GS.GENRE_NAME, TG.TAG_NAME FROM BOOKS B "+
                        "JOIN BOOKTAGS BT ON B.BOOK_ID = BT.BOOK_ID "+
                        "JOIN TAGS TG ON BT.TAG_ID = TG.TAG_ID "+
                        "JOIN BOOKGENRES BG ON B.BOOK_ID = BG.BOOK_ID "+
                        "JOIN GENRES GS ON BG.GENRE_ID = GS.GENRE_ID "+
                        "WHERE B.BOOK_AUTHOR = ?";

        try {
            return searchBook(query, author);
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public Booklist searchName(String bookName){
        String query = "SELECT B.BOOK_ID, B.BOOK_NAME, B.BOOK_AUTHOR, B.BOOK_DATE, GS.GENRE_NAME, TG.TAG_NAME FROM BOOKS B "+
                        "JOIN BOOKTAGS BT ON B.BOOK_ID = BT.BOOK_ID "+
                        "JOIN TAGS TG ON BT.TAG_ID = TG.TAG_ID "+
                        "JOIN BOOKGENRES BG ON B.BOOK_ID = BG.BOOK_ID "+
                        "JOIN GENRES GS ON BG.GENRE_ID = GS.GENRE_ID "+
                        "WHERE B.BOOK_NAME  = ?";

        try {
            return searchBook(query, bookName);
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    //(PRIVATE) search book for multiple request, (book name, author, genre, tag)
    private Booklist searchBook(String query, String require) throws SQLException{
        Booklist books = new Booklist();
        try(final Connection c = connection()){
            final PreparedStatement statement = c.prepareStatement(query);
            statement.setString(1,require);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Book newBook = fromResultSet(rs);
                if(newBook != null){
                        books.add(newBook);
                }
            }
            rs.close();
            statement.close();
        }
        books = collapseDuplicates(books);
        return books;
    }
/**
 * === SEARCH book by mulitple requests END ===
 *************************************************************************************************/

    private int isUniqueBook(Booklist list, Book book){
        int index = -1;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getID() == book.getID()){
                index = i;
                break;
            }
        }
        return index;
    }

    private Booklist collapseDuplicates(Booklist list){
        Booklist newList = new Booklist();
        if(list.size() == 0) {
            return newList;
        }
        Book currBook = list.get(0).clone();
        int currID = currBook.getID();
        ArrayList<String> tags = new ArrayList<>(currBook.getTags());
        ArrayList<String> genres = new ArrayList<>(currBook.getGenres());
        for (int i = 1; i < list.size(); i++) {
            Book book = list.get(i);
            if (book.getID() == currID) {
                for(String tag : book.getTags()) {
                    if(!tags.contains(tag)) tags.add(tag);
                }
                for(String genre : book.getGenres()) {
                    if(!genres.contains(genre)) genres.add(genre);
                }

            } else {
                currBook.setTags(new ArrayList<>(tags));
                currBook.setGenres(new ArrayList<>(genres));
                newList.add(currBook);

                currBook = book.clone();
                currID = book.getID();
                tags = new ArrayList<>(book.getTags());
                genres = new ArrayList<>(book.getGenres());
            }
        }

        currBook.setTags(new ArrayList<>(tags));
        currBook.setGenres(new ArrayList<>(genres));
        newList.add(currBook);

        return newList;
    }

/**
 * === GET user's list END ===
 *************************************************************************************************/
}
