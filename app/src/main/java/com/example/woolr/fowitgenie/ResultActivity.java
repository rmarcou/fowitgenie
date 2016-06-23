package com.example.woolr.fowitgenie;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class ResultActivity extends Activity {

    public Game moteurJeu;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        moteurJeu = (Game) getIntent().getParcelableExtra("moteurJeu");

        TextView resultIHM = (TextView) findViewById(R.id.resul_textView);
        // globally
        if (moteurJeu.nbtour > 19) {
            resultIHM.setText("Je n'ai pas reussi à trouver ce à quoi vous pensiez. Voulez vous renseignez le jouet auqel vous pensiez?");
        } else {
            if (moteurJeu.getJouets().size() == 1) {
                resultIHM.setText("Votre jouet est : " + moteurJeu.getJouets().get(0).getNom());
            } else {
                if (moteurJeu.getJouets().size() == 0) {
                    resultIHM.setText("Je ne connais pas votre jouer. Cliquez sur non pour renseignez votre jouet.");
                } else {
                    resultIHM.setText("Je ne connais pas votre jouer. Cliquez sur non pour renseignez votre jouet.");
                }

            }

        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    ///////////////////////////////////APPRENTISSAGE/////////////////////////////////
    public void pressYess(View view) {
        //Si l'utilisateur dit non on revient dans l'accueil.
        finish();
        Intent main_intent = new Intent(ResultActivity.this, MainActivity.class);
        ResultActivity.this.startActivity(main_intent);
    }

    public void pressNo(View view) {
        Intent apprentissage_intent = new Intent(ResultActivity.this, LearnActivity.class);
        apprentissage_intent.putExtra("moteurJeu", moteurJeu);
        ResultActivity.this.startActivity(apprentissage_intent);
    }

}
