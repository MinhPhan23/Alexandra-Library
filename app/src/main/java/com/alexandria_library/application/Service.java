package com.alexandria_library.application;

import com.alexandria_library.data.IBookPersistenceHSQLDB;
import com.alexandria_library.data.IBookPersistentIntermediate;
import com.alexandria_library.data.IUserPersistenceHSQLDB;
import com.alexandria_library.data.IUserPersistent;
import com.alexandria_library.data.hsqldb.BookPersistenceHSQLDB;
import com.alexandria_library.data.hsqldb.UserPersistenceHSQLDB;
import com.alexandria_library.data.stub.BookPersistentInterStub;
import com.alexandria_library.data.stub.UserPersistentStub;

public class Service {
    private static IUserPersistent userPersistence = null;
    private static IBookPersistentIntermediate bookPersistentIntermediate = null;
    private static IBookPersistenceHSQLDB bookPersistenceHSQLDB = null;
    private static IUserPersistenceHSQLDB userPersistenceHSQLDB = null;

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

    /******
     * get book persistence HSQLDB intermediate's information
     */
    public static synchronized IBookPersistenceHSQLDB getBookPersistenceHSQLDB(){
        if(bookPersistenceHSQLDB == null){
            bookPersistenceHSQLDB = new BookPersistenceHSQLDB(Main.getDBPathName());
        }
        return bookPersistenceHSQLDB;
    }

    /******
     * get user persistence HSQLDB data ready when service begin
     */
    public static synchronized IUserPersistenceHSQLDB getUserPersistenceHSQLDB(){
        if(userPersistenceHSQLDB == null){
            userPersistenceHSQLDB = new UserPersistenceHSQLDB(Main.getDBPathName());
        }
        return userPersistenceHSQLDB;
    }
}
