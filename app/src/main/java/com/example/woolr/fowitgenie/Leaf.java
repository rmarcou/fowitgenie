package com.example.woolr.fowitgenie;

/**
 * Created by woolr on 17/03/2016.
 */
public class Leaf extends Node{

    public Leaf(int data){
        super(data);
    }


    public int size(){
        return super.size();
    }


    public boolean contains(int i){
        return super.contains(i);
    }


    @Override public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getData()).append(" ");
        return sb.toString();
    }


    @Override public boolean equals(Object o){
        if(!(o instanceof Leaf))
            return false;

        Leaf l = (Leaf)o;
        return getData() == l.getData();
    }

}
