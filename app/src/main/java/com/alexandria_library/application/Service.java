package com.alexandria_library.application;

import com.alexandria_library.data.IUser;
import com.alexandria_library.data.stub.UserStub;

public class Service {
    private static IUser userPersistence = null;

    public static synchronized IUser getUserPersistence(){
        if(userPersistence == null){
            userPersistence = new UserStub();
        }
        return userPersistence;
    }

}
