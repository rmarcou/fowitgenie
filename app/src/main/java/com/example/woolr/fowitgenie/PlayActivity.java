package com.example.woolr.fowitgenie;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Game moteurJeu = (Game) getIntent().getParcelableExtra("moteurJeu");
        // globally
        TextView myAwesomeTextView = (TextView)findViewById(R.id.text_question);
        myAwesomeTextView.setText(moteurJeu.getQuestion_courante().getTexte());

        //ajouter des boutton réponse pour chaque reponse associé à la question qui sera affiché
        //la premiere question sera choisi au hasard mais par le début d'un arbre.
        //puis les question suivantes seront choisis en descendant l'arbre des quesiton tel que définis par weka

    }




}
