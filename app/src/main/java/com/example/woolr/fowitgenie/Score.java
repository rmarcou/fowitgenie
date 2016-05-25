package com.example.woolr.fowitgenie;

/**
 * Created by woolr on 14/04/2016.
 */
public class Score {

    private int id_question;
    //nombre de jouet qui ont oui pour cette question
    private int oui = 0;
    //nombre de jouet qui ont non pour cette question
    private int non = 0;
    private int score = 0;

    public int getOui() {
        return oui;
    }

    public void setOui(int oui) {
        this.oui = oui;
    }

    public int getNon() {
        return non;
    }

    public void setNon(int non) {
        this.non = non;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }
}
