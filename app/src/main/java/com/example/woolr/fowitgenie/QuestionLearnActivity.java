package com.example.woolr.fowitgenie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.woolr.fowitgenie.bdd.JouetsDAO;
import com.example.woolr.fowitgenie.bdd.QuestionsDAO;
import com.example.woolr.fowitgenie.bdd.ReponsesDAO;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class QuestionLearnActivity extends AppCompatActivity {

    public Game moteurJeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_learn);
        moteurJeu = (Game) getIntent().getParcelableExtra("moteurJeu");

    }

    public void ajoutQuestionYes(View view) {

        EditText input_name_question = (EditText) findViewById(R.id.editTextQuestion);
        String name_question = input_name_question.getText().toString();

        if(name_question.isEmpty()) {
            //si le champ n'est pas remplis message erreur.
            CharSequence text = "Le champ ne doit pas être vide!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this.getApplicationContext(), text, duration);
            toast.show();
        } else {


            //on ajoute les réponse aux quesitons en bdd pour ce nouveau jouet.
            try {
                moteurJeu.setQuestioDAO(new QuestionsDAO(this.getApplicationContext()));
                moteurJeu.setReponseDAO(new ReponsesDAO(this.getApplicationContext()));

                //si le champ est rempli on enregistre.
                Question q = new Question(name_question);
                //on insere en bdd.
                int id_quqestion = (int) moteurJeu.getQuestioDAO().create(q);
                int id_jeu = moteurJeu.getQuestionsRepondu().get(1).getJeu_id();
                //on met à jour l'idee des réponses enregistrer jusqua maintenant.
                Reponse reponse_learn = new Reponse(id_jeu, id_quqestion, 1);

                moteurJeu.getReponseDAO().create(reponse_learn);

                ArrayList<Reponse> list_reponse_question_learn = new ArrayList<>();
                for(Jouet jouet_exist : moteurJeu.getJouets()) {
                    Reponse rep_question_learn = new Reponse(jouet_exist.getId(),id_quqestion,0);
                    list_reponse_question_learn.add(rep_question_learn);
                }

                moteurJeu.getReponseDAO().create(list_reponse_question_learn);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


            CharSequence text_else = "Merci de m'aider à m'améliorer.";
            int duration_else = Toast.LENGTH_SHORT;
            Toast toast_else = Toast.makeText(this.getApplicationContext(), text_else, duration_else);
            toast_else.show();

            Intent question_learn_intent = new Intent(QuestionLearnActivity.this, MainActivity.class);
            question_learn_intent.putExtra("moteurJeu",moteurJeu);
            QuestionLearnActivity.this.startActivity(question_learn_intent);

        }

    }

    public void ajoutQuestionNo(View view) {

        EditText input_name_question = (EditText) findViewById(R.id.editTextQuestion);
        String name_question = input_name_question.getText().toString();

        if(name_question.isEmpty()) {
            //si le champ n'est pas remplis message erreur.
            CharSequence text = "Le champ ne doit pas être vide!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this.getApplicationContext(), text, duration);
            toast.show();
        } else {


            //on ajoute les réponse aux quesitons en bdd pour ce nouveau jouet.
            try {
                moteurJeu.setQuestioDAO(new QuestionsDAO(this.getApplicationContext()));
                moteurJeu.setReponseDAO(new ReponsesDAO(this.getApplicationContext()));

                //si le champ est rempli on enregistre.
                Question q = new Question(name_question);
                //on insere en bdd.
                int id_quqestion = (int) moteurJeu.getQuestioDAO().create(q);
                int id_jeu = moteurJeu.getQuestionsRepondu().get(1).getJeu_id();
                //on met à jour l'idee des réponses enregistrer jusqua maintenant.
                Reponse reponse_learn = new Reponse(id_jeu, id_quqestion, 0);

                moteurJeu.getReponseDAO().create(reponse_learn);

                ArrayList<Reponse> list_reponse_question_learn = new ArrayList<>();
                for(Jouet jouet_exist : moteurJeu.getJouets()) {
                    Reponse rep_question_learn = new Reponse(jouet_exist.getId(),id_quqestion,0);
                    list_reponse_question_learn.add(rep_question_learn);
                }

                moteurJeu.getReponseDAO().create(list_reponse_question_learn);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


            CharSequence text_else = "Merci de m'aider à m'améliorer.";
            int duration_else = Toast.LENGTH_SHORT;
            Toast toast_else = Toast.makeText(this.getApplicationContext(), text_else, duration_else);
            toast_else.show();

            Intent question_learn_intent = new Intent(QuestionLearnActivity.this, MainActivity.class);
            question_learn_intent.putExtra("moteurJeu",moteurJeu);
            QuestionLearnActivity.this.startActivity(question_learn_intent);

        }

    }

}
