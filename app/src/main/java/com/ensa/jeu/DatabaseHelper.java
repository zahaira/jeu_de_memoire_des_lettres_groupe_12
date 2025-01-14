package com.ensa.jeu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "game.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_VICTORIES = "victories";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_FLIPS = "flips";

    private static final String CREATE_TABLE_VICTORIES =
            "CREATE TABLE " + TABLE_VICTORIES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_FLIPS + " INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_VICTORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VICTORIES);
        onCreate(db);
    }

    /**
     * Insérer une nouvelle victoire dans la base de données.
     *
     * @param date La date de la victoire.
     * @param flips Le nombre de mouvements pour gagner.
     * @return true si l'insertion a réussi, false sinon.
     */
    public boolean insertVictory(String date, int flips) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_FLIPS, flips);
        long result = db.insert(TABLE_VICTORIES, null, values);
        return result != -1;
    }

    /**
     * Récupérer toutes les victoires depuis la base de données.
     *
     * @return Un Cursor contenant les données des victoires.
     */
    public Cursor getAllVictories() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_VICTORIES + " ORDER BY id DESC", null);
    }

    /**
     * Méthode utilitaire pour insérer une victoire avec la date actuelle et le nombre de flips.
     *
     * @param flips Le nombre de mouvements pour gagner.
     */
    public void insertCurrentVictory(int flips) {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        insertVictory(currentDate, flips);
    }
}
