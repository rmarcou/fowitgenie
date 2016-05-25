package com.example.woolr.fowitgenie;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.woolr.fowitgenie.bdd.JouetsDAO;
import com.example.woolr.fowitgenie.bdd.QuestionsDAO;

import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by sazm on 31/03/2016.
 */
public class Reponse implements Parcelable {
    private int jeu_id;
    private int question_id;
    private int reponse_attendue;

    public Reponse() {

    }
    public Reponse(int jid, int qid, int rep) {
        jeu_id = jid;
        question_id = qid;
        reponse_attendue = rep;
    }

    protected Reponse(Parcel in) {
        jeu_id = in.readInt();
        question_id = in.readInt();
        reponse_attendue = in.readInt();
    }

    public static final Creator<Reponse> CREATOR = new Creator<Reponse>() {
        @Override
        public Reponse createFromParcel(Parcel in) {
            return new Reponse(in);
        }

        @Override
        public Reponse[] newArray(int size) {
            return new Reponse[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(jeu_id);
        dest.writeInt(question_id);
        dest.writeInt(reponse_attendue);
    }
}
