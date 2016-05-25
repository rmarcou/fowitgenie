package com.example.woolr.fowitgenie;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.woolr.fowitgenie.bdd.JouetsDAO;
import com.example.woolr.fowitgenie.bdd.QuestionsDAO;
import com.example.woolr.fowitgenie.bdd.ReponsesDAO;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by woolr on 14/04/2016.
 */
public class Game implements Parcelable {

    //Context de l'activité en cours.
    private Context context;

    private ArrayList<Question> questions;
    private ArrayList<Jouet> jouets;
    private ArrayList<Reponse> matrice_jeu;
    private Question question_courante;
    public ArrayList<Score> scores;
    public int nbtour = 1;

    //DAO
    private JouetsDAO jouetDAO;
    private QuestionsDAO questioDAO;
    private ReponsesDAO reponseDAO;

    public Game() {
        questions = new ArrayList<Question>();
        jouets = new ArrayList<Jouet>();
        scores = new ArrayList<Score>();
        matrice_jeu = new ArrayList<Reponse>();
    }


    public Game(Context c) throws IOException, ParseException {
        context = c;
        init();
    }

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

    public ArrayList<Reponse> getMatrice_jeu() {
        return matrice_jeu;
    }

    public void setMatrice_jeu(ArrayList<Reponse> matrice_jeu) {
        this.matrice_jeu = matrice_jeu;
    }

    public Question getQuestion_courante() {
        return question_courante;
    }

    public void setQuestion_courante(Question question_courante) {
        this.question_courante = question_courante;
    }

    //initialisation de la classe.
    public void init() throws IOException, ParseException {
        nbtour = 1;
        //faire le CRUD pour recupperer tout les jouets et toutes les questions
        /*this.setJouetDAO(new JouetsDAO(context));
        this.setJouets(getJouetDAO().read());
        this.setQuestioDAO(new QuestionsDAO(context));
        this.setQuestions(getQuestioDAO().read());
        this.setReponseDAO(new ReponsesDAO(context));*/
        //en attendant
        questions = new ArrayList<Question>();
        jouets = new ArrayList<Jouet>();
        scores = new ArrayList<Score>();
        matrice_jeu = new ArrayList<Reponse>();

        //fin CRUD
        this.setMatrice_jeu(new ArrayList<Reponse>());
        Reponse m = new Reponse();
        int reponse_utilisateur = 0;
        Score s = new Score();

        //ancienne version
        /*for (int q : this.getQuestions()) {
            for (int j : this.getJouets()) {
                m.setQuestion_id(q);
                m.setJeu_id(j);
                m.setReponse_attendue(0);
                this.getMatrice_jeu().add(m);
                s.setId_question(q.getId());
                scores.add(s);
            }
        }*/

        //nouvelle version
        /*for( Reponse r : getReponseDAO().read()) {
            this.getMatrice_jeu().add(r);
            s.setId_question(r.getQuestion_id());
            scores.add(s);
        }*/

        //on met à jour la premiére question.
        /*Random rand = new Random();
        int q0 = rand.nextInt(getQuestions().size() + 1);
        this.setQuestion_courante(getQuestions().get(q0));*/

    }

    //Lance un tour de jeu.
    public Question Jouer(int reponse_joueur) {
        //dans cette fonction on lance le tour de jeu;
        //on augmente le tour si on est pas arrivé à 20 tour
        if (nbtour > 20) {
            //appeller methode de fin de partie.
            fin_de_partie();
            return null;
        } else nbtour++;

        //puis on choisis/calcul le score des questions pour choisir la prochaine
        traitement_reponse(reponse_joueur);
        this.setQuestion_courante(calcul_scrore());
        return this.getQuestion_courante();

    }

    private void fin_de_partie() {

    }

    //retourne la question la plus pertinente pour le prochain tour.
    public Question calcul_scrore() {
        Score s;
        //on compte le nombre de oui et de non pour chaque question pour chaque objet qui reste.
        for (Reponse m : this.getMatrice_jeu()) {
            s = scores.get(m.getQuestion_id());
            if (s != null) {
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
        for (Score sc : scores) {
            tot_score = (sc.getOui() + 1) * (sc.getNon() + 1);
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
        for (Reponse m : getMatrice_jeu()) {
            int id_question = m.getQuestion_id();
            if (id_question == this.getQuestion_courante().getId()) {
                if (m.getReponse_attendue() != reponse_utilisateur) {
                    this.getMatrice_jeu().remove(id_question);
                    scores.remove(id_question);
                    this.questions.remove(id_question);
                }
            }
        }
    }


    public JouetsDAO getJouetDAO() {
        return jouetDAO;
    }

    public void setJouetDAO(JouetsDAO jouetDAO) {
        this.jouetDAO = jouetDAO;
    }

    public QuestionsDAO getQuestioDAO() {
        return questioDAO;
    }

    public void setQuestioDAO(QuestionsDAO questioDAO) {
        this.questioDAO = questioDAO;
    }

    public ReponsesDAO getReponseDAO() {
        return reponseDAO;
    }

    public void setReponseDAO(ReponsesDAO reponseDAO) {
        this.reponseDAO = reponseDAO;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(questions);
        dest.writeTypedList(jouets);
        dest.writeTypedList(matrice_jeu);
        dest.writeValue(question_courante);
        dest.writeTypedList(scores);

        dest.writeInt(nbtour);
    /*
        dest.writeTypedObject(jouetDAO);
        dest.writeTypedObject(questioDAO);
        dest.writeTypedObject(reponseDAO);*/
    }

    public Game(Parcel in) {
        this();

        in.readTypedList(questions, Question.CREATOR);
        in.readTypedList(jouets, Jouet.CREATOR);
        in.readTypedList(matrice_jeu, Reponse.CREATOR);
        question_courante = (Question) in.readValue(Question.class.getClassLoader());

        in.readTypedList(scores, Score.CREATOR);

        this.nbtour = in.readInt();

        /*
        this.jouetDAO = in.readValue();
        this.questioDAO = in.readValue();
        this.reponseDAO = in.readValue();*/
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>()
    {
        @Override
        public Game createFromParcel(Parcel source)
        {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size)
        {
            return new Game[size];
        }
    };




}
