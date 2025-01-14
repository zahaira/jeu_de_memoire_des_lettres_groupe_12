package com.ensa.jeu;

/**
 * Représente une victoire dans le jeu, contenant des informations telles que
 * l'identifiant, la date et le nombre de tentatives nécessaires pour gagner.
 */
public class Victory {
    /**
     * Identifiant unique de la victoire.
     */
    private int id;

    /**
     * Date de la victoire sous forme de chaîne.
     */
    private String date;

    /**
     * Nombre de tentatives effectuées pour atteindre la victoire.
     */
    private int flips;

    /**
     * Constructeur pour initialiser une instance de la classe Victory.
     *
     * @param id    l'identifiant unique de la victoire.
     * @param date  la date de la victoire.
     * @param flips le nombre de tentatives effectuées.
     */
    public Victory(int id, String date, int flips) {
        this.id = id;
        this.date = date;
        this.flips = flips;
    }

    /**
     * Retourne l'identifiant unique de la victoire.
     *
     * @return l'identifiant unique de la victoire.
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne la date de la victoire.
     *
     * @return la date de la victoire sous forme de chaîne.
     */
    public String getDate() {
        return date;
    }

    /**
     * Retourne le nombre de tentatives effectuées pour atteindre la victoire.
     *
     * @return le nombre de tentatives.
     */
    public int getFlips() {
        return flips;
    }
}
