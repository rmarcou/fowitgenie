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

    //aprentissage
    private ArrayList<Reponse> questionsRepondu;

    //DAO
    private JouetsDAO jouetDAO;
    private QuestionsDAO questioDAO;
    private ReponsesDAO reponseDAO;

    public Game() {
        questions = new ArrayList<Question>();
        jouets = new ArrayList<Jouet>();
        scores = new ArrayList<Score>();
        matrice_jeu = new ArrayList<Reponse>();
        questionsRepondu = new ArrayList<Reponse>();
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
        questions = new ArrayList<Question>();
        jouets = new ArrayList<Jouet>();
        scores = new ArrayList<Score>();
        matrice_jeu = new ArrayList<Reponse>();
        questionsRepondu = new ArrayList<Reponse>();

        //faire le CRUD pour recupperer tout les jouets et toutes les questions
        this.setJouetDAO(new JouetsDAO(context));
        this.setJouets(getJouetDAO().read());
        this.setQuestioDAO(new QuestionsDAO(context));
        this.setQuestions(getQuestioDAO().read());

        this.setReponseDAO(new ReponsesDAO(context));
        //fin CRUD

        this.setMatrice_jeu(new ArrayList<Reponse>());
        Reponse m = new Reponse();
        int reponse_utilisateur = 0;
        Score s = new Score();

        //nouvelle version
        for (Reponse r : getReponseDAO().read()) {
            this.getMatrice_jeu().add(r);
            s.setId_question(r.getQuestion_id());
        }

        //on prepare la liste des question repondu pour APPRENTISSAGE
        for(Question q : questions) {
            Reponse r_repondu = new Reponse(jouets.size()+1,q.getId(),0);
            questionsRepondu.add(r_repondu);
        }

        //on met à jour la premiére question.
        Random rand = new Random();
        int q0 = rand.nextInt(getQuestions().size());
        this.setQuestion_courante(getQuestions().get(q0));

    }

    //Lance un tour de jeu.
    public Question Jouer(int reponse_joueur) {
        //dans cette fonction on lance le tour de jeu;

        Reponse rNouvelObjet = new Reponse();
        rNouvelObjet.setQuestion_id(question_courante.getId());
        rNouvelObjet.setJeu_id(jouets.size()+1);


        //on sauvegardes les question repondu pour laprentissage
        //this.questionsRepondu.add(this.questions.get(id_question));
        for(Reponse r_repondu : questionsRepondu){
            if(r_repondu.getQuestion_id() == rNouvelObjet.getQuestion_id()){
                r_repondu.setReponse_attendue(reponse_joueur);
            }
        }
        //questionsRepondu.add(rNouvelObjet);

        //puis on choisis/calcul le score des questions pour choisir la prochaine
        int finirpartie = traitement_reponse(reponse_joueur);
        Question questionSuivante = calcul_scrore();
        this.setQuestion_courante(questionSuivante);

        //Si on a plus qu'un jouet dans la liste on a finit!!
        if (finirpartie > 0) {
            // on finit la partie.
            return null;
        }

        //on augmente le tour si on est pas arrivé à 20 tour
        if (nbtour > 20) {
            return null;
        } else nbtour++;

        return this.getQuestion_courante();

    }

    private void fin_de_partie() {

    }

    //retourne la question la plus pertinente pour le prochain tour.
    public Question calcul_scrore() {
        this.scores = new ArrayList<>();
        Score s;
        //on compte le nombre de oui et de non pour chaque question pour chaque objet qui reste.
        for (Question q : this.questions) {
            s = new Score();
            for (Reponse m : this.getMatrice_jeu()) {

                if (q.getId() == m.getQuestion_id()) {
                    if (m.getReponse_attendue() == 0) {
                        s.setNon(s.getOui() + 1);
                    } else {
                        s.setOui(s.getNon() + 1);
                    }
                }
            }
            this.scores.add(s);
        }

        //on calcul les score de chaque question.
        int tot_score = 0;
        int max_score = 0;
        int id_question_max_score = 0;
        //les entree sont decalé car les tableaux commencent à 0 en java et pas a 1

        for (Score sc : this.scores) {
            tot_score = (sc.getOui() + 1) * (sc.getNon() + 1);
            sc.setScore(tot_score);
            if (max_score < tot_score) {
                id_question_max_score = sc.getId_question();
                max_score = tot_score;
            }
            tot_score = 0;
        }
        int id_question_max_score_minus = 0;
        if (id_question_max_score > 0) {
            id_question_max_score_minus = id_question_max_score - 1;
        }

        if (this.getQuestions().size() == 0) {
            return null;
        }
        return this.getQuestions().get(id_question_max_score_minus);
    }

    public int traitement_reponse(int reponse_utilisateur) {
        //on recupere la réponse et on la traite

        int id_question = 0;
        int id_jouet = 0;
        boolean delete_j = false;
        ArrayList<Integer> ids_jouets_a_supprimer = new ArrayList<>();
        ArrayList<Reponse> reponse_a_supprimer = new ArrayList<>();

        for (Reponse m : getMatrice_jeu()) {
            reponse_a_supprimer.add(m);

            id_question = (m.getQuestion_id());
            id_jouet = (m.getJeu_id());


            if (id_question == this.getQuestion_courante().getId()) {

                if (m.getReponse_attendue() != reponse_utilisateur) {
                    //Si on a pas la reponse attendu on vire le objet associé.
                    ids_jouets_a_supprimer.add(id_jouet);
                }
            }
        }

        //on supprime en dehors de la boucle pour eviter des effets de bord de supression en chaine (le size qui se reduit au fur et a mesure.
        //on supprime la question dans tout les cas pour ne plus la reposer.
        int id_question_courante = this.getQuestion_courante().getId();
        for (Question question_a_supprimer : this.questions) {

            if (question_a_supprimer.getId() == id_question_courante) {
                this.questions.remove(question_a_supprimer);
                break;
            }

        }

        for (Integer id : ids_jouets_a_supprimer) {

            for (Jouet jouet_a_supprimer : this.jouets) {
                if (jouet_a_supprimer.getId() == id) {
                    this.jouets.remove(jouet_a_supprimer);
                    break;
                }
            }

            for (Reponse rep : reponse_a_supprimer) {
                if(rep.getJeu_id() == id) {
                    getMatrice_jeu().remove(rep);
                }
            }

        }

        if (this.jouets.size() < 2) {
            return 1;//on finit la partie
        } else {
            return 0;//on continue
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

        dest.writeTypedList(questionsRepondu);

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

        in.readTypedList(questionsRepondu, Reponse.CREATOR);

        this.nbtour = in.readInt();

        /*
        this.jouetDAO = in.readValue();
        this.questioDAO = in.readValue();
        this.reponseDAO = in.readValue();*/
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public ArrayList<Reponse> getQuestionsRepondu() {
        return questionsRepondu;
    }

    public void setQuestionsRepondu(ArrayList<Reponse> questionsRepondu) {
        this.questionsRepondu = questionsRepondu;
    }
}
