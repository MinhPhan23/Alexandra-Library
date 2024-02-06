package com.alexandria_library.data.stub;

import com.alexandria_library.data.IBookPersistentIntermediate;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.User;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class BookPersistentStub implements IBookPersistentIntermediate {

    ArrayList<Book> bookList = new ArrayList<Book>();
    public BookPersistentStub(){
        String tags1[] = new String[]{"LGBT", "Adult"};
        String genres1[] = new String[]{"Romance", "Contemporary", "Historical Fiction"};
        Book b1 = new Book(1 , "The Seven Husbands of Evalyn Hugo", "Taylor Jenkins Reid", "June 13. 2017", tags1, genres1);
        bookList.add(b1);

        String tags2[] = new String[]{"High School", "Literature"};
        String genres2[] = new String[]{"Classics", "Fiction", "Historical Fiction", "Young Adult"};
        Book b2 = new Book(2 , "To Kill a Mockingbird", "Harper Lee", "July 11, 1960", tags2, genres2);
        bookList.add(b2);

        String tags3[] = new String[]{"Coming of Age", "Love", "Teen"};
        String genres3[] = new String[]{"Realistic Fiction", "Contemporary", "Young Adult"};
        Book b3 = new Book(3 , "The Fault in Our Stars", "John Green", "January 10, 2012", tags3, genres3);
        bookList.add(b3);

        String tags4[] = new String[]{"World War II", "Holocaust", "Books About Books"};
        String genres4[] = new String[]{"War", "Classics", "Historical Fiction", "Young Adult"};
        Book b4 = new Book(4 , "The Book Thief", "Markus Zusak", "September 1, 2005", tags4, genres4);
        bookList.add(b4);

        String tags5[] = new String[]{"Post Apocalyptic", "Survival", "Hunger Games 1"};
        String genres5[] = new String[]{"Dystopia", "Science Fiction", "Fantasy", "Young Adult", "Action"};
        Book b5 = new Book(5 , "The Hunger Games", "Suzanne Collins", "September 14, 2008", tags5, genres5);
        bookList.add(b5);

        String tags6[] = new String[]{"Spirituality", "Dreams"};
        String genres6[] = new String[]{"Fiction", "Fantasy", "Philosophy", "Self Help"};
        Book b6 = new Book(6 , "The Alchemist", "Paulo Coelho", "January 1, 1988", tags6, genres6);
        bookList.add(b6);

        String tags7[] = new String[]{"Suspence", "Adventure"};
        String genres7[] = new String[]{"Fiction", "Mystery", "Thriller", "Historical Fiction"};
        Book b7 = new Book(7 , "The Da Vinci Code", "Dan Brown", "March 18, 2003", tags7, genres7);
        bookList.add(b7);

        String tags8[] = new String[]{"Magical Realism", "Adult", "Mental Health"};
        String genres8[] = new String[]{"Fiction", "Fantasy", "Contemporary", "Science Fiction"};
        Book b8 = new Book(8 , "The Midnight Library", "Matt Haig", "August 13, 2020", tags8, genres8);
        bookList.add(b8);

        String tags9[] = new String[]{"Adult", "School"};
        String genres9[] = new String[]{"Historical Fiction", "Contemporary", "Adult Fiction"};
        Book b9 = new Book(9 , "The Kite Runner", "Khaled Hosseini", "May 29, 2003", tags9, genres9);
        bookList.add(b9);

        String tags10[] = new String[]{"Post Apocalyptic", "Teen", "Hunger Games 2"};
        String genres10[] = new String[]{"Young Adult", "Dystopia", "Science Fiction", "Romance", "Fantasy"};
        Book b10 = new Book(10 , "Catching Fire", "Suzanne Collins", "September 1, 2009", tags10, genres10);
        bookList.add(b10);

        String tags11[] = new String[]{"Adult", "Book Club"};
        String genres11[] = new String[]{"Historical Fiction", "Contemporary"};
        Book b11 = new Book(11 , "The Help", "Kathryn Stockett", "February 10, 2009", tags11, genres11);
        bookList.add(b11);

        String tags12[] = new String[]{"Post Apocalyptic", "Teen", "Adventure", "Hunger Games 3"};
        String genres12[] = new String[]{"Young Adult", "Dystopia", "Fiction", "Fantasy", "Science Fiction", "Romance"};
        Book b12 = new Book(12 , "Mockingjay", "Suzanne Collins", "August 24, 2010",tags12, genres12);
        bookList.add(b12);

        String tags13[] = new String[]{"Coming of Age", "High School"};
        String genres13[] = new String[]{"Classics", "Fiction", "Young Adult", "American"};
        Book b13 = new Book(13 , "The Catcher in the Rye", "J.D. Salinger", "July 16, 1951", tags13, genres13);
        bookList.add(b13);

        String tags14[] = new String[]{"Teen", "LGBT", "Mental Health"};
        String genres14[] = new String[]{"Young Adult", "Fiction", "Classics", "Romance"};
        Book b14 = new Book(14 , "The Perks of Being a Wallflower", "Stephen Chbosky", "February 1, 1999", tags14, genres14);
        bookList.add(b14);

        String tags15[] = new String[]{"Adult", "Torture"};
        String genres15[] = new String[]{"Classics", "Science Fiction", "Dystopia", "Politics", "Fantasy"};
        Book b15 = new Book(15 , "1984", "George Orwell", "June 8, 1949", tags15, genres15);
        bookList.add(b15);

        String tags16[] = new String[]{"Teen", "Mental Health"};
        String genres16[] = new String[]{"Young Adult", "Mystery", "Contemporary", "Fiction", "Romance"};
        Book b16 = new Book(16 , "We Were Liars", "E. Lockhart", "May 13, 2014", tags16, genres16);
        bookList.add(b16);

        String tags17[] = new String[]{"Physics", "Adult"};
        String genres17[] = new String[]{"Nonfiction", "Science", "Humor", "Education"};
        Book b17 = new Book(17 , "What If? Serious Scientific Answers to Absurd Hypothetical Questions", "Randall Munroe", "September 2, 2014", tags17, genres17);
        bookList.add(b17);

        String tags18[] = new String[]{"Adult", "Literature"};
        String genres18[] = new String[]{"Classics", "Historical Fiction", "Romance"};
        Book b18 = new Book(18 , "Pride and Prejudice", "Jane Austen", "January 28, 1813", tags18, genres18);
        bookList.add(b18);

        String tags19[] = new String[]{"Young Adult", "Twilight 1", "Vampires"};
        String genres19[] = new String[]{"Urban Fantasy", "Paranormal", "Romance"};
        Book b19 = new Book(19 , "Twilight", "Stephenie Meyer", "October 5, 2005", tags19, genres19);
        bookList.add(b19);

        String tags20[] = new String[]{"Social Science", "Business", "Finance"};
        String genres20[] = new String[]{"Nonfiction", "Economics", "Psycology", "Politics", "Science"};
        Book b20 = new Book(20 , "Freakonomics: A Rogue Economist Explores the Hidden Side of Everything", "Steven D. Levitt", "April 12, 2005", tags20, genres20);
        bookList.add(b20);

        String tags21[] = new String[]{"LGBT", "Retellings"};
        String genres21[] = new String[]{"Historical Fiction", "Romance", "Greek Mythology"};
        Book b21 = new Book(21 , "The Song of Achilles", "Madeline Miller", "September 20, 2011", tags21, genres21);
        bookList.add(b21);

        String tags22[] = new String[]{"Adult", "Sweden"};
        String genres22[] = new String[]{"Fiction", "Mystery", "Thriller", "Crime", "Contemporary"};
        Book b22 = new Book(22 , "The Girl with the Dragon Tattoo", "Stieg Larsson", "August 1, 2005", tags22, genres22);
        bookList.add(b22);

        String tags23[] = new String[]{"LGBT", "Read For School"};
        String genres23[] = new String[]{"Classics", "Historical Fiction", "Romance", "American"};
        Book b23 = new Book(23 , "The Great Gatsby", "F. Scott Fitzgerald", "April 10, 1925", tags23, genres23);
        bookList.add(b23);

        String tags24[] = new String[]{"Adult", "Book Club"};
        String genres24[] = new String[]{"Historical Fiction", "Contemporary", "Romance"};
        Book b24 = new Book(24 , "Water for Elephants", "Sara Gruen", "May 22, 2006", tags24, genres24);
        bookList.add(b24);

        String tags25[] = new String[]{"Suspence", "Crime", "Adventure"};
        String genres25[] = new String[]{"Fiction", "Mystery", "Thriller", "Historical Fiction"};
        Book b25 = new Book(25 , "Angels & Demons", "Dan Brown", "May 1, 2000", tags25, genres25);
        bookList.add(b25);

        String tags26[] = new String[]{"Read For School", "Satire"};
        String genres26[] = new String[]{"Classics", "Dystopia", "Fantasy", "Science Fiction", "Philosophy"};
        Book b26 = new Book(26 , "Animal Farm", "George Orwell", "August 17, 1945", tags26, genres26);
        bookList.add(b26);

        String tags27[] = new String[]{"Japan", "Adult"};
        String genres27[] = new String[]{"Historical Fiction", "Romance", "Classics", "Adult Fiction"};
        Book b27 = new Book(27 , "Memoirs of a Geisha", "Arthur Golden", "January 1, 1997", tags27, genres27);
        bookList.add(b27);

        String tags28[] = new String[]{"Feminism", "Literature", "Adult"};
        String genres28[] = new String[]{"Science Fiction", "Fantasy", "Dystopia"};
        Book b28 = new Book(28 , "The Handmaid’s Tale", "Margaret Atwood", "January 1, 1985", tags28, genres28);
        bookList.add(b28);

        String tags29[] = new String[]{"Adult", "Spirituality"};
        String genres29[] = new String[]{"Fiction", "Contemporary", "Fantasy", "Classics", "Philosophy", "Adult Fiction"};
        Book b29 = new Book(29 , "The Five People You Meet in Heaven", "Mitch Albom", "September 1, 2003", tags29, genres29);
        bookList.add(b29);

        String tags30[] = new String[]{"Young Adult", "Magical Realism"};
        String genres30[] = new String[]{"Fiction", "Classics", "Philosophy"};
        Book b30 = new Book(30 , "Life of Pi", "Yann Martel", "September 11, 2001", tags30, genres30);
        bookList.add(b30);
    }

    public int checkList(ArrayList<Book> list){
        int status = 0;
        for (int i = 0; i < list.size(); i++){
            status = checkBook(list.get(i));
        }
        return status;
    }

    public int checkBook(Book book){
        int status = 0;
        if(book.getName() == null || book.getName().equals("")){
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
        return status;
    }


    public int checkCredentials(User user){
        return 0;
    }

    public int upload(Book book, User user){
        int status = -1;
        if(checkCredentials(user) == 0) {
            status = checkBook(book);
        }
        if(status == 0){
            bookList.add(book);
        }
        return status;
    }

    public int update(Book book){
        Book curr = search(book);
        int status = checkBook(book);
        if(curr == null){
            status = -1;
        }
        else if(status == 0) {

        }
        return status;
    }

    public void delete(Book book){
        Book curr = null;
        if(checkBook(book) == 0){
            for(int i = 0; i < bookList.size(); i++){
                curr = bookList.get(i);
                if(curr.getID() == book.getID()){
                    bookList.remove(i);
                    i = Integer.MAX_VALUE;
                }
            }
        }
    }

    public void delete(ArrayList<Book> list){

    }

    public ArrayList<Book> searchTag(String[] tags){
        return null;
    }

    public ArrayList<Book> searchGenre(String[] genres){
        return null;
    }

    public ArrayList<Book> searchAuthor(String author){
        return null;
    }

    public ArrayList<Book> searchName(String bookName){
        return null;
    }

    public ArrayList<Book> search(ArrayList<Book> list){
        return null;
    }

    public ArrayList<Book> search(PreparedStatement statement){
        return null;
    }

    public Book search(Book book){
        Book curr = null;
        for(int i = 0; i < bookList.size(); i++){
            curr = bookList.get(i);
            if(curr.getID() == book.getID()){
                i = Integer.MAX_VALUE;
            }
        }
        return curr;
    }

}
