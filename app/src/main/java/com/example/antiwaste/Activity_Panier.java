package com.example.antiwaste;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity_Panier extends AppCompatActivity {

    TextView nomDuPanier;
    TextView typePanier;
    TextView nombrePanier;
    TextView prixPanier;
    TextView descritionPanier;
    TextView nomDuCommercant;
    TextView adresseDuCommercant,dateDuPanier;
    ImageView imageDuPanier;
    ImageView imageDuCommercant;
    Button buttonReserver;

    TextView textViewNombreDePanierAReserver;
    public static Panier panier;
    public static Commercant commercant;
    String pdc="http://192.168.43.144";
    String url=pdc+":8080/commercantParPanier/";
    String urlReserver=pdc+":8080/reserverPanier/";
    String texte;

    long id_utilisateur;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);


        SharedPreferences prefs = getSharedPreferences("utilisateur", MODE_PRIVATE);
        id_utilisateur = prefs.getLong("utilisateur", (long)0);

        url=url+panier.getId_panier();
        commercantParPanier(url);

        //Log.e("Commercant:",commercant.toString()+"");

        nomDuPanier = findViewById(R.id.nomDuPanier);
        nomDuPanier.setText(panier.getTitre());

        typePanier = findViewById(R.id.typeDuPanier);
        typePanier.setText(panier.getType());

        nombrePanier= findViewById(R.id.nomreDePanier);
        nombrePanier.setText(panier.getNbrDExemplaires()+" Panier(s) disponible(s)");

        prixPanier=findViewById(R.id.prixDuPanier);
        prixPanier.setText("à "+panier.getPrix()+" €");

        descritionPanier= findViewById(R.id.descriptionDuPanier);
        descritionPanier.setText(panier.getDescription());

        imageDuPanier= findViewById(R.id.imageDuPanier);
        if (imageDuPanier != null) {
            /*-------------fatching image------------*/;
            new ImageDownloaderTask(imageDuPanier).execute(panier.getImage());
        }

        dateDuPanier=findViewById(R.id.dateDuPanier);


        String dateString="";
        String jour="";

        dateString=panier.getDateEtHeure().substring(0,10)+" "+panier.getDateEtHeure().substring(11,panier.getDateEtHeure().length()-3);

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

        dateDuPanier.setText(jour);

        buttonReserver=findViewById(R.id.btnReserverPanier);
        buttonReserver.setEnabled(false);


        textViewNombreDePanierAReserver = findViewById(R.id.nombreDePanierAReserver);





    }

    public void onClickImageCommercant(View view) {
        startActivity(new Intent(Activity_Panier.this,Activity_Commercant.class));
    }

    public void onClickPlus(View view) {
        int nombreDePanierAReserver = Integer.parseInt(textViewNombreDePanierAReserver.getText().toString());
        if (nombreDePanierAReserver<panier.getNbrDExemplaires()) nombreDePanierAReserver++;
        textViewNombreDePanierAReserver.setText(nombreDePanierAReserver+"");
        buttonReserver.setEnabled(true);
    }

    public void onCLickMoins(View view) {
        textViewNombreDePanierAReserver = findViewById(R.id.nombreDePanierAReserver);
        int nombreDePanierAReserver = Integer.parseInt(textViewNombreDePanierAReserver.getText().toString());
        if (nombreDePanierAReserver>0) nombreDePanierAReserver--;
        textViewNombreDePanierAReserver.setText(nombreDePanierAReserver+"");
        if (textViewNombreDePanierAReserver.getText().toString().equals("0")){
            buttonReserver.setEnabled(false);
        }
    }

    public void onClickReserverPanier(View view) {
        textViewNombreDePanierAReserver = findViewById(R.id.nombreDePanierAReserver);
        String nombreAReserver = textViewNombreDePanierAReserver.getText().toString();
        urlReserver=urlReserver+id_utilisateur+"/"+panier.getId_panier()+"/"+nombreAReserver;
        if (textViewNombreDePanierAReserver.getText().toString().equals("0")) texte="Vous n'avez selectionné aucun panier à reserver";
        else {
            reserverPanier(urlReserver);
            }
    }

    public void commercantParPanier(String url){
        Log.e("Liste des  maps",url);
        RequestQueue requestQueue = Volley.newRequestQueue(Activity_Panier.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        Commercant commercantJson= new Gson().fromJson(
                                response.toString(), new TypeToken<Commercant>() {}.getType());
                        Log.e("Commercant panier",response.toString());
                        commercant=commercantJson;
                        Activity_Commercant.commercant=commercantJson;

                        nomDuCommercant=findViewById(R.id.nomDuCommercant);
                        nomDuCommercant.setText(commercant.getNom());

                        adresseDuCommercant=findViewById(R.id.adresseDuCommercant);
                        adresseDuCommercant.setText(commercant.getAdresse().getNumeroDeRue()+" " +
                                ""+commercant.getAdresse().getRue()+", " +
                                ""+commercant.getAdresse().getCodePostal()+" " +
                                ""+commercant.getAdresse().getVille()+".");

                        imageDuCommercant=findViewById(R.id.imageCommercant);
                        if (imageDuCommercant != null) {
                            /*-------------fatching image------------*/;
                            new ImageDownloaderTask(imageDuCommercant).execute(commercant.getLogo());
                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Error Panier", error.toString());
                        // Toast.makeText(LoginActivity.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);

    }

    public void reserverPanier(String url){

        RequestQueue requestQueue = Volley.newRequestQueue(Activity_Panier.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        texte="Vous avez réservé "+textViewNombreDePanierAReserver.getText().toString()+" panier(s)";
                        Toast.makeText(Activity_Panier.this,texte, Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Activity_Panier.this,Activity_Commercant.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Error favoris", error.toString());
                        // Toast.makeText(LoginActivity.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);

    }


}