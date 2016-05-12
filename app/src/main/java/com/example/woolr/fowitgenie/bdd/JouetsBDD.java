package com.example.woolr.fowitgenie.bdd;

/**
 * Created by sazm on 14/04/2016.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.woolr.fowitgenie.Jouet;
import com.example.woolr.fowitgenie.Question;

import java.util.ArrayList;

public class JouetsBDD extends BDD {

    private static final String TABLE_JOUETS = "jouets";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NOM = "nom";
    private static final int NUM_COL_NOM = 1;

    public JouetsBDD(Context context) {
        super(context);
    }

    // Methode pour ajouter un Jouet dans la BDD
    public long create(Jouet jouet) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_NOM, jouet.getNom());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_JOUETS, null, values);
    }

    // Methode pour ajouter une liste de Jouets dans la BDD
    public void create(ArrayList<Jouet> jouets) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

        for (Jouet jouet : jouets) {
            values.put(COL_NOM, jouet.getNom());
            //on insère l'objet dans la BDD via le ContentValues
            bdd.insert(TABLE_JOUETS, null, values);
        }
    }

    // Methode pour lire un Jouet de la BDD par son id
    public Jouet read(int id) {
        Cursor c = bdd.query(TABLE_JOUETS, new String[]{COL_ID, COL_NOM}, COL_ID + " = " + id, null, null, null, null);
        c.moveToFirst();
        Jouet jouet = cursorToJouet(c);
        return jouet;
    }

    // Methode pour récupérer tous les Jouets de la BDD
    public ArrayList<Jouet> read() {
        ArrayList<Jouet> jouets = new ArrayList<Jouet>();
        Cursor c = bdd.query(TABLE_JOUETS, new String[]{COL_ID, COL_NOM}, null, null, null, null, null);
        c.moveToFirst();
        do {
            Jouet jouet = cursorToJouet(c);
            jouets.add(jouet);
        }
        while (c.moveToNext());
        return jouets;
    }

    // Methode pour mettre à jour un Jouet dans la BDD par son id
    public int update(int id, Jouet jouet) {
        //La mise à jour dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_NOM, jouet.getNom());
        return bdd.update(TABLE_JOUETS, values, COL_ID + " = " + id, null);
    }

    // Methode pour effacer un Jouet dans la BDD par son id
    public int delete(int id) {
        //Suppression de la BDD grâce à l'ID
        return bdd.delete(TABLE_JOUETS, COL_ID + " = " + id, null);
    }

    // Methode pour Vider la table de la BDD
    public void erase(){
       bdd.execSQL("DELETE FROM " +TABLE_JOUETS);
    }

    //Cette méthode permet de convertir un cursor en un Jouet
    private Jouet cursorToJouet(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //On créé un Jouet
        Jouet jouet = new Jouet();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        jouet.setId(c.getInt(NUM_COL_ID));
        jouet.setNom(c.getString(NUM_COL_NOM));

        //On retourne le livre
        return jouet;
    }

}