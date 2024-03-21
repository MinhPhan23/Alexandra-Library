package com.alexandria_library.data.stub;

import com.alexandria_library.data.IUserPersistent;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.Reader;
import com.alexandria_library.dso.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPersistentStub implements IUserPersistent {
    private static int userID = 1;
    private static int librarianID = 1;

    private ArrayList<User> userList;
    private ArrayList<User> librarianList;
    public UserPersistentStub(){
        userList = new ArrayList<>();

        /*****
         * create 5 users
         */
        Reader user1 = new Reader("Xiang", "123", 1);
        Reader user2 = new Reader("Andrei", "123456", 2);
        Reader user3 = new Reader("Minh", "123456", 3);
        Reader user4 = new Reader("Carlo", "123456", 4);
        Reader user5 = new Reader("alina", "123456", 5);
        userID = 6;

        librarianList = new ArrayList<>();

        /*****
         * create 5 users
         */
        Reader libr1 = new Reader("Xiang", "123", 1);
        Reader libr2 = new Reader("Andrei", "123456", 2);
        Reader libr3 = new Reader("Minh", "123456", 3);
        Reader libr4 = new Reader("Carlo", "123456", 4);
        Reader libr5 = new Reader("alina", "123456", 5);
        librarianID = 6;

        /****
         * add temperature books to some users
         */
        String[] tags1Array = new String[]{"LGBT", "Adult"};
        String[] genres1Array = new String[]{"Romance", "Contemporary", "Historical Fiction"};
        List<String> tags1 = new ArrayList<>(Arrays.asList(tags1Array));
        List<String>genres1 = new ArrayList<>(Arrays.asList(genres1Array));
        Book b1 = new Book(1 , "The Seven Husbands of Evalyn Hugo", "Taylor Jenkins ReuserID", "June 13, 2017", tags1, genres1);

        String[] tags2Array = new String[]{"High School", "Literature"};
        String[] genres2Array = new String[]{"Classics", "Fiction", "Historical Fiction", "Young Adult"};
        List<String>tags2 = new ArrayList<>(Arrays.asList(tags2Array));
        List<String>genres2 = new ArrayList<>(Arrays.asList(genres2Array));
        Book b2 = new Book(2 , "To Kill a Mockingbird", "Harper Lee", "July 11, 1960", tags2, genres2);

        String[] tags3Array = new String[]{"Coming of Age", "Love", "Teen"};
        String[] genres3Array = new String[]{"Realistic Fiction", "Contemporary", "Young Adult"};
        List<String>tags3 = new ArrayList<>(Arrays.asList(tags3Array));
        List<String>genres3 = new ArrayList<>(Arrays.asList(genres3Array));
        Book b3 = new Book(3 , "The Fault in Our Stars", "John Green", "January 10, 2012", tags3, genres3);

        String[] tags4Array = new String[]{"World War II", "Holocaust", "Books About Books"};
        String[] genres4Array = new String[]{"War", "Classics", "Historical Fiction", "Young Adult"};
        List<String>tags4 = new ArrayList<>(Arrays.asList(tags4Array));
        List<String>genres4 = new ArrayList<>(Arrays.asList(genres4Array));
        Book b4 = new Book(4 , "The Book Thief", "Markus Zusak", "September 1, 2005", tags4, genres4);

        String[] tags5Array = new String[]{"Post Apocalyptic", "Survival", "Hunger Games 1"};
        String[] genres5Array = new String[]{"Dystopia", "Science Fiction", "Fantasy", "Young Adult", "Action"};
        List<String>tags5 = new ArrayList<>(Arrays.asList(tags5Array));
        List<String>genres5 = new ArrayList<>(Arrays.asList(genres5Array));
        Book b5 = new Book(5 , "The Hunger Games", "Suzanne Collins", "September 14, 2008", tags5, genres5);

        String[] tags6Array = new String[]{"Spirituality", "Dreams"};
        String[] genres6Array = new String[]{"Fiction", "Fantasy", "Philosophy", "Self Help"};
        List<String>tags6 = new ArrayList<>(Arrays.asList(tags6Array));
        List<String>genres6 = new ArrayList<>(Arrays.asList(genres6Array));
        Book b6 = new Book(6 , "The Alchemist", "Paulo Coelho", "January 1, 1988", tags6, genres6);

        String[] tags7Array = new String[]{"Suspence", "Adventure"};
        String[] genres7Array = new String[]{"Fiction", "Mystery", "Thriller", "Historical Fiction"};
        List<String>tags7 = new ArrayList<>(Arrays.asList(tags7Array));
        List<String>genres7 = new ArrayList<>(Arrays.asList(genres7Array));
        Book b7 = new Book(7 , "The Da Vinci Code", "Dan Brown", "March 18, 2003", tags7, genres7);

        String[] tags8Array = new String[]{"Magical Realism", "Adult", "Mental Health"};
        String[] genres8Array = new String[]{"Fiction", "Fantasy", "Contemporary", "Science Fiction"};
        List<String>tags8 = new ArrayList<>(Arrays.asList(tags8Array));
        List<String>genres8 = new ArrayList<>(Arrays.asList(genres8Array));
        Book b8 = new Book(8 , "The MuserIDnight Library", "Matt Haig", "August 13, 2020", tags8, genres8);

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
        boolean success = false;
        if(findUser(userName) == null){
            User newUser = new User(userName, password, userID);
            userID++;
            success = userList.add(newUser);
        }
        return success;
    }

    public boolean addNewLibrarian(String userName, String password){
        boolean success = false;
        if(findLibrarian(userName) == null){
            User newLibrarian = new User(userName, password, userID);
            librarianID++;
            success = librarianList.add(newLibrarian);
        }
        return success;
    }

    public User findUser(String userName, String password){
        User found = null;
        for(int i = 0; i<userList.size() && found == null; i++){
            User current = userList.get(i);
            if(current.getUserName().equals(userName)){
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

<<<<<<< app/src/main/java/com/alexandria_library/data/stub/UserPersistentStub.java
    
=======
    @Override
    public List<User> getUserSequential() {
        return null;
    }

    @Override
    public void addBookToAllList(Booklist list, User user) {

    }

    @Override
    public void addBookToReadingList(Booklist list, User user) {

    }

    @Override
    public void addBookToFinishedList(Booklist list, User user) {

    }

    @Override
    public void deleteUserAllListBook(Booklist list, User user) {

    }

    @Override
    public void deleteReadingListBook(Booklist list, User user) {

    }

    @Override
    public void deleteFinishedListBook(Booklist list, User user) {

>>>>>>> app/src/main/java/com/alexandria_library/data/stub/UserPersistentStub.java
    public User findLibrarian(String userName) {
        User found = null;
        for(int i = 0; i<librarianList.size() && found == null; i++){
            User current = librarianList.get(i);
            if(current.getUserName().equals(userName)){
                found = current;
            }
        }
        return found;
    }
}
