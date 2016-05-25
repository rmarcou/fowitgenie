package com.example.woolr.fowitgenie;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sazm on 31/03/2016.
 */
public class Jouet implements Parcelable {
    private int id;
    private String nom;

    public Jouet(){
    }
    public Jouet(String nom){
        this.nom = nom;
    }

    protected Jouet(Parcel in) {
        id = in.readInt();
        nom = in.readString();
    }

    public static final Creator<Jouet> CREATOR = new Creator<Jouet>() {
        @Override
        public Jouet createFromParcel(Parcel in) {
            return new Jouet(in);
        }

        @Override
        public Jouet[] newArray(int size) {
            return new Jouet[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
    }
}
