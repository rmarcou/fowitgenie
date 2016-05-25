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

import com.example.woolr.fowitgenie.bdd.JouetsBDD;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView BddTest = (TextView) findViewById(R.id.BddTestContent);

        BddTest.clearComposingText();
        try {

            JouetsBDD BddJouets = new JouetsBDD(this);

            ArrayList<Jouet> jouets = BddJouets.read();
            for (Jouet jouet : jouets) {
                BddTest.append("\n"+jouet.getNom());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


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

        MainActivity.this.startActivity(play_intent);
    }
}
