package com.alexandria_library.dso;

import java.util.ArrayList;

public class Booklist extends ArrayList<Book> implements IBooklist {

    private String name;

    private String desc;
    public Booklist(){
        this.name = "";
        this.desc = "";
    }

    public String getName(){
        return name;
    }

    public String getDesc(){
        return desc;
    }

    public void setName(String name){
        if(name != null){
            this.name = name;
        }
    }

    public void setDesc(String desc){
        if(desc != null){
            this.desc = desc;
        }
    }
}
