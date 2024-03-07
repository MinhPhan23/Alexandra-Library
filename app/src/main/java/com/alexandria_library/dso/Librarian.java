package com.alexandria_library.dso;

import com.alexandria_library.dso.dsoInterface.ILibrarian;

public class Librarian extends User implements ILibrarian {

    public Librarian(String userName, String password, int id){
        super(userName, password, id);
    }

}
