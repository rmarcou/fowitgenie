package com.example.woolr.fowitgenie;

import java.util.List;

/**
 * Created by woolr on 17/03/2016.
 */
public class InternalNode extends Node{

    private final List<Node> children;

    public InternalNode(int data, List<Node> children){
        super(data);
        this.children = children;
    }


    public List<Node> getChildren(){
        return children;
    }


    @Override public int size(){
        int size = 1;
        for(Node n : children){
            size += n.size();
        }
        return size;
    }


    @Override public boolean contains(int i){
        if(getData() == i)
            return true;

        boolean ret = false;
        int j = 0;
        while(!ret && j < children.size()){
            ret = children.get(j++).contains(i);
        }
        return ret;

    }


    @Override public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getData()).append(" ").append(children.toString());
        return sb.toString();

    }


    @Override public boolean equals(Object o){
        boolean ret = false;
        if(!(o instanceof InternalNode))
            return ret;

        InternalNode n = (InternalNode)o;
        return (getData() == n.getData() && children.equals(n.children));
    }

}
