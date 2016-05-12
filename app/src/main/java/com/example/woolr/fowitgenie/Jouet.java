package com.example.woolr.fowitgenie;

/**
 * Created by sazm on 31/03/2016.
 */
public class Jouet {
    private int id;
    private String nom;

    public Jouet(){
    }
    public Jouet(String nom){
        this.nom = nom;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
