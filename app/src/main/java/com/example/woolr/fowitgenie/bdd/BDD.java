package com.example.woolr.fowitgenie.bdd;

/**
 * Created by sazm on 14/04/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.woolr.fowitgenie.Jouet;
import com.example.woolr.fowitgenie.Question;
import com.example.woolr.fowitgenie.Reponse;

import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class BDD {

        protected static int VERSION_BDD = 1;
        protected static String NOM_BDD = "fowitBDD42.db";

        protected SQLiteDatabase bdd;

        protected MaBaseSQLite maBaseSQLite;

        public BDD(Context context) throws IOException, ParseException {
            //On crée la BDD et sa table
            maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
            this.open();
        }

        public void open(){
            //on ouvre la BDD en écriture
            bdd = maBaseSQLite.getWritableDatabase();
        }

        public void close(){
            //on ferme l'accès à la BDD
            bdd.close();
        }

        public SQLiteDatabase getBDD(){
            return bdd;
        }

    }