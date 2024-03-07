package com.alexandria_library.presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI { //command-line interface

    public static BufferedReader console;
    public static String inputLine;
    public static String[] inputTokens;


    public static String indent = "  ";

    public static void run(){
        try{
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

        }
        else{
            System.out.println("invalid command");
        }
    }

}
