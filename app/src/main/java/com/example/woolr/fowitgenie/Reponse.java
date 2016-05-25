package com.example.woolr.fowitgenie;

import android.content.Context;

import com.example.woolr.fowitgenie.bdd.JouetsDAO;
import com.example.woolr.fowitgenie.bdd.QuestionsDAO;

import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by sazm on 31/03/2016.
 */
public class Reponse {
    private int jeu_id;
    private int question_id;
    private int reponse_attendue;

    public int getJeu_id() {
        return jeu_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getReponse_attendue() {
        return reponse_attendue;
    }

    public void setReponse_attendue(int reponse_attendue) {
        this.reponse_attendue = reponse_attendue;
    }

    public void setJeu_id(int jeu_id) {

        this.jeu_id = jeu_id;
    }

    public Jouet getJouet(Context context) throws IOException, ParseException {
        JouetsDAO jbdd = new JouetsDAO(context);
   
        return jbdd.read(jeu_id);
    }

    public Question getQuestion(Context context) throws IOException, ParseException {
        QuestionsDAO qbdd = new QuestionsDAO(context);
        return qbdd.read(question_id);
    }

    public int getReponseAttendue(){
        return reponse_attendue;
    }
}
