package com.example.woolr.fowitgenie;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sazm on 31/03/2016.
 */
public class Question implements Parcelable {
    private int id;
    private String texte;

    public Question() {

    }

    public Question(String t) {
        texte = t;
    }

    protected Question(Parcel in) {
        id = in.readInt();
        texte = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(texte);
    }

}
