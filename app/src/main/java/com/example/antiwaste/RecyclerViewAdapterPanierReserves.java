package com.example.antiwaste;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class RecyclerViewAdapterPanierReserves extends RecyclerView.Adapter<RecyclerViewAdapterPanierReserves.ConteneurDeDonnee> {
    private ArrayList<Reserver> donnees;
    private  DetecteurDeClicSurRecycler detecteurDeClicSurRecycler;
    String pdc="http://192.168.43.144:8080";

    public RecyclerViewAdapterPanierReserves(ArrayList<Reserver> donnees) {
        this.donnees = donnees;
        this.detecteurDeClicSurRecycler=detecteurDeClicSurRecycler;
    }
    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_panier_reservations, parent, false);
        return new ConteneurDeDonnee(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int
            position) {
        String dateString="";
        String jour="";
        conteneur.titrePanier.setText(donnees.get(position).getPaniers().getTitre());
        conteneur.categoriePanier.setText(donnees.get(position).getPaniers().getType());
        conteneur.nombrePanier.setText(donnees.get(position).getNombreExemplairesReserves()+" ");
        conteneur.prixPanier.setText(donnees.get(position).getPaniers().getPrix()+" €");
        dateString=donnees.get(position).getPaniers().getDateEtHeure().substring(0,10)+" "
                +donnees.get(position).getPaniers().getDateEtHeure().substring(11,donnees.get(position).getPaniers().getDateEtHeure().length()-3);

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

        conteneur.imageAnnule.setOnClickListener(v -> {
            SharedPreferences prefs = v.getContext().getSharedPreferences("utilisateur", MODE_PRIVATE);
            long id_utilisateur = prefs.getLong("utilisateur", (long)0);
            String urlAnnulerReservation=pdc+"/annulerReservationPanier/"+id_utilisateur+"/"+donnees.get(position).getId_reservation();
            annulerReservationPanier(v,urlAnnulerReservation);
            Toast.makeText(v.getContext(), "Vous annulé la réservation du panier de "+donnees.get(position).getPaniers().getTitre(),Toast.LENGTH_LONG).show();
            donnees.remove(position);
            this.notifyItemRemoved(position);

        });


    }
    @Override
    public int getItemCount() {
        return donnees.size();
    }

    public static class ConteneurDeDonnee extends RecyclerView.ViewHolder   {

        TextView titrePanier;
        TextView categoriePanier;
        TextView nombrePanier;
        ImageView imagePanier;
        ImageView imageAnnule;
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
            imageAnnule = itemView.findViewById(R.id.canvelReservation);


        }



    }


    public void annulerReservationPanier(View view, String url){
        boolean result;

        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Log.e("Liste des commercants:",response.toString());
                        Toast.makeText(view.getContext(),"Reservation annulée",Toast.LENGTH_SHORT );



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Error Reser", error.toString());

                        // Toast.makeText(LoginActivity.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);

    }



}