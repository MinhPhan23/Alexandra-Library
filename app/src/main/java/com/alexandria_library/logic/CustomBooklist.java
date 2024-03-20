package com.alexandria_library.logic;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IUserPersistent;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.IReader;
import com.alexandria_library.dso.User;
import com.alexandria_library.logic.Exception.BooklistException;

import java.util.ArrayList;

public class CustomBooklist implements ICustomBooklist{
    private final IUserPersistent data;
    public  CustomBooklist() {
        data = Service.getUserPersistent();
    }
    public  CustomBooklist(IUserPersistent data) {
        this.data = data;
    }

    private void checkDuplicate(Booklist booklist, Booklist newBook) throws BooklistException {
        Booklist compareList = new Booklist(newBook);
        compareList.retainAll(booklist);
        if (compareList.size() > 0) {
            String bookNames = "";
            for (Book book : compareList) {
                bookNames += (book.getName()+" ");
            }
            throw new BooklistException(String.format("The book(s) %sis already in list %s", bookNames, booklist.getName()));
        }
    }

    @Override
    public void addBookToCustom(IReader reader, Booklist newBook, Booklist booklist) throws BooklistException {
        ArrayList<Booklist> customBooklist = reader.getAllCustomList();
        boolean exist = false;
        for (Booklist eachBooklist : customBooklist) {
            if (booklist.getName().equals(eachBooklist.getName())) {
                exist = true;
                checkDuplicate(booklist, newBook);
                booklist.addAll(newBook);
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
