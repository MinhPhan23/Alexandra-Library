package com.alexandria_library.application;

import com.alexandria_library.data.IBookPersistentHSQLDB;
import com.alexandria_library.data.IBookPersistentStub;
import com.alexandria_library.data.IUserPersistentHSQLDB;
import com.alexandria_library.data.IUserPersistentStub;
import com.alexandria_library.data.hsqldb.BookPersistentHSQLDB;
import com.alexandria_library.data.hsqldb.UserPersistentHSQLDB;
import com.alexandria_library.data.stub.BookPersistentInterStub;
import com.alexandria_library.data.stub.UserPersistentStub;

public class Service {
    private static IUserPersistentStub userPersistence = null;
    private static IBookPersistentStub bookPersistentIntermediate = null;
    private static IBookPersistentHSQLDB bookPersistenceHSQLDB = null;
    private static IUserPersistentHSQLDB userPersistenceHSQLDB = null;

    /******
     * get user persistence mock data ready when service begin
     * @return
     */
    public static synchronized IUserPersistentStub getUserPersistence(){
        if(userPersistence == null){
          userPersistence = new UserPersistentStub();
        }
        return userPersistence;
    }

    /******
     * get book persistence intermediate's mock data ready when service is begin
     * @return
     */
    public static synchronized IBookPersistentStub getBookPersistenceIntermediate(){
        if(bookPersistentIntermediate == null){
            bookPersistentIntermediate = new BookPersistentInterStub();
        }
        return bookPersistentIntermediate;
    }

    /******
     * get book persistence HSQLDB intermediate's information
     */
    public static synchronized IBookPersistentHSQLDB getBookPersistenceHSQLDB(){
        if(bookPersistenceHSQLDB == null){
            bookPersistenceHSQLDB = new BookPersistentHSQLDB(Main.getDBPathName());
        }
        return bookPersistenceHSQLDB;
    }

    /******
     * get user persistence HSQLDB data ready when service begin
     */
    public static synchronized IUserPersistentHSQLDB getUserPersistenceHSQLDB(){
        if(userPersistenceHSQLDB == null){
            userPersistenceHSQLDB = new UserPersistentHSQLDB(Main.getDBPathName());
        }
        return userPersistenceHSQLDB;
    }
}
