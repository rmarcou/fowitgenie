package com.example.woolr.fowitgenie;

/**
 * Created by woolr on 14/04/2016.
 */
public class Matrice {

    private Question question;
    private Jouet jouet;
    private int reponse_attendue;
    //private int score;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Jouet getJouet() {
        return jouet;
    }

    public void setJouet(Jouet jouet) {
        this.jouet = jouet;
    }

    public int getReponse_attendue() {
        return reponse_attendue;
    }

    public void setReponse_attendue(int reponse_attendue) {
        this.reponse_attendue = reponse_attendue;
    }
}
