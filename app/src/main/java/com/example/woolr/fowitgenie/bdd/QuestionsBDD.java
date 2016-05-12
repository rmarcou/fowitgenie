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

public class QuestionsBDD extends BDD {

    private static final String TABLE_QUESTIONS = "jouets";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_TEXTE = "texte";
    private static final int NUM_COL_TEXTE = 1;

    public QuestionsBDD(Context context) {
        super(context);
    }

    // Methode pour ajouter une Question dans la BDD
    public long create(Question question) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_TEXTE, question.getTexte());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_QUESTIONS, null, values);
    }

    // Methode pour ajouter une liste de Jouets dans la BDD
    public void create(ArrayList<Question>  questions) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

        for (Question question : questions) {
            values.put(COL_TEXTE, question.getTexte());
            //on insère l'objet dans la BDD via le ContentValues
            bdd.insert(TABLE_QUESTIONS, null, values);
        }
    }

    // Methode pour lire une Question de la BDD par son id
    public Question read(int id) {
        Cursor c = bdd.query(TABLE_QUESTIONS, new String[]{COL_ID, COL_TEXTE}, COL_ID + " = " + id, null, null, null, null);
        c.moveToFirst();
        Question question = cursorToQuestion(c);
        return question;
    }

    // Methode pour récupérer toutes les Questions de la BDD
    public ArrayList<Question> read() {
        ArrayList<Question> questions = new ArrayList<Question>();
        Cursor c = bdd.query(TABLE_QUESTIONS, new String[]{COL_ID, COL_TEXTE}, null, null, null, null, null);
        c.moveToFirst();
        do {
            Question question = cursorToQuestion(c);
            questions.add(question);
        }
        while (c.moveToNext());
        return questions;
    }

    // Methode pour mettre à jour un Question dans la BDD par son id
    public int update(int id, Question question) {
        //La mise à jour dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_TEXTE, question.getTexte());
        return bdd.update(TABLE_QUESTIONS, values, COL_ID + " = " + id, null);
    }

    // Methode pour effacer une Question dans la BDD par son id
    public int delete(int id) {
        //Suppression de la BDD grâce à l'ID
        return bdd.delete(TABLE_QUESTIONS, COL_ID + " = " + id, null);
    }

    // Methode pour Vider la table de la BDD
    public void erase(){
        bdd.execSQL("DELETE FROM " + TABLE_QUESTIONS);
    }

    //Cette méthode permet de convertir un cursor en un Question
    private Question cursorToQuestion(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //On créé un Jouet
        Question question = new Question();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        question.setId(c.getInt(NUM_COL_ID));
        question.setTexte(c.getString(NUM_COL_TEXTE));

        //On retourne le livre
        return question;
    }

}