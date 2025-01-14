package com.ensa.jeu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Activity that implements a memory matching game with a 5x4 board layout.
 * The game presents a set of face-down cards, and the player needs to match pairs of cards.
 * After all pairs are matched, a win dialog is shown.
 */
public class Game5x4Activity extends AppCompatActivity implements TaskCompleted {

    /**
     * List to hold image resource IDs used for cards in the game.
     */
    ArrayList<Integer> img_ids;

    /**
     * Constant for the number of unique images in the game.
     */
    final int NUM_IMGS = 10;

    /**
     * Builder for showing alert dialogs.
     */
    AlertDialog.Builder builder;

    /**
     * List of image buttons representing cards on the game board.
     */
    ArrayList<ImageButton> buttons;

    /**
     * A map linking each card button to its corresponding card data.
     */
    Map<ImageButton, Card> hm;

    /**
     * Set of matched cards that are disabled after being matched.
     */
    Set<ImageButton> matchedCards;

    /**
     * Flag to lock the board when two cards are flipped and awaiting check.
     */
    boolean isBoardLocked;
    /**
     * The first card that was flipped.
     */
    ImageButton firstCard;

    /**
     * The second card that was flipped for comparison.
     */
    ImageButton secondCard;

    /**
     * TextView to display the number of flips.
     */
    TextView flips;

    /**
     * TextView to display the number of matched pairs.
     */
    TextView matches;

    /**
     * Counter for the number of flips made by the player.
     */
    int flip_count = 0;

    /**
     * Counter for the number of matched pairs found.
     */
    int match_count = 0;

    /**
     * Button that shuffles the cards when clicked.
     */
    Button shuffle;

    /**
     * SoundPool instance for playing sound effects.
     */
    private SoundPool soundPool;

    /**
     * Sound ID for the match sound effect.
     */
    private int matchSoundId;

    /**
     * Sound ID for the win sound effect.
     */
    private int winSoundId;

    /**
     * Called when the activity is created. Initializes the game board and buttons.
     *
     * @param savedInstanceState The saved instance state bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5x4);

        builder = new AlertDialog.Builder(this);

        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retour à la page principale
                Intent intent = new Intent(Game5x4Activity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Facultatif, ferme l'activité actuelle
            }
        });

        // Configure SoundPool
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();

        // Load sound effects
        matchSoundId = soundPool.load(this, R.raw.match_sound, 1);
        winSoundId = soundPool.load(this, R.raw.win_sound, 1);

        flips = findViewById(R.id.textView1);
        matches = findViewById(R.id.textView2);

        flips.setText("Flips: " + flip_count);
        matches.setText("Matches: " + match_count);

        img_ids = new ArrayList<>();

        // Load drawable resources into img_ids
        img_ids.add(R.drawable.card_front_a);
        img_ids.add(R.drawable.card_front_b);
        img_ids.add(R.drawable.card_front_c);
        img_ids.add(R.drawable.card_front_d);
        img_ids.add(R.drawable.card_front_e);
        img_ids.add(R.drawable.card_front_f);
        img_ids.add(R.drawable.card_front_g);
        img_ids.add(R.drawable.card_front_h);
        img_ids.add(R.drawable.card_front_i);
        img_ids.add(R.drawable.card_front_j);

        shuffle = findViewById(R.id.button_shuffle);

        buttons = new ArrayList<>();
        initializeBoard(buttons);

        // Add listener for shuffle button
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuffleBoard();
            }
        });
    }

    /**
     * Initializes the game board by assigning image buttons and setting up the cards.
     * Each card is paired with an image, shuffled, and listeners are set for user interaction.
     *
     * @param buttons The list of image buttons representing the game board.
     */
    private void initializeBoard(ArrayList<ImageButton> buttons) {
        // Assign image buttons
        buttons.add(findViewById(R.id.imageButton1));
        buttons.add(findViewById(R.id.imageButton2));
        buttons.add(findViewById(R.id.imageButton3));
        buttons.add(findViewById(R.id.imageButton4));
        buttons.add(findViewById(R.id.imageButton5));
        buttons.add(findViewById(R.id.imageButton6));
        buttons.add(findViewById(R.id.imageButton7));
        buttons.add(findViewById(R.id.imageButton8));
        buttons.add(findViewById(R.id.imageButton9));
        buttons.add(findViewById(R.id.imageButton10));
        buttons.add(findViewById(R.id.imageButton11));
        buttons.add(findViewById(R.id.imageButton12));
        buttons.add(findViewById(R.id.imageButton13));
        buttons.add(findViewById(R.id.imageButton14));
        buttons.add(findViewById(R.id.imageButton15));
        buttons.add(findViewById(R.id.imageButton16));
        buttons.add(findViewById(R.id.imageButton17));
        buttons.add(findViewById(R.id.imageButton18));
        buttons.add(findViewById(R.id.imageButton19));
        buttons.add(findViewById(R.id.imageButton20));

        Collections.shuffle(buttons, new Random());

        hm = new HashMap<>();
        matchedCards = new HashSet<>();
        isBoardLocked = false;
        firstCard = null;

        // Assign image IDs to buttons
        for (int i = 0; i < buttons.size(); i++) {
            final int img_id = img_ids.get(i % img_ids.size());
            final ImageButton card = buttons.get(i);
            hm.put(card, new Card(img_id, true));

            card.setImageResource(R.drawable.card_back);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!canFlipCard(card))
                        return;

                    flips.setText("Flips: " + (++flip_count));

                    if (firstCard == null) {
                        firstCard = card;
                        firstCard.setImageResource(img_id);
                        return;
                    }

                    if (hm.get(firstCard).img_id == img_id) {
                        card.setImageResource(img_id);
                        processCardMatch(firstCard, card);
                    } else {
                        processCardNoMatch(firstCard, card);
                    }
                }

