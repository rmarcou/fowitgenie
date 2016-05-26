package com.example.woolr.fowitgenie;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    public Game moteurJeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        moteurJeu = (Game) getIntent().getParcelableExtra("moteurJeu");

        TextView resultIHM = (TextView)findViewById(R.id.resul_textView);
        // globally
        if(moteurJeu.nbtour > 19) {
            resultIHM.setText("Je n'ai pas reussi à trouver ce à quoi vous pensiez.");
        } else {
            if(moteurJeu.getJouets().size() == 1) {
                resultIHM.setText("Votre jouet est : " + moteurJeu.getJouets().get(0).getNom());
            } else {
                if(moteurJeu.getJouets().size() == 0) {
                    resultIHM.setText("Soit vous avez répondu n'importe quoi, soit votre jouet m'est inconnue.");
                } else {
                    resultIHM.setText("Je ne comprend pas,il doit surement y avoir une erreur.");
                }

            }

        }

    }

}
