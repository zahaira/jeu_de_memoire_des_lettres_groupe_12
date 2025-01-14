package com.ensa.jeu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adaptateur pour afficher une liste de victoires dans un RecyclerView.
 * Utilise un élément de mise en page défini dans le fichier XML `item_victory`.
 */
public class VictoryAdapter extends RecyclerView.Adapter<VictoryAdapter.VictoryViewHolder> {

    /**
     * Liste des objets Victory à afficher.
     */
    private final List<Victory> victoryList;

    /**
     * Constructeur de l'adaptateur.
     *
     * @param victoryList la liste des victoires à afficher.
     */
    public VictoryAdapter(List<Victory> victoryList) {
        this.victoryList = victoryList;
    }

    /**
     * Crée une nouvelle instance de ViewHolder.
     *
     * @param parent   le ViewGroup parent auquel l'élément sera ajouté.
     * @param viewType le type de la vue (non utilisé ici).
     * @return une nouvelle instance de {@link VictoryViewHolder}.
     */
    @NonNull
    @Override
    public VictoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_victory, parent, false);
        return new VictoryViewHolder(view);
    }

    /**
     * Lie les données d'un objet Victory à une vue.
     *
     * @param holder   le ViewHolder contenant les vues.
     * @param position la position de l'élément dans la liste.
     */
    @Override
    public void onBindViewHolder(@NonNull VictoryViewHolder holder, int position) {
        Victory victory = victoryList.get(position);
        holder.dateText.setText(victory.getDate());
        holder.flipsText.setText("Flips: " + victory.getFlips());
    }

    /**
     * Retourne le nombre total d'éléments dans la liste.
     *
     * @return le nombre d'éléments dans la liste.
     */
    @Override
    public int getItemCount() {
        return victoryList.size();
    }

    /**
     * ViewHolder pour les éléments de type Victory.
     * Contient les références aux vues de l'élément de mise en page.
     */
    public static class VictoryViewHolder extends RecyclerView.ViewHolder {
        /**
         * Vue affichant la date de la victoire.
         */
        TextView dateText;

        /**
         * Vue affichant le nombre de tentatives (flips).
         */
        TextView flipsText;

        /**
         * Constructeur du ViewHolder.
         *
         * @param itemView la vue de l'élément.
         */
        public VictoryViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
            flipsText = itemView.findViewById(R.id.flipsText);
        }
    }
}
