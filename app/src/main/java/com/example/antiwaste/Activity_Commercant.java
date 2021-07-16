package com.example.antiwaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity_Commercant extends AppCompatActivity implements DetecteurDeClicSurRecycler{

    private RecyclerView mRecyclerViewPanierDuCommercant;
    private RecyclerViewAdapterPanier mAdapterPanierDuCommercant;
    private RecyclerView.LayoutManager mLayoutManagerPanierDuCommercant;
    private ScrollView scrollView;
    public static Commercant commercant;


    TextView titre;
    TextView categorie;
    TextView nbrDePaniers;
    TextView description;
    TextView adressetxt;
    ImageView logoCommercant;
    ImageView imageFavoris;

    String pdc="http://192.168.43.144";
    String url=pdc+":8080/listeDesPaniersParCommercant/";


    boolean favoris;

    long id_utilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Adresse adresse = commercant.getAdresse();
        super.onCreate(savedInstanceState);
        url=url+commercant.getId_commercant();
        setContentView(R.layout.activity_commercant);

        SharedPreferences prefs = getSharedPreferences("utilisateur", MODE_PRIVATE);
        id_utilisateur = prefs.getLong("utilisateur", (long)0);

        scrollView=findViewById(R.id.scrollCommercant);
        scrollView.setSmoothScrollingEnabled(true);

        titre=findViewById(R.id.nomDuCommercant);
        titre.setText(commercant.getNom());

        categorie=findViewById(R.id.categorieCommercant);
        categorie.setText(commercant.getCategorie());

        description=findViewById(R.id.descriptionDuCommercant);
        description.setText(commercant.getDescription());


        adressetxt=findViewById(R.id.adresseDuCommercant);
        adressetxt.setText(adresse.getNumeroDeRue()+" "+adresse.getRue()+", "+adresse.getCodePostal()+" "+adresse.getVille()+".");

        logoCommercant=findViewById(R.id.imageCommercant);
        if (logoCommercant != null) {
            /*-------------fatching image------------*/;
            new ImageDownloaderTask(logoCommercant).execute(commercant.getLogo());
        }

        nbrDePaniers=findViewById(R.id.nombrePanierCommercant);

        listeDesPaniersDuCommercant(url);

        favoris =false;


        if (commercant.getUtilisateurs().size()>0){
            for (int i=0;i<commercant.getUtilisateurs().size();i++) {
                if (commercant.getUtilisateurs().get(i).getId_utilisateur() == id_utilisateur) {
                    favoris = true;
                    break;
                }
            }
        }

        imageFavoris= findViewById(R.id.idFavoris);

        if (favoris==false){
            imageFavoris.setImageResource(R.drawable.ic_empy_heart);
        }else imageFavoris.setImageResource(R.drawable.ic_heart);





    }

    public void onClickFavoris(View view) {
        String urlFavoris=pdc+":8080/ajouterCommercantAuxFavoris/"+id_utilisateur+"/"+commercant.getId_commercant();

        String urlSuppFavoris=pdc+":8080/supprimerCommercantAuxFavoris/"+id_utilisateur+"/"+commercant.getId_commercant();

        if (favoris== false){
            ajouterCommercantFavoris(urlFavoris);
            Toast.makeText(Activity_Commercant.this, "Super! Ce commerçant a été ajouté à vos favoris", Toast.LENGTH_SHORT).show();
        }
        else {
            supprimerCommercantFavoris(urlSuppFavoris);
            Toast.makeText(Activity_Commercant.this, "Oops! Ce commerçant a été retiré de vos favoris", Toast.LENGTH_SHORT).show();
        }
        }


    private ArrayList<Panier> getDataSource() {
        ArrayList listePanier = new ArrayList<Panier>();
        //ArrayList listePanier= new ArrayList();
        //listePanier.add (new Panier(1,"Panier de viénoiserie et de pain" ,"khra","Panier de 5 baguette", "Boulangerie" ,"15",15,"objetcommercant"));

        return listePanier;
    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    public void clicSurRecyclerItem(int position, View v) {
        TextView titre= v.findViewById(R.id.titrePanier);
        Toast.makeText(Activity_Commercant.this,"le panier "+titre.getText().toString(), Toast.LENGTH_LONG).show();
        startActivity(new Intent(Activity_Commercant.this, Activity_Panier.class));

    }

    public void listeDesPaniersDuCommercant(String url){
        Log.e("Liste des  maps",url);
        RequestQueue requestQueue = Volley.newRequestQueue(Activity_Commercant.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Log.e("Liste des commercants:",response.toString());

                        Map<String, ArrayList<Panier>> retMap = new Gson().fromJson(
                                response.toString(), new TypeToken<HashMap<String, ArrayList<Panier>>>() {}.getType());

                        long nbr=0;
                        if (retMap.get("Commercant").size()>0) {
                        for (int i = 0 ; i<retMap.get("Commercant").size();i++){
                            nbr=nbr+retMap.get("Commercant").get(i).getNbrDExemplaires();
                        }}

                        nbrDePaniers.setText(nbr+ " Restants");

                        Log.e("Liste P P C ",retMap.toString());
                        affichagePaniersDuCommercant(retMap.get("Commercant"));

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


    public void affichagePaniersDuCommercant( ArrayList<Panier> listePaniersDuCommercant) {

        mLayoutManagerPanierDuCommercant=new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mLayoutManagerPanierDuCommercant=new GridLayoutManager(this,1,GridLayoutManager.VERTICAL, false);


        mRecyclerViewPanierDuCommercant = (RecyclerView) findViewById(R.id.recyclerview_commercant);
        mRecyclerViewPanierDuCommercant.setHasFixedSize(false);
        mRecyclerViewPanierDuCommercant.setLayoutManager(mLayoutManagerPanierDuCommercant);
        mAdapterPanierDuCommercant = new RecyclerViewAdapterPanier(listePaniersDuCommercant);
        mRecyclerViewPanierDuCommercant.setAdapter(mAdapterPanierDuCommercant);

        mAdapterPanierDuCommercant.setDetecteurDeClicSurRecycler(this);


    }

    public void ajouterCommercantFavoris(String url){

        RequestQueue requestQueue = Volley.newRequestQueue(Activity_Commercant.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        imageFavoris.setImageResource(R.drawable.ic_heart);
                        favoris=true;
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


    public void supprimerCommercantFavoris(String url){

        RequestQueue requestQueue = Volley.newRequestQueue(Activity_Commercant.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        imageFavoris.setImageResource(R.drawable.ic_empy_heart);
                        favoris=false;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Err supp favoris", error.toString());
                        // Toast.makeText(LoginActivity.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);

    }



}