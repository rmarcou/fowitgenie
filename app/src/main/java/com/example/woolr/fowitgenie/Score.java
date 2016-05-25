package com.example.woolr.fowitgenie;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by woolr on 14/04/2016.
 */
public class Score  implements Parcelable {

    private int id_question;
    //nombre de jouet qui ont oui pour cette question
    private int oui = 0;
    //nombre de jouet qui ont non pour cette question
    private int non = 0;
    private int score = 0;

    protected Score() {
    }

    protected Score(Parcel in) {
        id_question = in.readInt();
        oui = in.readInt();
        non = in.readInt();
        score = in.readInt();
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_question);
        dest.writeInt(oui);
        dest.writeInt(non);
        dest.writeInt(score);
    }
}
