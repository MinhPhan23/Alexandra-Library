package com.alexandria_library.data.hsqldb;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.IUserPersistent;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.IReader;
import com.alexandria_library.dso.Librarian;
import com.alexandria_library.dso.Reader;
import com.alexandria_library.dso.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserPersistentHSQLDB implements IUserPersistent {
    private final String dbPath;

    private static final IBookPersistent bookData = Service.getBookPersistent();
    private static int userID = 6; //start with 6 because group members are default \
    private static int librarianID = 6; //start with 6 because group members are default users
    private static int allListID = 19; //start with 19 because group members already have booklist setup
    private static int readingListID = 18; //start with 18 because group members already have booklist setup
    private static int finishedListID = 18; //start with 18 because group members already have booklist setup

    public UserPersistentHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "123");
    }

    /**
     * Create a new User DSO from the database result
     * @param rs ResultSet object
     * @param type type of user, 0 is Reader, 1 is Librarian
     * @return user object
     * @throws SQLException in case database error
     */
    private User fromResultSet(final ResultSet rs, String type) throws SQLException{
        final String userName = rs.getString("USER_NAME");
        final String password = rs.getString("PASSWORD");
        final int userID = rs.getInt("USER_ID");

        User user = null;
        if (type.equalsIgnoreCase("reader")) {
            user = new Reader(userName, password, userID);
            getAllList((Reader) user);
            getFinishedList((Reader) user);
            getReadingList((Reader) user);
        }
        else if (type.equalsIgnoreCase("librarian")) {
            user = new Librarian(userName, password, userID);
        }
        return user;
    }

    private ArrayList<User> getAllUser(){
        ArrayList<User> userList = new ArrayList<>();
        try(final Connection c = connection()){
            String sql = "SELECT* FROM USERS";
            PreparedStatement statement = c.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                User user = fromResultSet(rs, "reader");
                userList.add(user);
            }
            return userList;
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    private ArrayList<User> getAllLibrarian(){
        ArrayList<User> userArrayList = new ArrayList<>();
        try(final Connection c = connection()){
            String sql = "SELECT* FROM LIBRARIANS";
            PreparedStatement statement = c.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                User user = fromResultSet(rs, "librarian");
                userArrayList.add(user);
            }
            return userArrayList;
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    private void getAllList(Reader user) {
        try(final Connection c = connection()){
            String sql = "SELECT B.BOOK_NAME FROM BOOKS B " +
                    "JOIN CUSTOMLIST CL ON B.BOOK_ID = CL.BOOK_ID " +
                    "WHERE CL.USER_ID = ?";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String bookName = rs.getString("BOOK_NAME");
                Book book = bookData.getEachBooks(bookName);
                if (book!=null) {
                    user.getAllBooksList().add(book);
                }
            }
            rs.close();
            statement.close();
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    private void getReadingList(Reader user) {
        try(final Connection c = connection()){
            String sql = "SELECT B.BOOK_NAME FROM BOOKS B " +
                    "JOIN READINGLIST RD ON B.BOOK_ID = RD.BOOK_ID " +
                    "WHERE RD.USER_ID = ?";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String bookName = rs.getString("BOOK_NAME");
                Book book = bookData.getEachBooks(bookName);
                if (book!=null) {
                    user.getInProgressList().add(book);
                }
            }
            rs.close();
            statement.close();
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    private void getFinishedList(Reader user) {
        try(final Connection c = connection()){
            String sql = "SELECT B.BOOK_NAME FROM BOOKS B " +
                    "JOIN FINISHEDLIST CL ON B.BOOK_ID = CL.BOOK_ID " +
                    "WHERE CL.USER_ID = ?";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String bookName = rs.getString("BOOK_NAME");
                Book book = bookData.getEachBooks(bookName);
                if (book!=null) {
                    user.getFinishedList().add(book);
                }
            }
            rs.close();
            statement.close();
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public boolean addNewUser(String userName, String password){
        boolean result = false;
        userID = getAllUser().size()+1;
        try(final Connection c = connection()){
            final PreparedStatement statement = c.prepareStatement("INSERT INTO USERS(USER_ID, USER_NAME, PASSWORD) VALUES (?, ?, ?)");
            statement.setInt(1, userID);
            statement.setString(2, userName);
            statement.setString(3, password);
            int affectedRow = statement.executeUpdate();

            if(affectedRow > 0){
                result = true;
            }
            return result;
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public boolean addNewLibrarian(String userName, String password){
        boolean result = false;
        librarianID = getAllLibrarian().size()+1;
        try(final Connection c = connection()){
            final PreparedStatement statement = c.prepareStatement("INSERT INTO LIBRARIANS(USER_ID, USER_NAME, PASSWORD) VALUES (?, ?, ?)");
            statement.setInt(1, librarianID);
            statement.setString(2, userName);
            statement.setString(3, password);
            int affectedRow = statement.executeUpdate();

            if(affectedRow > 0){
                result = true;
            }
            return result;
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public User findUser(String userName){
        User found = null;
        try(final Connection c = connection()){
            final PreparedStatement statement = c.prepareStatement("SELECT * FROM USERS WHERE USER_NAME = ?");
            statement.setString(1, userName);

            final ResultSet rs =statement.executeQuery();
            if(rs.next()){
                found = fromResultSet(rs, "reader");
            }
            rs.close();
            statement.close();
            return found;
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public User findLibrarian(String userName){
        User found = null;
        try(final Connection c = connection()){
            final PreparedStatement statement = c.prepareStatement("SELECT * FROM LIBRARIANS WHERE USER_NAME = ?");
            statement.setString(1, userName);

            final ResultSet rs =statement.executeQuery();
            if(rs.next()){
                found =fromResultSet(rs, "librarian");
            }
            rs.close();
            statement.close();
            return found;
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }


/*******
 * === Adding book to specific user' list Implement Start ===
 * Part for adding book to specific user's list (reading list, finished list, customer list)
 *******/
    //add book to custom list
    @Override
    public void addBookToAllList(Booklist list, User user){
        final String addToCustomListQuery =  "INSERT INTO CUSTOMLIST(BOOK_ID, USER_ID, CUSTOMLIST_PK) VALUES (?, ?, ?) ";
        try{
            for(int i = 0; i<list.size(); i++){
                boolean checkEachAdd = addBookToUserList(addToCustomListQuery, list.get(i), user, allListID);

                if(checkEachAdd)
                    allListID++;
            }
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }
    //add book to reading list
    @Override
    public void addBookToReadingList(Booklist list, User user){
        final String addToReadingQuery = "INSERT INTO READINGLIST(BOOK_ID, USER_ID, READINGLIST_PK) VALUES(?, ?, ?)";
        try {
            for(int i = 0; i<list.size(); i++){
                boolean checkEachAdd = addBookToUserList(addToReadingQuery, list.get(i), user, readingListID);

                if(checkEachAdd)
                    readingListID++;
            }
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }
    //add book to finished list
    @Override
    public void addBookToFinishedList(Booklist list, User user){
        final String addToFinishedQuery = "INSERT INTO FINISHEDLIST(BOOK_ID, USER_ID, FINISHEDLIST_PK) VALUES (?, ?, ?)";
        try {
            for (int i = 0; i<list.size(); i++){
                boolean checkEachAdd = addBookToUserList(addToFinishedQuery, list.get(i), user, finishedListID);

                if(checkEachAdd)
                    finishedListID++;
            }
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }
    //(PRIVATE) add book to specific user's list
    private boolean addBookToUserList(String query, Book book, User user, int id) throws SQLException{
        boolean added = false;
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement(query);
            st.setInt(1, book.getID());
            st.setInt(2, user.getId());
            st.setInt(3, id);
            int addAffected = st.executeUpdate();
            if(addAffected > 0){
                added = true;
            }
        }
        return added;
    }
/*******
 * === Adding book to specific user' list Implement End ===
 *******/



/*******
 * === DELETE START ===
 * delete book from specific user's list
 */
    //delete book from user's custom list
    @Override
    public void deleteUserAllListBook(Booklist list, User user){
        String query = "DELETE FROM CUSTOMLIST WHERE BOOK_ID = ? AND USER_ID = ?";
        try {
            if(user instanceof Reader){
                Reader reader = (Reader) user;
                for (int i = 0; i<list.size(); i++){
                    deleteFromList(query, list.get(i), reader);
                }
            }
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }
    //delete book from user's reading list
    @Override
    public void deleteReadingListBook(Booklist list, User user){
        String query = "DELETE FROM READINGLIST WHERE BOOK_ID = ? AND USER_ID = ?";
        try {
            if(user instanceof Reader){
                Reader reader = (Reader) user;
                for (int i = 0; i<list.size(); i++){
                    deleteFromList(query, list.get(i), reader);
                }
            }
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }
    //delete book from user's finished list
    @Override
    public void deleteFinishedListBook(Booklist list, User user){
        String query = "DELETE FROM FINISHEDLIST WHERE BOOK_ID = ? AND USER_ID = ?";
        try {
            if(user instanceof Reader){
                Reader reader = (Reader) user;
                for (int i = 0; i<list.size(); i++){
                    deleteFromList(query, list.get(i), reader);
                }
            }
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }
    //(PRIVATE) delete book from specific user's list
    private void deleteFromList(String query, Book book, Reader reader) throws SQLException{
        int bookID = book.getID();
        int readerID = reader.getId();
        try(final Connection c = connection()){
            final PreparedStatement statement = c.prepareStatement(query);
            statement.setInt(1, bookID);
            statement.setInt(2, readerID);
            statement.executeUpdate();
        }
    }
/********
 * === DELETE END ===
 ********/
}
