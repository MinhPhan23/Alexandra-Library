package com.alexandria_library.logic;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IUserPersistent;
import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.IReader;
import com.alexandria_library.dso.User;
import com.alexandria_library.logic.Exception.BooklistException;

public class DefaultBooklist implements IDefaultBooklist {
    private final IUserPersistent data;
    public  DefaultBooklist() {
        data = Service.getUserPersistent();
    }
    public  DefaultBooklist(IUserPersistent data) {
        this.data = data;
    }

    private void checkDuplicate(Booklist booklist, Booklist newBook) throws BooklistException {
        Booklist compareList = new Booklist(newBook);
        compareList.retainAll(booklist);
        if (compareList.size() > 0) {
            boolean firstWord = true;
            String bookNames = "";
            for (Book book : compareList) {
                if (firstWord) {
                    bookNames = book.getName();
                    firstWord = false;
                }
                else {
                    bookNames += (", "+ book.getName());
                }
            }
            throw new BooklistException(String.format("The book(s) %s is already in list %s", bookNames, booklist.getName()));
        }
    }
    @Override
    public void addBookToAll(IReader reader, Booklist newBook) throws BooklistException{
        Booklist all = reader.getAllBooksList();
        checkDuplicate(all, newBook);
        all.addAll(newBook);
        data.addBookToCustomList(newBook, (User) reader);
    }

    @Override
    public void addBookToInProgress(IReader reader, Booklist newBook) throws BooklistException{
        Booklist inProgress = reader.getInProgressList();
        checkDuplicate(inProgress, newBook);
        inProgress.addAll(newBook);
        data.addBookToReadingList(newBook, (User) reader);
    }

    @Override
    public void addBookToFinished(IReader reader, Booklist newBook) throws BooklistException{
        Booklist finished = reader.getFinishedList();
        checkDuplicate(finished, newBook);
        finished.addAll(newBook);
        data.addBookToFinishedList(newBook, (User) reader);
    }
}
