/**
 * Représente une carte du jeu de mémoire.
 * Chaque carte a une image associée et un état (fermé ou ouvert).
 */
package com.ensa.jeu;

/**
 * La classe Card modélise une carte avec un identifiant d'image et un état.
 */
public class Card {

    /**
     * L'identifiant de l'image associée à la carte.
     */
    int img_id;

    /**
     * Indique si la carte est fermée (true) ou ouverte (false).
     */
    boolean isClosed;

    /**
     * Constructeur pour initialiser une carte avec une image et un état.
     *
     * @param id L'identifiant de l'image de la carte.
     * @param isClosed L'état initial de la carte (fermée ou ouverte).
     */
    public Card(int id, boolean isClosed) {
        this.img_id = id;
        this.isClosed = isClosed;
    }
}
