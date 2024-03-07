package com.alexandria_library.logic;

import com.alexandria_library.dso.Book;
import com.alexandria_library.dso.Booklist;
import com.alexandria_library.dso.dsoInterface.IReader;
import com.alexandria_library.logic.Exception.BooklistException;

public class DefaultBooklist implements IDefaultBooklist {
    private void addNonDuplicate(Booklist booklist, Book book) throws BooklistException {
        for (Book eachBook: booklist) {
            if (eachBook.equals(book)) {
                throw new BooklistException(String.format("The book %s is already in %s list", book.getName(), booklist.getName()));
            }
        }
        booklist.add(book);
    }
    @Override
    public void addBookToAll(IReader reader, Book newBook) throws BooklistException{
        Booklist all = reader.getAllBooksList();
        addNonDuplicate(all, newBook);
    }

    @Override
    public void addBookToInProgress(IReader reader, Book newBook) throws BooklistException{
        Booklist inProgress = reader.getInProgressList();
        addNonDuplicate(inProgress, newBook);
    }

    @Override
    public void addBookToFinished(IReader reader, Book newBook) throws BooklistException{
        Booklist finished = reader.getFinishedList();
        addNonDuplicate(finished, newBook);
    }
}
