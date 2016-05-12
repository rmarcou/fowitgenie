package com.example.woolr.fowitgenie;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //ajouter des boutton réponse pour chaque reponse associé à la question qui sera affiché
        //la premiere question sera choisi au hasard mais par le début d'un arbre.
        //puis les question suivantes seront choisis en descendant l'arbre des quesiton tel que définis par weka

    }


    /*Fonction calculValeurNoeud(Noeud n)
    {
        Si n est une feuille alors
        retourner eval(n);
        Sinon
        Si c'est un noeud max alors
        retourner le maximum des évaluations des fils du noeud
            sinon
        retourner le minimum des évaluations des fils du noeud
        Fin Si
    }*/

    /*public void valueNode(Node n) {
        if (n instanceof Leaf) { //c'est une feuille (noeud sans enfant)
            return eval(n);
        } else { //c'est un noeud
            if() {

            } else {

            }
        }
    }


    int Max(int[][] jeu,int profondeur) {
        if(profondeur == 0 || gagnant(jeu)!=0) {
            return eval(jeu);
        }
        int max = -10000;
        int i,j,tmp;
        for(i=0;i<3;i++) {
            for(j=0;j<3;j++) {
                if(jeu[i][j] == 0) {
                    jeu[i][j] = 2;
                    tmp = Min(jeu,profondeur-1);

                    if(tmp > max) {
                        max = tmp;
                    }
                    jeu[i][j] = 0;
                }
            }
        }
        return max;
    }
    int Min(int[][] jeu,int profondeur) {
        if(profondeur == 0 || gagnant(jeu)!=0) {
            return eval(jeu);
        }
        int min = 10000;
        int i,j,tmp;
        for(i=0;i<3;i++) {
            for(j=0;j<3;j++) {
                if(jeu[i][j] == 0) {
                    jeu[i][j] = 1;
                    tmp = Max(jeu, profondeur -1);
                    if(tmp < min) {
                        min = tmp;
                    }
                    jeu[i][j] = 0;
                }
            }
        }
        return min;
    }*/


}
