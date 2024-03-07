package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.dsoInterface.IReader;
import com.alexandria_library.logic.Exception.BooklistException;

import java.util.ArrayList;

public class CustomBooklist implements ICustomBooklist{

    private void addNonDuplicate(Booklist booklist, Book book) throws BooklistException {
        for (Book eachBook: booklist) {
            if (eachBook.equals(book)) {
                throw new BooklistException(String.format("The book %s is already in list %s", book.getName(), booklist.getName()));
            }
        }
        booklist.add(book);
    }

    @Override
    public void addBookToCustom(IReader reader, Book book, Booklist booklist) throws BooklistException {
        ArrayList<Booklist> customBooklist = reader.getAllCustomList();
        boolean exist = false;
        for (Booklist eachBooklist : customBooklist) {
            if (booklist.getName().equals(eachBooklist.getName())) {
                exist = true;
                addNonDuplicate(eachBooklist, book);
            }
        }
        if (!exist) {
            throw new BooklistException(String.format("The list %s does not exist", booklist.getName()));
        }
    }

    @Override
    public void addBooklist(IReader reader, String name) throws BooklistException{
        Booklist newBooklist = new Booklist();
        ArrayList<Booklist> customBooklist = reader.getAllCustomList();

        for (Booklist eachBooklist : customBooklist) {
            if (name.equals(eachBooklist.getName())) {
                throw new BooklistException(String.format("The list %s already exist", name));
            }
        }

        newBooklist.setName(name);
        customBooklist.add(newBooklist);
    }

    @Override
    public void addBooklist(IReader reader, Booklist booklist) throws BooklistException{
        ArrayList<Booklist> customBooklist = reader.getAllCustomList();

        for (Booklist eachBooklist : customBooklist) {
            if (booklist.getName().equals(eachBooklist.getName())) {
                throw new BooklistException(String.format("The list %s already exist", booklist.getName()));
            }
        }
        customBooklist.add(booklist);
    }
}
