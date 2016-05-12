package com.example.woolr.fowitgenie.bdd;

/**
 * Created by sazm on 14/04/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MaBaseSQLite extends SQLiteOpenHelper {

    private static final String TABLE_JOUETS = "jouets";
    private static final String COL_ID = "id";
    private static final String COL_NOM = "nom";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_JOUETS + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOM + " TEXT NOT NULL);";

    public MaBaseSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_JOUETS + ";");
        onCreate(db);
    }

}
