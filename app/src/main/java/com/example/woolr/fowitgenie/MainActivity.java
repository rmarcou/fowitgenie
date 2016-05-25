package com.example.woolr.fowitgenie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.woolr.fowitgenie.bdd.JouetsDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Game lejeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lejeu = new Game(this.getApplicationContext());
        Jouet j = new Jouet("Billes");
        j.setId(1);
        lejeu.getJouets().add(j);

        Question q = new Question("Est ce que c'est rond?");
        q.setId(1);
        lejeu.getQuestions().add(q);

        Reponse r = new Reponse(j.getId(), q.getId(), 1);
        lejeu.getMatrice_jeu().add(r);

        lejeu.setQuestion_courante(q);

/*      JouetsBDD BddJouets = new JouetsBDD(this);

        BddJouets.erase();

        Jouet jouet = new Jouet("test1");
        Jouet jouet2 = new Jouet("test2");

        BddJouets.create(jouet);
        BddJouets.create(jouet2);

        ArrayList<Jouet> jouetsFromBdd = BddJouets.read();


        TextView BddTest = (TextView) findViewById(R.id.BddTestContent);

        for(Jouet j : jouetsFromBdd) {
            BddTest.append(j.getNom());
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchplay(View view)
    {
        Intent play_intent = new Intent(MainActivity.this, PlayActivity.class);
        //pour passer des parametre a l'acitivté d'aprés.
        //intent.putExtra("key", value); //Optional parameters

        play_intent.putExtra("lejeu", lejeu);
        MainActivity.this.startActivity(play_intent);

    }
}
