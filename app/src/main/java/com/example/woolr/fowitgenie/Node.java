package com.example.woolr.fowitgenie;

/**
 * Created by woolr on 17/03/2016.
 */
public abstract class Node {

    private final int data;

    public Node(int data) {
        this.data = data;
    }

    public int getData(){
        return data;
    }

    public int size(){
        return 1;
    }

    public boolean contains (int i){
        return data == i;
    }

}
