package com.alexandria_library.data.stub;

import com.alexandria_library.data.IUser;
import com.alexandria_library.dso.User;

import java.util.ArrayList;

public class UserStub implements IUser {

    private ArrayList<User> userList;
    public UserStub (){
        userList = new ArrayList<>();

        userList.add(new User("Xiang Shi", "123456"));
        userList.add(new User("Andrei", "123456"));
        userList.add(new User("Minh", "123456"));
        userList.add(new User("Carlo", "123456"));
        userList.add(new User("alina", "123456"));
    }


    public boolean addNewUser(String userName, String password){
        User newUser = new User(userName, password);
        return userList.add(newUser);
    }

    public User findUser(String userName, String password){
        User found = null;
        for(int i = 0; i<userList.size() && found == null; i++){
            User current = userList.get(i);
            if(current.getUserName().equals(userName) && current.getPassword().equals(password)){
                found = current;
            }
        }
        return found;
    }
}
