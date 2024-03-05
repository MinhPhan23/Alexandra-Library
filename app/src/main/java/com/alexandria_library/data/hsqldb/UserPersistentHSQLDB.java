package com.alexandria_library.data.hsqldb;

import com.alexandria_library.data.IUserPersistentHSQLDB;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
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

public class UserPersistentHSQLDB implements IUserPersistentHSQLDB {
    private final String dbPath;
    private static int userID = 1;
    private static int customListID = 1;
    private static int readingListID = 1;
    private static int finishedListID = 1;

    public UserPersistentHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromResultSet(final ResultSet rs) throws SQLException{
        final String userName = rs.getString("USER_NAME");
        final String password = rs.getString("PASSWORD");
        final int userID = rs.getInt("USER_ID");

        return new User(userName, password, userID);
    }

    public List<User> getUserSequential(){
        final List<User> users = new ArrayList<>();
        try(final Connection c = connection()){
            final Statement statement = c.createStatement();
            final ResultSet rs = statement.executeQuery("SELECT * FROM USERS");
            while(rs.next()){
                final User currentUser = fromResultSet(rs);
                users.add(currentUser);
            }
            rs.close();
            statement.close();
            return users;
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public boolean addNewUser(String userName, String password){
        boolean result = false;
        try(final Connection c = connection()){
            final PreparedStatement statement = c.prepareStatement("INSERT INTO USERS(USER_ID, USER_NAME, PASSWORD) VALUES (?, ?, ?)");
            statement.setInt(1, userID);
            statement.setString(2, userName);
            statement.setString(3, password);
            int affectedRow = statement.executeUpdate();

            if(affectedRow > 0){
                result = true;
                userID++;
            }
            return result;
        }
        catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public User findUser(String userName, String password){
        User found = null;
        try(final Connection c = connection()){
            final PreparedStatement statement = c.prepareStatement("SELECT * FROM USERS WHERE USER_NAME = ? AND PASSWORD = ?");
            statement.setString(1, userName);
            statement.setString(2, password);

            final ResultSet rs =statement.executeQuery();
            if(rs.next()){
                found =fromResultSet(rs);
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
    public User findUser(String userName){
        User found = null;
        try(final Connection c = connection()){
            final PreparedStatement statement = c.prepareStatement("SELECT * FROM USERS WHERE USER_NAME = ?");
            statement.setString(1, userName);

            final ResultSet rs =statement.executeQuery();
            if(rs.next()){
                found =fromResultSet(rs);
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
    public void addBookToCustomList(Booklist list, User user){
        final String addToCustomListQuery =  "INSERT INTO CUSTOMLIST(BOOK_ID, USER_ID, CUSTOMLIST_PK) VALUES (?, ?, ?) ";
        try{
            for(int i = 0; i<list.size(); i++){
                boolean checkEachAdd = addBookToUserList(addToCustomListQuery, list.get(i), user, customListID);

                if(checkEachAdd)
                    customListID++;
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
    public void deleteUserCustomListBook(Booklist list, User user){
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
