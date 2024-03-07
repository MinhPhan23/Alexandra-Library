package com.alexandria_library.application;

import com.alexandria_library.presentation.CLI;

public class Main {

    private static String dbName = "LibraryDB";

    public static void main(String [] args){
        CLI.run();
        System.out.println("ALL done");
    }

    public static void setDBPathName(final String name){
        try{
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e){
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        dbName = name;
    }

    public static String getDBPathName(){
        return dbName;
    }
}
