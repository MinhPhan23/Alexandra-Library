package com.alexandria_library.data.stub;

import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.IUser;
import com.alexandria_library.dso.Librarian;
import com.alexandria_library.dso.User;
import com.alexandria_library.dso.Booklist;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookPersistentInterStub implements IBookPersistent {

    private final Booklist bookList = new Booklist();

    public BookPersistentInterStub(){
        String[] tags1Array = new String[]{"LGBT", "Adult"};
        String[] genres1Array = new String[]{"Romance", "Contemporary", "Historical Fiction"};
        List<String>tags1 = new ArrayList<>(Arrays.asList(tags1Array));
        List<String>genres1 = new ArrayList<>(Arrays.asList(genres1Array));
        Book b1 = new Book(1 , "The Seven Husbands of Evalyn Hugo", "Taylor Jenkins Reid", "June 13, 2017", tags1, genres1);
        addBook(bookList, b1);

        String[] tags2Array = new String[]{"High School", "Literature"};
        String[] genres2Array = new String[]{"Classics", "Fiction", "Historical Fiction", "Young Adult"};
        List<String>tags2 = new ArrayList<>(Arrays.asList(tags2Array));
        List<String>genres2 = new ArrayList<>(Arrays.asList(genres2Array));
        Book b2 = new Book(2 , "To Kill a Mockingbird", "Harper Lee", "July 11, 1960", tags2, genres2);
        addBook(bookList, b2);

        String[] tags3Array = new String[]{"Coming of Age", "Love", "Teen"};
        String[] genres3Array = new String[]{"Realistic Fiction", "Contemporary", "Young Adult"};
        List<String>tags3 = new ArrayList<>(Arrays.asList(tags3Array));
        List<String>genres3 = new ArrayList<>(Arrays.asList(genres3Array));
        Book b3 = new Book(3 , "The Fault in Our Stars", "John Green", "January 10, 2012", tags3, genres3);
        addBook(bookList, b3);

        String[] tags4Array = new String[]{"World War II", "Holocaust", "Books About Books"};
        String[] genres4Array = new String[]{"War", "Classics", "Historical Fiction", "Young Adult"};
        List<String>tags4 = new ArrayList<>(Arrays.asList(tags4Array));
        List<String>genres4 = new ArrayList<>(Arrays.asList(genres4Array));
        Book b4 = new Book(4 , "The Book Thief", "Markus Zusak", "September 1, 2005", tags4, genres4);
        addBook(bookList, b4);

        String[] tags5Array = new String[]{"Post Apocalyptic", "Survival", "Hunger Games 1"};
        String[] genres5Array = new String[]{"Dystopia", "Science Fiction", "Fantasy", "Young Adult", "Action"};
        List<String>tags5 = new ArrayList<>(Arrays.asList(tags5Array));
        List<String>genres5 = new ArrayList<>(Arrays.asList(genres5Array));
        Book b5 = new Book(5 , "The Hunger Games", "Suzanne Collins", "September 14, 2008", tags5, genres5);
        addBook(bookList, b5);

        String[] tags6Array = new String[]{"Spirituality", "Dreams"};
        String[] genres6Array = new String[]{"Fiction", "Fantasy", "Philosophy", "Self Help"};
        List<String>tags6 = new ArrayList<>(Arrays.asList(tags6Array));
        List<String>genres6 = new ArrayList<>(Arrays.asList(genres6Array));
        Book b6 = new Book(6 , "The Alchemist", "Paulo Coelho", "January 1, 1988", tags6, genres6);
        addBook(bookList, b6);

        String[] tags7Array = new String[]{"Suspence", "Adventure"};
        String[] genres7Array = new String[]{"Fiction", "Mystery", "Thriller", "Historical Fiction"};
        List<String>tags7 = new ArrayList<>(Arrays.asList(tags7Array));
        List<String>genres7 = new ArrayList<>(Arrays.asList(genres7Array));
        Book b7 = new Book(7 , "The Da Vinci Code", "Dan Brown", "March 18, 2003", tags7, genres7);
        addBook(bookList, b7);

        String[] tags8Array = new String[]{"Magical Realism", "Adult", "Mental Health"};
        String[] genres8Array = new String[]{"Fiction", "Fantasy", "Contemporary", "Science Fiction"};
        List<String>tags8 = new ArrayList<>(Arrays.asList(tags8Array));
        List<String>genres8 = new ArrayList<>(Arrays.asList(genres8Array));
        Book b8 = new Book(8 , "The Midnight Library", "Matt Haig", "August 13, 2020", tags8, genres8);
        addBook(bookList, b8);

        String[] tags9Array = new String[]{"Adult", "School"};
        String[] genres9Array = new String[]{"Historical Fiction", "Contemporary", "Adult Fiction"};
        List<String>tags9 = new ArrayList<>(Arrays.asList(tags9Array));
        List<String>genres9 = new ArrayList<>(Arrays.asList(genres9Array));
        Book b9 = new Book(9 , "The Kite Runner", "Khaled Hosseini", "May 29, 2003", tags9, genres9);
        addBook(bookList, b9);

        String[] tags10Array = new String[]{"Post Apocalyptic", "Teen", "Hunger Games 2"};
        String[] genres10Array = new String[]{"Young Adult", "Dystopia", "Science Fiction", "Romance", "Fantasy"};
        List<String>tags10 = new ArrayList<>(Arrays.asList(tags10Array));
        List<String>genres10 = new ArrayList<>(Arrays.asList(genres10Array));
        Book b10 = new Book(10 , "Catching Fire", "Suzanne Collins", "September 1, 2009", tags10, genres10);
        addBook(bookList, b10);

        String[] tags11Array = new String[]{"Adult", "Book Club"};
        String[] genres11Array = new String[]{"Historical Fiction", "Contemporary"};
        List<String>tags11 = new ArrayList<>(Arrays.asList(tags11Array));
        List<String>genres11 = new ArrayList<>(Arrays.asList(genres11Array));
        Book b11 = new Book(11 , "The Help", "Kathryn Stockett", "February 10, 2009", tags11, genres11);
        addBook(bookList, b11);

        String[] tags12Array = new String[]{"Post Apocalyptic", "Teen", "Adventure", "Hunger Games 3"};
        String[] genres12Array = new String[]{"Young Adult", "Dystopia", "Fiction", "Fantasy", "Science Fiction", "Romance"};
        List<String>tags12 = new ArrayList<>(Arrays.asList(tags12Array));
        List<String>genres12 = new ArrayList<>(Arrays.asList(genres12Array));
        Book b12 = new Book(12 , "Mockingjay", "Suzanne Collins", "August 24, 2010",tags12, genres12);
        addBook(bookList, b12);

        String[] tags13Array = new String[]{"Coming of Age", "High School"};
        String[] genres13Array = new String[]{"Classics", "Fiction", "Young Adult", "American"};
        List<String>tags13 = new ArrayList<>(Arrays.asList(tags13Array));
        List<String>genres13 = new ArrayList<>(Arrays.asList(genres13Array));
        Book b13 = new Book(13 , "The Catcher in the Rye", "J.D. Salinger", "July 16, 1951", tags13, genres13);
        addBook(bookList, b13);

        String[] tags14Array = new String[]{"Teen", "LGBT", "Mental Health"};
        String[] genres14Array = new String[]{"Young Adult", "Fiction", "Classics", "Romance"};
        List<String>tags14 = new ArrayList<>(Arrays.asList(tags14Array));
        List<String>genres14 = new ArrayList<>(Arrays.asList(genres14Array));
        Book b14 = new Book(14 , "The Perks of Being a Wallflower", "Stephen Chbosky", "February 1, 1999", tags14, genres14);
        addBook(bookList, b14);

        String[] tags15Array = new String[]{"Adult", "Torture"};
        String[] genres15Array = new String[]{"Classics", "Science Fiction", "Dystopia", "Politics", "Fantasy"};
        List<String>tags15 = new ArrayList<>(Arrays.asList(tags15Array));
        List<String>genres15 = new ArrayList<>(Arrays.asList(genres15Array));
        Book b15 = new Book(15 , "1984", "George Orwell", "June 8, 1949", tags15, genres15);
        addBook(bookList, b15);

        String[] tags16Array = new String[]{"Teen", "Mental Health"};
        String[] genres16Array = new String[]{"Young Adult", "Mystery", "Contemporary", "Fiction", "Romance"};
        List<String>tags16 = new ArrayList<>(Arrays.asList(tags16Array));
        List<String>genres16 = new ArrayList<>(Arrays.asList(genres16Array));
        Book b16 = new Book(16 , "We Were Liars", "E. Lockhart", "May 13, 2014", tags16, genres16);
        addBook(bookList, b16);

        String[] tags17Array = new String[]{"Physics", "Adult"};
        String[] genres17Array = new String[]{"Nonfiction", "Science", "Humor", "Education"};
        List<String>tags17 = new ArrayList<>(Arrays.asList(tags17Array));
        List<String>genres17 = new ArrayList<>(Arrays.asList(genres17Array));
        Book b17 = new Book(17 , "What If? Serious Scientific Answers to Absurd Hypothetical Questions", "Randall Munroe", "September 2, 2014", tags17, genres17);
        addBook(bookList, b17);

        String[] tags18Array = new String[]{"Adult", "Literature"};
        String[] genres18Array = new String[]{"Classics", "Historical Fiction", "Romance"};
        List<String>tags18 = new ArrayList<>(Arrays.asList(tags18Array));
        List<String>genres18 = new ArrayList<>(Arrays.asList(genres18Array));
        Book b18 = new Book(18 , "Pride and Prejudice", "Jane Austen", "January 28, 1813", tags18, genres18);
        addBook(bookList, b18);

        String[] tags19Array = new String[]{"Young Adult", "Twilight 1", "Vampires"};
        String[] genres19Array = new String[]{"Urban Fantasy", "Paranormal", "Romance"};
        List<String>tags19 = new ArrayList<>(Arrays.asList(tags19Array));
        List<String>genres19 = new ArrayList<>(Arrays.asList(genres19Array));
        Book b19 = new Book(19 , "Twilight", "Stephenie Meyer", "October 5, 2005", tags19, genres19);
        addBook(bookList, b19);

        String[] tags20Array = new String[]{"Social Science", "Business", "Finance"};
        String[] genres20Array = new String[]{"Nonfiction", "Economics", "Psycology", "Politics", "Science"};
        List<String>tags20 = new ArrayList<>(Arrays.asList(tags20Array));
        List<String>genres20 = new ArrayList<>(Arrays.asList(genres20Array));
        Book b20 = new Book(20 , "Freakonomics: A Rogue Economist Explores the Hidden Side of Everything", "Steven D. Levitt", "April 12, 2005", tags20, genres20);
        addBook(bookList, b20);

        String[] tags21Array = new String[]{"LGBT", "Retellings"};
        String[] genres21Array = new String[]{"Historical Fiction", "Romance", "Greek Mythology"};
        List<String>tags21 = new ArrayList<>(Arrays.asList(tags21Array));
        List<String>genres21 = new ArrayList<>(Arrays.asList(genres21Array));
        Book b21 = new Book(21 , "The Song of Achilles", "Madeline Miller", "September 20, 2011", tags21, genres21);
        addBook(bookList, b21);

        String[] tags22Array = new String[]{"Adult", "Sweden"};
        String[] genres22Array = new String[]{"Fiction", "Mystery", "Thriller", "Crime", "Contemporary"};
        List<String>tags22 = new ArrayList<>(Arrays.asList(tags22Array));
        List<String>genres22 = new ArrayList<>(Arrays.asList(genres22Array));
        Book b22 = new Book(22 , "The Girl with the Dragon Tattoo", "Stieg Larsson", "August 1, 2005", tags22, genres22);
        addBook(bookList, b22);

        String[] tags23Array = new String[]{"LGBT", "Read For School"};
        String[] genres23Array = new String[]{"Classics", "Historical Fiction", "Romance", "American"};
        List<String>tags23 = new ArrayList<>(Arrays.asList(tags23Array));
        List<String>genres23 = new ArrayList<>(Arrays.asList(genres23Array));
        Book b23 = new Book(23 , "The Great Gatsby", "F. Scott Fitzgerald", "April 10, 1925", tags23, genres23);
        addBook(bookList, b23);

        String[] tags24Array = new String[]{"Adult", "Book Club"};
        String[] genres24Array = new String[]{"Historical Fiction", "Contemporary", "Romance"};
        List<String>tags24 = new ArrayList<>(Arrays.asList(tags24Array));
        List<String>genres24 = new ArrayList<>(Arrays.asList(genres24Array));
        Book b24 = new Book(24 , "Water for Elephants", "Sara Gruen", "May 22, 2006", tags24, genres24);
        addBook(bookList, b24);

        String[] tags25Array = new String[]{"Suspence", "Crime", "Adventure"};
        String[] genres25Array = new String[]{"Fiction", "Mystery", "Thriller", "Historical Fiction"};
        List<String>tags25 = new ArrayList<>(Arrays.asList(tags25Array));
        List<String>genres25 = new ArrayList<>(Arrays.asList(genres25Array));
        Book b25 = new Book(25 , "Angels & Demons", "Dan Brown", "May 1, 2000", tags25, genres25);
        addBook(bookList, b25);

        String[] tags26Array = new String[]{"Read For School", "Satire"};
        String[] genres26Array = new String[]{"Classics", "Dystopia", "Fantasy", "Science Fiction", "Philosophy"};
        List<String>tags26 = new ArrayList<>(Arrays.asList(tags26Array));
        List<String>genres26 = new ArrayList<>(Arrays.asList(genres26Array));
        Book b26 = new Book(26 , "Animal Farm", "George Orwell", "August 17, 1945", tags26, genres26);
        addBook(bookList, b26);

        String[] tags27Array = new String[]{"Japan", "Adult"};
        String[] genres27Array = new String[]{"Historical Fiction", "Romance", "Classics", "Adult Fiction"};
        List<String>tags27 = new ArrayList<>(Arrays.asList(tags27Array));
        List<String>genres27 = new ArrayList<>(Arrays.asList(genres27Array));
        Book b27 = new Book(27 , "Memoirs of a Geisha", "Arthur Golden", "January 1, 1997", tags27, genres27);
        addBook(bookList, b27);

        String[] tags28Array = new String[]{"Feminism", "Literature", "Adult"};
        String[] genres28Array = new String[]{"Science Fiction", "Fantasy", "Dystopia"};
        List<String>tags28 = new ArrayList<>(Arrays.asList(tags28Array));
        List<String>genres28 = new ArrayList<>(Arrays.asList(genres28Array));
        Book b28 = new Book(28 , "The Handmaid’s Tale", "Margaret Atwood", "January 1, 1985", tags28, genres28);
        addBook(bookList, b28);

        String[] tags29Array = new String[]{"Adult", "Spirituality"};
        String[] genres29Array = new String[]{"Fiction", "Contemporary", "Fantasy", "Classics", "Philosophy", "Adult Fiction"};
        List<String>tags29 = new ArrayList<>(Arrays.asList(tags29Array));
        List<String>genres29 = new ArrayList<>(Arrays.asList(genres29Array));
        Book b29 = new Book(29 , "The Five People You Meet in Heaven", "Mitch Albom", "September 1, 2003", tags29, genres29);
        addBook(bookList, b29);

        String[] tags30Array = new String[]{"Young Adult", "Magical Realism"};
        String[] genres30Array = new String[]{"Fiction", "Classics", "Philosophy"};
        List<String>tags30 = new ArrayList<>(Arrays.asList(tags30Array));
        List<String>genres30 = new ArrayList<>(Arrays.asList(genres30Array));
        Book b30 = new Book(30 , "Life of Pi", "Yann Martel", "September 11, 2001", tags30, genres30);
        addBook(bookList, b30);
    }

    public int checkList(Booklist list){
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


    public int checkCredentials(IUser user){
        int authrorized = 1;
        if(user != null && user instanceof Librarian) authrorized = 0;
        return authrorized;
    }

    public boolean upload(Book book, IUser user){
        int status = checkBook(book);
        if(checkCredentials(user) == 0 && status == 0) {
            addBook(bookList, book);
        }
        return status == 0;
    }

    @Override
    public void deleteLibraryBook(Booklist list, User user) {

    }

    @Override
    public ArrayList<String> searchTagByBook(Book book) {
        return null;
    }

    @Override
    public ArrayList<String> getAllTags() {
        return null;
    }

    @Override
    public ArrayList<String> getAllGenres() {
        return null;
    }

    @Override
    public Booklist searchTag(String tagName) {
        return null;
    }

    @Override
    public Booklist searchGenre(String genreName) {
        return null;
    }

    public int update(Book book, User user){
        int status = 1;
        if(checkBook(book) == 0) {
            for(int i = 0; i < bookList.size(); i++){
                if(bookList.get(i).getID() == book.getID()){
                    bookList.remove(i);
                    bookList.add(book);
                    status = 0;
                    break;
                }
            }
        }
        else{
            status = -1;
        }
        return status;
    }

    public void delete(Book book, User user){
        Book curr = null;
        if(checkCredentials(user) == 0 && checkBook(book) == 0){
            for(int i = 0; i < bookList.size(); i++){
                curr = bookList.get(i);
                if(curr.getID() == book.getID()){
                    bookList.remove(i);
                    break;
                }
            }
        }
    }

    public void delete(Booklist list, User user){
        Book curr;
        if(checkCredentials(user) == 0 && checkList(list) == 0) {
            for (int i = 0; i < list.size(); i++) {
                for(int j = 0; j < bookList.size(); j++){
                    curr = bookList.get(j);
                    if(curr.getID() == list.get(i).getID()){
                        bookList.remove(j);
                        break;
                    }
                }
            }

        }
    }

    public Booklist searchTag(List<String> tags){
        Booklist foundBooks = new Booklist();
        Book curr;
        if(tags != null){
            for(int i = 0; i < bookList.size(); i++){
                curr = bookList.get(i);
                if(similarStringArrays(tags, curr.getTags())){
                    addBook(foundBooks, curr);
                }
            }
        }
        return foundBooks;
    }

    public Booklist searchGenre(List<String> genres){
        Booklist foundBooks = new Booklist();
        Book curr;
        if(genres != null) {
            for (int i = 0; i < bookList.size(); i++) {
                curr = bookList.get(i);
                if (similarStringArrays(genres, curr.getGenres())) {
                    addBook(foundBooks, curr);
                }
            }
        }
        return foundBooks;
    }

    public Booklist searchAuthor(String author){
        Booklist foundBooks = new Booklist();
        Book curr;
        if(author != null) {
            for (int i = 0; i < bookList.size(); i++) {
                curr = bookList.get(i);
                if (curr.getAuthor().equals(author)) {
                    addBook(foundBooks, curr);
                }
            }
        }
        return foundBooks;
    }

    public Booklist searchName(String bookName){
        Booklist foundBooks = new Booklist();
        Book curr;
        if(bookName != null){
            for(int i = 0; i < bookList.size(); i++){
                curr = bookList.get(i);
                if(curr.getName().equals(bookName)){
                    addBook(foundBooks, curr);
                }
            }
        }
        return foundBooks;
    }

    public Booklist search(Booklist list){
        Booklist foundBooks = new Booklist();
        Book curr;
        if(checkList(list) == 0) {
            for (int i = 0; i < list.size(); i++) {
                curr = search(list.get(i));
                if (curr != null) {
                    addBook(foundBooks, curr);
                }
            }
        }
        return foundBooks;
    }

    public Booklist search(PreparedStatement statement){
        return null;
    }

    public Book search(Book book){
        Book curr = null;
        boolean found = false;
        if(checkBook(book) == 0) {
            for (int i = 0; i < bookList.size(); i++) {
                curr = bookList.get(i);
                if (curr.getID() == book.getID()) {
                    found = true;
                    break;
                }
            }
        }
        if(!found){
            curr = null;
        }
        return curr;
    }

    private void addBook(Booklist list, Book book){
        boolean newBook = true;
        Book curr;
        if(checkBook(book) == 0) {
            for (int i = 0; i < list.size(); i++) {
                curr = list.get(i);
                if (curr.getName().equals(book.getName()) && curr.getAuthor().equals(book.getAuthor())) {
                    newBook = false;
                    break;
                }
            }
        }
        if(newBook){
            list.add(book);
        }
    }

    public boolean similarStringArrays(List<String>array1, List<String> array2){
        boolean similar = false;
        if(array1 != null && array2 != null) {
            for (int i = 0; i < array1.size(); i++) {
                for (int j = 0; j < array2.size(); j++) {
                    if (array1.get(i).equals(array2.get(j))) {
                        similar = true;
                        break;
                    }
                }
                if(similar){
                    break;
                }
            }
        }
        return similar;
    }

    public Booklist getBookList(){
        return bookList;
    }

    @Override
    public Book getEachBooks(String require) {
        return null;
    }
}
