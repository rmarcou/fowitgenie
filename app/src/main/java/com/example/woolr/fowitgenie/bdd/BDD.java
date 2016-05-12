package com.example.woolr.fowitgenie.bdd;

/**
 * Created by sazm on 14/04/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.woolr.fowitgenie.Jouet;

public class BDD {

        protected static int VERSION_BDD = 1;
        protected static String NOM_BDD = "fowitBDD.db";

        protected SQLiteDatabase bdd;

        protected MaBaseSQLite maBaseSQLite;

        public BDD(Context context){
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