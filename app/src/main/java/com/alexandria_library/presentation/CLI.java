package com.alexandria_library.presentation;

import com.alexandria_library.application.Service;
import com.alexandria_library.data.IBookPersistent;
import com.alexandria_library.data.IUserPersistent;
import com.alexandria_library.data.utils.HSQLDBHelper;
import com.alexandria_library.dso.IReader;
import com.alexandria_library.logic.BookListFilter;
import com.alexandria_library.logic.IBookListFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

public class CLI { //command-line interface

    public static BufferedReader console;
    public static String inputLine;
    public static String[] inputTokens;
    private static IBookPersistent bookPersistent;
    private static IUserPersistent userPersistent;


    public static String indent = "  ";

    public static void run(){
        try{
            bookPersistent = Service.getBookPersistent();
            userPersistent = Service.getUserPersistent();
            console = new BufferedReader(new InputStreamReader(System.in));
            process();
            console.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void process(){
        readLine();
        while((inputLine != null) && (!inputLine.equalsIgnoreCase("quit"))){
            inputTokens = inputLine.split("\\s+");
            parse();
            readLine();
        }
    }

    public static void readLine(){
        try{
            inputLine = console.readLine();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void parse(){
        if(inputTokens[0].equalsIgnoreCase("get")){
            processGet();
        }
        else{
            System.out.println("invalid command");
        }
    }

    public static void processGet(){
        if(inputTokens[1].equalsIgnoreCase("book")){
            processGetBook();
        }
//        else if(inputTokens[1].equalsIgnoreCase("user")){
//            processGetUser();
//        }
//        else if(inputTokens[1].equalsIgnoreCase("tag")){
//            processGetTag();
//        }
//        else if(inputTokens[1].equalsIgnoreCase("genre")){
//            processGetGenre();
//        }
        else{
            System.out.println("Invalid data type");
        }
    }

    public static void processGetBook(){
        IBookListFilter bookFilter = new BookListFilter();
        if(inputTokens.length == 3){
            if(inputTokens[2].equalsIgnoreCase("alltag")){
                ArrayList<String > getAllTags = bookFilter.getAllTags(bookPersistent);
                System.out.println(indent+getAllTags.toString());
            }
            if(inputTokens[2].equalsIgnoreCase("allgenre")){
                ArrayList<String > getAllGenres = bookFilter.getAllGenre(bookPersistent);
                System.out.println(indent+getAllGenres.toString());
            }
        }
    }
}