                /**
                 * Checks whether the card can be flipped.
                 *
                 * @param card The image button representing the card.
                 * @return true if the card can be flipped, false otherwise.
                 */
                private boolean canFlipCard(ImageButton card) {
                    return !isBoardLocked && (card != firstCard) && !matchedCards.contains(card);
                }

                /**
                 * Handles the case when two cards match.
                 * Disables the matched cards and updates the match counter.
                 *
                 * @param card1 The first card that was flipped.
                 * @param card2 The second card that was flipped.
                 */
                private void processCardMatch(ImageButton card1, ImageButton card2) {
                    playMatchSound();

                    matchedCards.add(card1);
                    matchedCards.add(card2);
                    card1.setEnabled(false);
                    card2.setEnabled(false);
                    matches.setText("Matches: " + (matchedCards.size() / 2));

                    if (matchedCards.size() == buttons.size()) {
                        showWinDialog(flip_count);
                    }

                    firstCard = null;
                }

                /**
                 * Handles the case when two cards do not match.
                 * After a short delay, both cards are flipped back over.
                 *
                 * @param card1 The first card that was flipped.
                 * @param card2 The second card that was flipped.
                 */
                private void processCardNoMatch(ImageButton card1, ImageButton card2) {
                    isBoardLocked = true;
                    firstCard = card1;
                    secondCard = card2;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            card1.setImageResource(R.drawable.card_back);
                            card2.setImageResource(R.drawable.card_back);
                            firstCard = null;
                            secondCard = null;
                            isBoardLocked = false;
                        }
                    }, 1000);
                }
            });
        }
    }

    /**
     * Shuffles the board, resets the game state, and enables all cards.
     */
    private void shuffleBoard() {
        Collections.shuffle(img_ids, new Random());
        matchedCards.clear();
        for (ImageButton card : buttons) {
            card.setEnabled(true);
            card.setImageResource(R.drawable.card_back);
        }
        flip_count = 0;
        match_count = 0;
        flips.setText("Flips: " + flip_count);
        matches.setText("Matches: " + match_count);
    }

    /**
     * Shows a dialog when the player wins the game.
     */
    private void showWinDialog(int flipsCount) {
        // Enregistrer la victoire dans la base de données
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.insertCurrentVictory(flipsCount);

        // Obtenir la date actuelle au format "yyyy-MM-dd HH:mm:ss"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sdf.format(new Date());

        // Play win sound
        playWinSound();

        // Jouer le son de victoire
        playWinSound();

        // Sauvegarder la victoire avec la date actuelle et le nombre de flips
        saveVictory(currentDate, flip_count);

        // Display the dialog
        builder.setMessage("You won the game!")
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, id) -> shuffleBoard())
                .show();
    }

    /**
     * Handles the completion of a background task.
     *
     * @param result The result of the background task.
     */
    @Override
    public void onTaskComplete(String result) {
        // Handle task completion if necessary
    }

    /**
     * Inner class representing a card in the game.
     */
    private static class Card {
        /**
         * The image ID of the card.
         */
        int img_id;

        /**
         * The face-up state of the card.
         */
        boolean isFaceUp;

        /**
         * Constructs a Card object with the given image ID and face-up state.
         *
         * @param img_id The image ID of the card.
         * @param isFaceUp The face-up state of the card.
         */
        Card(int img_id, boolean isFaceUp) {
            this.img_id = img_id;
            this.isFaceUp = isFaceUp;
        }
    }
    public void saveVictory(String date, int flips) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("flips", flips);

        db.insert("victories", null, values);
        db.close();
    }




    /**
     * Plays the match sound effect.
     */
    private void playMatchSound() {
        soundPool.play(matchSoundId, 1, 1, 1, 0, 1);
    }

    /**
     * Plays the win sound effect.
     */
    private void playWinSound() {
        soundPool.play(winSoundId, 1, 1, 1, 0, 1);
    }
}
