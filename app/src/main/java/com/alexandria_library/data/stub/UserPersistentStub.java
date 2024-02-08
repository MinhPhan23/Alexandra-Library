package com.alexandria_library.data.stub;

import com.alexandria_library.data.IUser;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.User;

import java.util.ArrayList;

public class UserPersistentStub implements IUser {

    private ArrayList<User> userList;
    public UserPersistentStub(){
        userList = new ArrayList<>();

        /*****
         * create 5 users
         */
        User user1 = new User("Xiang", "123");
        User user2 = new User("Andrei", "123456");
        User user3 = new User("Minh", "123456");
        User user4 = new User("Carlo", "123456");
        User user5 = new User("alina", "123456");

        /****
         * add temperature books to some users
         */
        String tags1[] = new String[]{"LGBT", "Adult"};
        String genres1[] = new String[]{"Romance", "Contemporary", "Historical Fiction"};
        Book b1 = new Book(1 , "The Seven Husbands of Evalyn Hugo", "Taylor Jenkins Reid", "June 13. 2017", tags1, genres1);


        String tags2[] = new String[]{"High School", "Literature"};
        String genres2[] = new String[]{"Classics", "Fiction", "Historical Fiction", "Young Adult"};
        Book b2 = new Book(2 , "To Kill a Mockingbird", "Harper Lee", "July 11, 1960", tags2, genres2);

        String tags3[] = new String[]{"Coming of Age", "Love", "Teen"};
        String genres3[] = new String[]{"Realistic Fiction", "Contemporary", "Young Adult"};
        Book b3 = new Book(3 , "The Fault in Our Stars", "John Green", "January 10, 2012", tags3, genres3);

        String tags4[] = new String[]{"World War II", "Holocaust", "Books About Books"};
        String genres4[] = new String[]{"War", "Classics", "Historical Fiction", "Young Adult"};
        Book b4 = new Book(4 , "The Book Thief", "Markus Zusak", "September 1, 2005", tags4, genres4);

        String tags5[] = new String[]{"Post Apocalyptic", "Survival", "Hunger Games 1"};
        String genres5[] = new String[]{"Dystopia", "Science Fiction", "Fantasy", "Young Adult", "Action"};
        Book b5 = new Book(5 , "The Hunger Games", "Suzanne Collins", "September 14, 2008", tags5, genres5);

        String tags6[] = new String[]{"Spirituality", "Dreams"};
        String genres6[] = new String[]{"Fiction", "Fantasy", "Philosophy", "Self Help"};
        Book b6 = new Book(6 , "The Alchemist", "Paulo Coelho", "January 1, 1988", tags6, genres6);

        String tags7[] = new String[]{"Suspence", "Adventure"};
        String genres7[] = new String[]{"Fiction", "Mystery", "Thriller", "Historical Fiction"};
        Book b7 = new Book(7 , "The Da Vinci Code", "Dan Brown", "March 18, 2003", tags7, genres7);

        String tags8[] = new String[]{"Magical Realism", "Adult", "Mental Health"};
        String genres8[] = new String[]{"Fiction", "Fantasy", "Contemporary", "Science Fiction"};
        Book b8 = new Book(8 , "The Midnight Library", "Matt Haig", "August 13, 2020", tags8, genres8);

        /****
         * add all books to user1's all book list
         */
        user1.addBookToAll(b1);
        user1.addBookToAll(b2);
        user1.addBookToAll(b3);
        user1.addBookToAll(b4);
        user1.addBookToAll(b5);
        user1.addBookToAll(b6);
        user1.addBookToAll(b7);
        user1.addBookToAll(b8);
        user1.addBookToFinished(b1);
        user1.addBookToFinished(b2);
        user1.addBookToFinished(b3);
        user1.addBookToFinished(b4);
        user1.addBookToFinished(b5);
        user1.addBookToFinished(b6);
        user1.addBookToFinished(b7);
        user1.addBookToInProgress(b8);
        user1.addBookToInProgress(b7);
        user1.addBookToInProgress(b6);
        user1.addBookToInProgress(b5);
        user1.addBookToInProgress(b4);
        user1.addBookToInProgress(b3);
        user1.addBookToInProgress(b2);



        user2.addBookToAll(b1);
        user2.addBookToAll(b2);
        user2.addBookToAll(b3);
        user2.addBookToAll(b4);
        user2.addBookToAll(b5);
        user2.addBookToAll(b6);
        user2.addBookToAll(b7);
        user2.addBookToAll(b8);
        user2.addBookToFinished(b3);
        user2.addBookToFinished(b4);
        user2.addBookToFinished(b5);
        user2.addBookToFinished(b6);
        user2.addBookToFinished(b7);
        user2.addBookToInProgress(b6);
        user2.addBookToInProgress(b5);
        user2.addBookToInProgress(b4);
        user2.addBookToInProgress(b3);
        user2.addBookToInProgress(b2);


        /***
         * add users to user persistent
         */
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
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

    public User findUser(String userName) {
        User found = null;
        for(int i = 0; i<userList.size() && found == null; i++){
            User current = userList.get(i);
            if(current.getUserName().equals(userName)){
                found = current;
            }
        }
        return found;
    }
}
