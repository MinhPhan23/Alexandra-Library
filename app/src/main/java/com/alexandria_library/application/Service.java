package com.alexandria_library.application;

import com.alexandria_library.data.IBookPersistentIntermediate;
import com.alexandria_library.data.IUserPersistent;
import com.alexandria_library.data.stub.BookPersistentInterStub;
import com.alexandria_library.data.stub.UserPersistentStub;
import com.alexandria_library.dso.User;

public class Service {
    private static IUserPersistent userPersistence = null;
    private static IBookPersistentIntermediate bookPersistentIntermediate = null;

    /******
     * get user persistence mock data ready when service begin
     * @return
     */
    public static synchronized IUserPersistent getUserPersistence(){
        if(userPersistence == null){
            userPersistence = new UserPersistentStub();
        }
        return userPersistence;
    }

    /******
     * get book persistence intermediate's mock data ready when service is begin
     * @return
     */

    public static synchronized IBookPersistentIntermediate getBookPersistenceIntermediate(){
        if(bookPersistentIntermediate == null){
            bookPersistentIntermediate = new BookPersistentInterStub();
        }
        return bookPersistentIntermediate;
    }
}
