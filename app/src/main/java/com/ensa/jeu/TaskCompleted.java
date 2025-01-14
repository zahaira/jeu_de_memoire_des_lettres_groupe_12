package com.ensa.jeu;
/**
 * Interface pour gérer la complétion des tâches asynchrones.
 */
public interface TaskCompleted {
    /**
     * Méthode appelée lorsque la tâche est terminée.
     *
     * @param result Le résultat de la tâche sous forme de chaîne de caractères.
     */
    public void onTaskComplete(String result);
}

