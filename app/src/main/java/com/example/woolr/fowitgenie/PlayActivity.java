package com.example.woolr.fowitgenie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //ajouter des boutton réponse pour chaque reponse associé à la question qui sera affiché
        //la premiere question sera choisi au hasard mais par le début d'un arbre.
        //puis les question suivantes seront choisis en descendant l'arbre des quesiton tel que définis par weka

    }
}
