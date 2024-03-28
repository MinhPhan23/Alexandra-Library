package com.alexandria_library.data.hsqldb;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.IUser;
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
    private static int bookID = 5;
    private static int tagID = 13;
    private static int genreID = 12;
    private static int bookTagID = 13;
    private static int bookGenreID = 19;

    public BookPersistentHSQLDB(final String dbPath){this.dbPath = dbPath;}

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "123");
    }

    private Book fromResultSet(final ResultSet rs) throws SQLException {
        Book book = null;
        List<String> tags = new ArrayList<>();
        List<String> genres = new ArrayList<>();
        boolean isFirstRow = true;

        while (rs.next()) {
            if (isFirstRow) {
                final int bookID = rs.getInt("BOOK_ID");
                final String bookName = rs.getString("BOOK_NAME");
                final String bookAuthor = rs.getString("BOOK_AUTHOR");
                final String bookDate = rs.getString("BOOK_DATE");

                book = new Book(bookID, bookName, bookAuthor, bookDate, new ArrayList<>(), new ArrayList<>());
                isFirstRow = false;
            }

            String tag = rs.getString("TAG_NAME");
            if (tag != null && !tags.contains(tag)) {
                tags.add(tag);
            }
            String genre = rs.getString("GENRE_NAME");
            if (genre != null && !genres.contains(genre)) {
                genres.add(genre);
            }
        }
        if (book != null) {
            book.setTags(tags);
            book.setGenres(genres);
        }

        return book;
    }


    private int checkCredentials(IUser user){
        return 0;
    }

    @Override
    public boolean upload(Book book, IUser user) {
        boolean result = false;
        try{
            if(checkCredentials(user) == 0 && duplicateBook(book.getName())<0){

                ArrayList<Integer> list = new ArrayList<>();
                Booklist bookList = getBookList();
                for(int i = 0; i<bookList.size(); i++){
                    list.add(bookList.get(i).getID());
                }
                while(list.contains(bookID)){
                    bookID++;
                }

                book.setID(bookID);
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

            statement.setInt(1, newBook.getID());
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
                    addBookTagRelation(newBook.getID(), newTagID);
                }
                else{
                    addBookTagRelation(newBook.getID(), findTagID);
                }
            }
            // adding new genre or make new relation with genre and book
            for(int j = 0; j<newBook.getGenres().size(); j++){
                String currentGenre = newBook.getGenres().get(j);
                int findGenreID = duplicateGenre(currentGenre);
                if(findGenreID < 0){
                    int newGenreID = addGenre(currentGenre);
                    addBookGenreRelation(newBook.getID(), newGenreID);
                }
                else{
                    addBookGenreRelation(newBook.getID(), findGenreID);
                }
            }
            bookID++;
            statement.close();
        }
    }

    private int addTag(String tagName) throws SQLException {
        String insertTag = "INSERT INTO TAGS (TAG_NAME, TAG_ID) VALUES (?, ?)";
//        int result = getAllTags().size()+1;
//
//        if(tagID < result)
//            tagID = result;
//        else
//            tagID++;
        ArrayList<Integer> allTagID = getAllTagID();
        while(allTagID.contains(tagID)){
            tagID++;
        }
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
//        int result = getAllGenres().size()+1;
//
//        if(genreID < result)
//            genreID = result;
//        else
//            genreID++;

        ArrayList<Integer> allGenreID = getAllGenreID();
        while(allGenreID.contains(genreID)){
            genreID++;
        }
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
//        int result = getTagsCount()+1;
//        if(bookTagID < result)
//            bookTagID = result;
//        else
//            bookTagID++;

        ArrayList<Integer> allbookTagID = bookTagsCount();
        while(allbookTagID.contains(bookTagID)){
            bookTagID++;
        }
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
//        int result = getGenresCount()+1;
//        if(bookGenreID < result)
//            bookGenreID = result;
//        else
//            bookGenreID++;

        ArrayList<Integer> allBookGenreID = bookGenresCount();
        while(allBookGenreID.contains(bookGenreID)){
            bookGenreID++;
        }
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
            statement.close();
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

    private ArrayList<Integer> getAllTagID(){
        String query = "SELECT TAG_ID FROM TAGS";
        ArrayList<Integer> list = new ArrayList<>();
        try(final Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int getTags = rs.getInt("TAG_ID");
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

    private ArrayList<Integer> getAllGenreID(){
        String query = "SELECT GENRE_ID FROM GENRES";
        ArrayList<Integer> list = new ArrayList<>();
        try(final Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int getGenres = rs.getInt("GENRE_ID");
                list.add(getGenres);
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
            int success = statement.executeUpdate();

            if(success == 0){
                throw new SQLException ("@BookPersistenceHSQLDB.java delete book unsuccessful");
            }
            statement.close();
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


            result = fromResultSet(rs);

            rs.close();
            return result;
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

//    private int getTagsCount(){
//        int count = 0;
//        ArrayList<String> list = getAllTags();
//        for(int i = 0; i<list.size(); i++){
//            count+= bookTagsCount(list.get(i));
//        }
//        return count;
//    }
//
//    private int getGenresCount(){
//        int count = 0;
//        ArrayList<String> list = getAllGenres();
//        for(int i = 0; i<list.size(); i++){
//            count+= bookGenresCount(list.get(i));
//        }
//        return count;
//    }

    public ArrayList<Integer> bookTagsCount(){
        ArrayList<Integer> list = new ArrayList<>();
        String query = "SELECT BOOKTAGS_PK FROM BOOKTAGS ";
        try(Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("BOOKTAGS_PK");
                list.add(id);
            }
            rs.close();
            statement.close();
            return list;
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    public ArrayList<Integer> bookGenresCount(){
        ArrayList<Integer> list = new ArrayList<>();
        String query = "SELECT BOOKGENRES_PK FROM BOOKGENRES ";

        try(Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("BOOKGENRES_PK");
                list.add(id);
            }
            rs.close();
            statement.close();
            return list;
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
