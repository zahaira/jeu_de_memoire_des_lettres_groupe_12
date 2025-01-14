/**
 * MainActivity est l'activité principale de l'application.
 * Elle permet de démarrer le jeu de mémoire des lettres avec une grille de 5x4.
 */
package com.ensa.jeu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * La classe MainActivity gère l'écran principal de l'application.
 * Elle inclut un bouton pour lancer le jeu avec une grille de 5x4.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Bouton pour lancer l'activité du jeu avec une grille de 5x4.
     */
    Button button5x4, historyButton;

    /**
     * Méthode appelée à la création de l'activité.
     * Elle initialise l'interface utilisateur et configure le bouton pour lancer le jeu.
     *
     * @param savedInstanceState État sauvegardé de l'activité, s'il existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Changer la couleur de la barre de navigation
        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        // Initialiser le bouton pour afficher l'historique
        historyButton = findViewById(R.id.historyButton);
        historyButton.setOnClickListener(v -> {
            // Lancer l'activité VictoryHistoryActivity
            Intent intent = new Intent(MainActivity.this, VictoryHistoryActivity.class);
            startActivity(intent);
        });

        // Initialiser le bouton et définir son comportement au clic
        button5x4 = (Button) findViewById(R.id.button1);
        button5x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGame5x4Activity();
            }
        });
    }
    /**
     * Lance l'activité Game5x4Activity pour démarrer le jeu avec une grille de 5x4.
     */
    public void openGame5x4Activity() {
        Intent intent5x4 = new Intent(this, Game5x4Activity.class);
        startActivity(intent5x4);
    }
}
