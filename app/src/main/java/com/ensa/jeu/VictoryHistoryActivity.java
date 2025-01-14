package com.ensa.jeu;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Activité qui affiche l'historique des victoires dans une liste
 * et permet de commencer une nouvelle partie.
 */
public class VictoryHistoryActivity extends AppCompatActivity {

    /**
     * Méthode appelée lors de la création de l'activité.
     * Initialise l'interface utilisateur et charge les données d'historique.
     *
     * @param savedInstanceState l'état sauvegardé de l'activité, s'il y en a un.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory_history);

        // Références aux éléments de l'interface utilisateur
        ListView historyListView = findViewById(R.id.historyListView);
        Button playButton = findViewById(R.id.playButton);

        // Initialiser la base de données
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Récupérer les données de victoires
        ArrayList<String> victories = new ArrayList<>();
        Cursor cursor = dbHelper.getAllVictories();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Récupération des informations de victoire
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                int flips = cursor.getInt(cursor.getColumnIndexOrThrow("flips"));
                victories.add("Date: " + date + ", Flips: " + flips);
            } while (cursor.moveToNext());
            cursor.close();
        }

        // Configurer l'adaptateur pour afficher les données dans la liste
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, victories);
        historyListView.setAdapter(adapter);

        // Configuration du bouton pour commencer une nouvelle partie
        playButton.setOnClickListener(v -> {
            Intent intent = new Intent(VictoryHistoryActivity.this, Game5x4Activity.class);
            startActivity(intent);
        });
    }
}
