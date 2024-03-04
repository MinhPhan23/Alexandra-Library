package com.alexandria_library.data.hsqldb;

import com.alexandria_library.data.IUserPersistenceHSQLDB;
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

}
