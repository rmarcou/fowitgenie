package com.example.woolr.fowitgenie.bdd;

/**
 * Created by sazm on 14/04/2016.
 */
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.woolr.fowitgenie.Jouet;
import com.example.woolr.fowitgenie.Question;
import com.example.woolr.fowitgenie.Reponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaBaseSQLite extends SQLiteOpenHelper {

    private static final String TABLE_JOUETS = "jouets";
    private static final String TABLE_QUESTIONS = "questions";
    private static final String TABLE_REPONSES = "reponses";

    private static Context _context;
    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_JOUETS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT NOT NULL);";
    private static final String CREATE_BDD2 = "CREATE TABLE " + TABLE_QUESTIONS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, texte TEXT NOT NULL);";
    private static final String CREATE_BDD3 = "CREATE TABLE " + TABLE_REPONSES + " (id_jouet INTEGER, id_question INTEGER, reponse_attendue TEXT NOT NULL);";

    public MaBaseSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        _context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD

        db.execSQL(CREATE_BDD);
        db.execSQL(CREATE_BDD2);
        db.execSQL(CREATE_BDD3);

        // On remplit la base avec les données en provenance du json (à terme, on download le json avant)

        InsertJouetsDatasFromJson(getStringFromJson("jouets.json"), db, TABLE_JOUETS);
        InsertQuestionsDatasFromJson(getStringFromJson("questions.json"), db, TABLE_QUESTIONS);
        InsertReponsesDatasFromJson(getStringFromJson("reponses.json"),db,TABLE_REPONSES);
    }

    private void InsertJouetsDatasFromJson(String JSONString,SQLiteDatabase db,String table) {
        Gson gson = new Gson();
        Jouet[] Jouets = gson.fromJson(JSONString, Jouet[].class);

        for(Jouet jouet : Jouets){
            String requete = "INSERT INTO "+table+" VALUES('"+jouet.getId()+"','"+jouet.getNom()+"')";
            db.execSQL(requete);
        }
    }

    private void InsertQuestionsDatasFromJson(String JSONString,SQLiteDatabase db,String table) {
        Gson gson = new Gson();
        Question[] Questions = gson.fromJson(JSONString, Question[].class);

        for(Question question : Questions){
            String requete = "INSERT INTO "+table+" VALUES('"+question.getId()+"','"+question.getTexte()+"')";
            db.execSQL(requete);
        }
    }

    private void InsertReponsesDatasFromJson(String JSONString,SQLiteDatabase db,String table) {
        Gson gson = new Gson();
        Reponse[] Reponses = gson.fromJson(JSONString, Reponse[].class);

        for(Reponse reponse : Reponses){
            String requete = "INSERT INTO "+table+" VALUES('"+reponse.getJeu_id()+"','"+reponse.getQuestion_id()+"','"+reponse.getReponseAttendue()+"')";
            db.execSQL(requete);
        }
    }

    @NonNull
    private String getStringFromJson(String fichier) {
        AssetManager assetManager = _context.getAssets();
        InputStream is = null;
        try {
            is = assetManager.open(fichier);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getStringFromInputStream(is);
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_JOUETS + ";");
        onCreate(db);
    }

}
