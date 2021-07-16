package com.example.antiwaste;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;


public class RecyclerViewAdapterCommercant extends RecyclerView.Adapter<RecyclerViewAdapterCommercant.ConteneurDeDonnee> {
    private ArrayList<Commercant> donnees;
    private static DetecteurDeClicSurRecycler detecteurDeClicSurRecycler;

    public RecyclerViewAdapterCommercant(ArrayList<Commercant> donnees) {
        this.donnees = donnees;
    }
    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_commercant, parent, false);
        return new ConteneurDeDonnee(view);
    }
    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int
            position) {
        conteneur.id=donnees.get(position).getId_commercant();
        conteneur.titreCommercant.setText(donnees.get(position).getNom());
        conteneur.categorieCommercant.setText(donnees.get(position).getCategorie());
        conteneur.nombrePanierCommercant.setText("3 restants");
        conteneur.distanceCommercant.setText("3.2 km");

        conteneur.materialCardView.setOnClickListener(v -> {
            Intent intent = new Intent (v.getContext(), Activity_Commercant.class);
            v.getContext().startActivity(intent);
            Activity_Commercant.commercant=donnees.get(position);
        });

        if (conteneur.imageCommercant != null) {
            /*-------------fatching image------------*/;
            new ImageDownloaderTask(conteneur.imageCommercant).execute(donnees.get(position).getLogo());
        }

    }
    @Override
    public int getItemCount() {
        return donnees.size();
    }

    public static class ConteneurDeDonnee extends RecyclerView.ViewHolder implements View.OnClickListener {

        long id;
        TextView titreCommercant;
        TextView categorieCommercant;
        TextView nombrePanierCommercant;
        ImageView imageCommercant;
        TextView distanceCommercant;
        CardView materialCardView;

        public ConteneurDeDonnee(View itemView) {
            super(itemView);

            titreCommercant = (TextView) itemView.findViewById(R.id.titreCommercant);
            categorieCommercant = (TextView) itemView.findViewById(R.id.categorieCommercant);
            nombrePanierCommercant = (TextView) itemView.findViewById(R.id.nombrePanierCommercant);
            imageCommercant = (ImageView) itemView.findViewById(R.id.imageCommercant);
            distanceCommercant=(TextView) itemView.findViewById(R.id.distanceCommercant);
            materialCardView= itemView.findViewById(R.id.cardView_commercant);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            detecteurDeClicSurRecycler.clicSurRecyclerItem(getAdapterPosition(), v);
        }
    }

    public void setDetecteurDeClicSurRecycler(DetecteurDeClicSurRecycler
                                                      detecteurDeClicSurRecycler) {
        this.detecteurDeClicSurRecycler = detecteurDeClicSurRecycler;
    }




}