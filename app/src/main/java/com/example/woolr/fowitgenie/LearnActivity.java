package com.example.woolr.fowitgenie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.woolr.fowitgenie.bdd.JouetsDAO;
import com.example.woolr.fowitgenie.bdd.ReponsesDAO;

import org.json.simple.parser.ParseException;

import java.io.IOException;

//Activity ou l'on creer le jouet pour l'apprentissage.
public class LearnActivity extends Activity {

    public Game moteurJeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        moteurJeu = (Game) getIntent().getParcelableExtra("moteurJeu");

    }

    public void ajoutJouet(View view) {

        EditText input_name_jouet = (EditText) findViewById(R.id.input_jouet_name);
        String name_jouet = input_name_jouet.getText().toString();

        if(name_jouet.isEmpty()) {
            //si le champ n'est pas remplis message erreur.
            CharSequence text = "Le champ ne doit pas être vide!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this.getApplicationContext(), text, duration);
            toast.show();
        } else {
            try {
                moteurJeu.setJouetDAO(new JouetsDAO(this.getApplicationContext()));
                moteurJeu.setReponseDAO(new ReponsesDAO(this.getApplicationContext()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //si le champ est rempli on enregistre.
            Jouet j = new Jouet(name_jouet);
            //on insere en bdd.
            int id_j = (int) moteurJeu.getJouetDAO().create(j);

            //on met à jour l'idee des réponses enregistrer jusqua maintenant.
            for (Reponse r : moteurJeu.getQuestionsRepondu()) {
                r.setJeu_id(id_j);
            }

            //on ajoute les réponse aux quesitons en bdd pour ce nouveau jouet.
            try {
                moteurJeu.getReponseDAO().create( moteurJeu.getQuestionsRepondu());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Intent question_learn_intent = new Intent(LearnActivity.this, QuestionLearnActivity.class);
            question_learn_intent.putExtra("moteurJeu",moteurJeu);
            LearnActivity.this.startActivity(question_learn_intent);

        }

    }

}
