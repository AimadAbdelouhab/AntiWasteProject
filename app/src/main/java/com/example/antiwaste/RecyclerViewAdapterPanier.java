package com.example.antiwaste;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;




public class RecyclerViewAdapterPanier extends RecyclerView.Adapter<RecyclerViewAdapterPanier.ConteneurDeDonnee> {
    private ArrayList<Panier> donnees;
    private static DetecteurDeClicSurRecycler detecteurDeClicSurRecycler;

    public RecyclerViewAdapterPanier(ArrayList<Panier> donnees) {
        this.donnees = donnees;
    }
    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_panier, parent, false);
        return new ConteneurDeDonnee(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int
            position) {
        String dateString="";
        String jour="";
        conteneur.titrePanier.setText(donnees.get(position).getTitre());
        conteneur.categoriePanier.setText(donnees.get(position).getType());
        conteneur.nombrePanier.setText(donnees.get(position).getNbrDExemplaires()+" restants");
        conteneur.prixPanier.setText(donnees.get(position).getPrix()+" €");
        dateString=donnees.get(position).getDateEtHeure().substring(0,10)+" "+donnees.get(position).getDateEtHeure().substring(11,donnees.get(position).getDateEtHeure().length()-3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHeure = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        LocalTime localTime = LocalTime.parse(dateString,formatter);

        if(localDate.isEqual(LocalDate.now()))
        {            jour= "Aujourd'hui à "+localTime.toString();
        }else if (LocalDate.of(localDate.getYear(),localDate.getMonthValue(),localDate.getDayOfMonth()-1).isEqual(localDate.now())){
            jour = "Demain à "+localTime.toString();
        }
        else {
            String localDate2 = localDate.format(formatterDate);
            String localTime2 = localTime.format(formatterHeure);
            jour = localDate2+" à "+localTime2;
        }


        conteneur.datePanier.setText(jour);
        conteneur.materialCardView.setOnClickListener(v -> {
            Intent intent = new Intent (v.getContext(), Activity_Panier.class);
            v.getContext().startActivity(intent);
            Activity_Panier.panier=donnees.get(position);
        });

//        conteneur.imagePanier.setImageDrawable(LoadImageFromWebOperations(donnees.get(position).getImage()));

        if (conteneur.imagePanier != null) {
            /*-------------fatching image------------*/;
            new ImageDownloaderTask(conteneur.imagePanier).execute(donnees.get(position).getImage());
        }
        if (conteneur.imageCommercant != null) {
            /*-------------fatching image------------*/;
            new ImageDownloaderTask(conteneur.imageCommercant).execute(donnees.get(position).getCommercant().getLogo());
        }


    }
    @Override
    public int getItemCount() {
        return donnees.size();
    }

    public static class ConteneurDeDonnee extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titrePanier;
        TextView categoriePanier;
        TextView nombrePanier;
        ImageView imagePanier;
        ImageView imageCommercant;
        TextView prixPanier;
        TextView datePanier;
        TextView distancePanier;
        CardView materialCardView;

        public ConteneurDeDonnee(View itemView) {
            super(itemView);
            titrePanier = (TextView) itemView.findViewById(R.id.titrePanier);
            categoriePanier = (TextView) itemView.findViewById(R.id.categoriePanier);
            nombrePanier = (TextView) itemView.findViewById(R.id.nombrePanier);
            imagePanier = (ImageView) itemView.findViewById(R.id.imagePanier);
            imageCommercant = (ImageView) itemView.findViewById(R.id.imageCommercant);
            prixPanier = (TextView) itemView.findViewById(R.id.prixPanier);
            datePanier=(TextView) itemView.findViewById(R.id.date_Panier);
            distancePanier=(TextView) itemView.findViewById(R.id.distancePanier);
            materialCardView= itemView.findViewById(R.id.cardView_panier);

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