package com.alexandria_library.data.hsqldb;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistenceHSQLDB;
import com.alexandria_library.data.IUserPersistenceHSQLDB;
import com.alexandria_library.dso.Book;
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

public class UserPersistenceHSQLDB implements IUserPersistenceHSQLDB {
    private final String dbPath;
    private static int userID = 1;
    private IBookPersistenceHSQLDB bookPersistenceHSQLDB = Service.getBookPersistenceHSQLDB();

    public UserPersistenceHSQLDB(final String dbPath){this.dbPath = dbPath;}

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromResultSet(final ResultSet rs) throws SQLException{
        final String userName = rs.getString("USER_NAME");
        final String password = rs.getString("PASSWORD");
        final int userID = rs.getInt("USER_ID");

        return new User(userName, password, userID);
    }

    public List<User> getUserSequential() throws SQLException{
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
        }
        return users;
    }

    @Override
    public boolean addNewUser(String userName, String password) throws SQLException{
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
        }
        return result;
    }

    @Override
    public User findUser(String userName, String password) throws SQLException{
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
        }
        return found;
    }

    @Override
    public User findUser(String userName) throws SQLException{
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
        }
        return found;
    }

//
//    public boolean addBookToCustomList(ArrayList<Book> list, User user) throws SQLException{
//
//    }
//    public boolean addBookToInProgressList(ArrayList<Book> list, User user) throws SQLException{
//
//    }
//    public boolean addBookToFinishedList(ArrayList<Book> list, User user) throws SQLException{
//
//    }










    @Override
    public void deleteUserAllListBook(ArrayList<Book> list, User user) throws SQLException{
        if(user instanceof Reader){
            Reader reader = (Reader) user;
            for (int i = 0; i<list.size(); i++){
                deleteFromAllList(list.get(i), reader);
            }
        }
    }
    @Override
    public void deleteInProgressListBook(ArrayList<Book> list, User user) throws SQLException{
        if(user instanceof Reader){
            Reader reader = (Reader) user;
            for (int i = 0; i<list.size(); i++){
                deleteFromInProgressList(list.get(i), reader);
            }
        }

    }
    @Override
    public void deleteFinishedListBook(ArrayList<Book> list, User user) throws SQLException{
        if(user instanceof Reader){
            Reader reader = (Reader) user;
            for (int i = 0; i<list.size(); i++){
                deleteFromFinishedList(list.get(i), reader);
            }
        }
    }


    private void deleteFromAllList(Book book, Reader reader) throws SQLException {
        int bookID = book.getID();
        int readerID = reader.getId();
        String query = "DELETE FROM CUSTOMLIST WHERE BOOK_ID = ? AND USER_ID = ?";
        try(Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            statement.setInt(1, bookID);
            statement.setInt(2, readerID);
            ResultSet rs = statement.executeQuery();
            statement.close();
            rs.close();
        }
    }
    private void deleteFromInProgressList(Book book, Reader reader) throws SQLException {
        int bookID = book.getID();
        int readerID = reader.getId();
        String query = "DELETE FROM READINGLIST WHERE BOOK_ID = ? AND USER_ID = ?";
        try(Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            statement.setInt(1, bookID);
            statement.setInt(2, readerID);
            ResultSet rs = statement.executeQuery();
            statement.close();
            rs.close();
        }
    }
    private void deleteFromFinishedList(Book book, Reader reader) throws SQLException {
        int bookID = book.getID();
        int readerID = reader.getId();
        String query = "DELETE FROM FINISHEDLIST WHERE BOOK_ID = ? AND USER_ID = ?";
        try(Connection c = connection()){
            PreparedStatement statement = c.prepareStatement(query);
            statement.setInt(1, bookID);
            statement.setInt(2, readerID);
            ResultSet rs = statement.executeQuery();
            statement.close();
            rs.close();
        }
    }

}
