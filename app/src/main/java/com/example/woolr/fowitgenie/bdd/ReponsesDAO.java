package com.example.woolr.fowitgenie.bdd;

/**
 * Created by sazm on 14/04/2016.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.woolr.fowitgenie.Jouet;
import com.example.woolr.fowitgenie.Question;
import com.example.woolr.fowitgenie.Reponse;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class ReponsesDAO extends BDD {

    private Context context;
    private static final String TABLE_REPONSES = "reponses";
    private static final String COL_ID_JOUET = "id_jouet";
    private static final int NUM_COL_ID_JOUET = 0;
    private static final String COL_ID_QUESTION= "id_question";
    private static final int NUM_COL_ID_QUESTION = 1;
    private static final String COL_REPONSE = "reponse_attendue";
    private static final int NUM_COL_REPONSE = 2;

    public ReponsesDAO(Context context) throws IOException, ParseException {
        super(context);
    }
    
    // Methode pour ajouter un Jouet dans la BDD
    public long create(Reponse reponse) throws IOException, ParseException {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID_JOUET, reponse.getJouet(context).getId());
        values.put(COL_ID_QUESTION, reponse.getQuestion(context).getId());
        values.put(COL_REPONSE, reponse.getReponse_attendue());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_REPONSES, null, values);
    }

    // Methode pour ajouter une liste de réponses dans la BDD
    public void create(ArrayList<Reponse> reponses) throws IOException, ParseException {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

        for (Reponse reponse : reponses) {
            values.put(COL_ID_JOUET, reponse.getJouet(context).getId());
            values.put(COL_ID_QUESTION, reponse.getQuestion(context).getId());
            values.put(COL_REPONSE, reponse.getReponse_attendue());
            //on insère l'objet dans la BDD via le ContentValues
            bdd.insert(TABLE_REPONSES, null, values);
        }
    }

    // Methode pour lire une Reponse de la BDD par rapport aux id jouet et question
    public Reponse read(int Jouet_id,int Question_id) {
        Cursor c = bdd.query(TABLE_REPONSES, new String[]{COL_ID_JOUET, COL_ID_QUESTION, COL_REPONSE}, COL_ID_JOUET + " = " + Jouet_id + " AND " + COL_ID_QUESTION + " = " + Question_id, null, null, null, null);
        c.moveToFirst();
        Reponse reponse = cursorToReponse(c);
        return reponse;
    }

    // Methode pour récupérer toutes les Reponses de la BDD
    public ArrayList<Reponse> read() {
        ArrayList<Reponse> reponses = new ArrayList<Reponse>();
        Cursor c = bdd.query(TABLE_REPONSES, new String[]{COL_ID_JOUET, COL_ID_QUESTION, COL_REPONSE}, null, null, null, null, null);
        c.moveToFirst();
        do {
            Reponse reponse = cursorToReponse(c);
            reponses.add(reponse);
        }
        while (c.moveToNext());
        return reponses;
    }

    // Methode pour mettre à jour une Reponse dans la BDD par son id
    public int update(int Jouet_id, int Question_id, Reponse reponse) throws IOException, ParseException {
        //La mise à jour dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_ID_JOUET, reponse.getJouet(context).getId());
        values.put(COL_ID_QUESTION, reponse.getQuestion(context).getId());
        values.put(COL_REPONSE, reponse.getReponse_attendue());
        return bdd.update(TABLE_REPONSES, values, COL_ID_JOUET + " = " + Jouet_id + " AND " + COL_ID_QUESTION + " = " + Question_id, null);
    }

    // Methode pour effacer une Reponse dans la BDD par son id
    public int delete(int Jouet_id, int Question_id) {
        //Suppression de la BDD grâce aux IDs
        return bdd.delete(TABLE_REPONSES, COL_ID_JOUET + " = " + Jouet_id + " AND " + COL_ID_QUESTION + " = " + Question_id, null);
    }

    // Methode pour Vider la table de la BDD
    public void erase(){
       bdd.execSQL("DELETE FROM " + TABLE_REPONSES);
    }

    //Cette méthode permet de convertir un cursor en une reponse
    private Reponse cursorToReponse(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //On créé un Jouet
        Reponse reponse = new Reponse();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        reponse.setJeu_id(c.getInt(NUM_COL_ID_JOUET));
        reponse.setQuestion_id(c.getInt(NUM_COL_ID_QUESTION));
        reponse.setReponse_attendue(c.getInt(NUM_COL_REPONSE));

        //On retourne la reponse
        return reponse;
    }

}