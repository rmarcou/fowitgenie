package com.example.woolr.fowitgenie;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by woolr on 14/04/2016.
 */
public class Game {

    private ArrayList<Question> questions;
    private ArrayList<Jouet> jouets;
    private ArrayList<Matrice> matrice_jeu;
    private Question question_courante;
    public ArrayList<Score> scores;
    public int nbtour;


    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Jouet> getJouets() {
        return jouets;
    }

    public void setJouets(ArrayList<Jouet> jouets) {
        this.jouets = jouets;
    }

    public ArrayList<Matrice> getMatrice_jeu() {
        return matrice_jeu;
    }

    public void setMatrice_jeu(ArrayList<Matrice> matrice_jeu) {
        this.matrice_jeu = matrice_jeu;
    }

    public Question getQuestion_courante() {
        return question_courante;
    }

    public void setQuestion_courante(Question question_courante) {
        this.question_courante = question_courante;
    }

    //initialisation de la classe.
    public void init() {
        nbtour = 1;
        //faire le CRUD pour recupperer tout les jouets et toutes les questions

        //fin CRUD
        this.setMatrice_jeu(new ArrayList<Matrice>());
        Matrice m = new Matrice();
        int reponse_utilisateur = 0;
        Score s = new Score();
        for (Question q : this.getQuestions()) {
            for (Jouet j : this.getJouets()) {
                m.setQuestion(q);
                m.setJouet(j);
                m.setReponse_attendue(0);
                this.getMatrice_jeu().add(m);
                s.setId_question(q.getId());
                scores.add(s);
            }
        }

        //on met à jour la premiére question.
        Random rand = new Random();
        int q0 = rand.nextInt(getQuestions().size() + 1);
        this.setQuestion_courante(getQuestions().get(q0));

    }

    //Lance un tour de jeu.
    public void Jouer() {
        //dans cette fonction on lancer le tour de jeu;
        //on augmente le tour si on est pas arrivé à 20 tour
        if (nbtour > 20) {
            return;
        } else nbtour++;

        //puis on choisis calcul le score des questions pour choisir la prochaine


        this.setQuestion_courante(new Question());


    }

    //retourne la question la plus pertinente pour le prochain tour.
    public Question calcul_scrore() {
        Score s;
        //on compte le nombre de oui et de non pour chaque question pour chaque objet qui reste.
        for (Matrice m : this.getMatrice_jeu()) {
            s = scores.get(m.getQuestion().getId());
            if(s != null) {
                if (m.getReponse_attendue() == 0) {
                    s.setNon(s.getOui() + 1);
                } else {
                    s.setOui(s.getNon() + 1);
                }
            } else {
                s = new Score();
                scores.add(s);
                if (m.getReponse_attendue() == 0) {
                    s.setNon(s.getOui() + 1);
                } else {
                    s.setOui(s.getNon() + 1);
                }
            }
        }

        //on calcul les score de chaque question.
        int tot_score = 0;
        int max_score = 0;
        int id_question_max_score = 0;
        for (Score sc: scores) {
            tot_score = (sc.getOui()+1) * (sc.getNon()+1);
            sc.setScore(tot_score);
            if (max_score < tot_score) {
                id_question_max_score = sc.getId_question();
                max_score = tot_score;
            }
        }

        return this.getQuestions().get(id_question_max_score);
    }

    public void traitement_reponse(int reponse_utilisateur) {
        //on recupere la réponse et on la traite
        for (Matrice m : getMatrice_jeu()) {
            int id_question = m.getQuestion().getId();
            if (id_question == this.getQuestion_courante().getId()) {
                if (m.getReponse_attendue() != reponse_utilisateur) {
                    this.getMatrice_jeu().remove(id_question);
                    scores.remove(id_question);
                    this.questions.remove(id_question);
                }
            }
        }
    }


}
