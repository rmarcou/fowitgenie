package com.example.woolr.fowitgenie;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PlayActivity extends Activity {


    public Game moteurJeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        moteurJeu = (Game) getIntent().getParcelableExtra("moteurJeu");
        // globally
        TextView questionIHM = (TextView)findViewById(R.id.text_question);
        questionIHM.setText(moteurJeu.getQuestion_courante().getTexte() + " ?");

        //ajouter des boutton réponse pour chaque reponse associé à la question qui sera affiché
        //la premiere question sera choisi au hasard mais par le début d'un arbre.
        //puis les question suivantes seront choisis en descendant l'arbre des quesiton tel que définis par weka

    }

    public void pressYess(View view)
    {
        //PAS BESOIN DE RECREER UNE ACTIVITE ON FAIT TOUT DANS UNE SEULE!!!!!!
        Question prochaineQuestion = new Question();
        prochaineQuestion = moteurJeu.Jouer(1);
        if(prochaineQuestion == null) {
            Intent result_intent = new Intent(PlayActivity.this, ResultActivity.class);
            result_intent.putExtra("moteurJeu",moteurJeu);
            PlayActivity.this.startActivity(result_intent);
        } else {
            TextView questionIHM = (TextView)findViewById(R.id.text_question);
            questionIHM.setText(prochaineQuestion.getTexte() + " ?");
        }

    }

    public void pressNo(View view)
    {
        //PAS BESOIN DE RECREER UNE ACTIVITE ON FAIT TOUT DANS UNE SEULE!!!!!!
        Question prochaineQuestion = new Question();
        prochaineQuestion = moteurJeu.Jouer(0);
        if(prochaineQuestion == null) {
            Intent result_intent = new Intent(PlayActivity.this, ResultActivity.class);
            result_intent.putExtra("moteurJeu",moteurJeu);
            PlayActivity.this.startActivity(result_intent);
        } else {
            TextView questionIHM = (TextView)findViewById(R.id.text_question);
            questionIHM.setText(prochaineQuestion.getTexte() + " ?");
        }
    }




}
