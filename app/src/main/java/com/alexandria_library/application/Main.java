package com.alexandria_library.application;

public class Main {

    private static String dbName = "LibraryDatabase";

    public static void main(String [] args){
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
